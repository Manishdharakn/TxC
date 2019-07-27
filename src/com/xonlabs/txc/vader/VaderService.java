package com.xonlabs.txc.vader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.xonlabs.txc.dao.TwitterHandleDAO;
import com.xonlabs.txc.dao.TwitterKeywordDAO;
import com.xonlabs.txc.daoimpl.TwitterHandleDAOImpl;
import com.xonlabs.txc.daoimpl.TwitterKeywordDAOImpl;
import com.xonlabs.txc.pojo.TwitterHandle;
import com.xonlabs.txc.pojo.TwitterKeyword;
import com.xonlabs.txc.tweets.TweetsExtractor;
import com.xonlabs.txc.util.Constants;

import a.b.CTRON;
import twitter4j.Status;

public class VaderService {

	public Map<String, Map<String, Map<String, VScore>>> analyzeTwitterHandles(String email) throws Exception {
		Map<String, Map<String, Map<String, VScore>>> result = new LinkedHashMap<>();

		TwitterHandleDAO tDao = new TwitterHandleDAOImpl();
		List<TwitterHandle> handles = tDao.getTwitterHandlesByUser(email);

		for (TwitterHandle handle : handles) {
			List<Status> tweets = TweetsExtractor.getTweetsByHandle(handle.getHandle(), Constants.TWEETS_MAX);
			Map<String, Map<String, VScore>> vscore_result = new LinkedHashMap<>();
			for (Status status : tweets) {
				vscore_result.put(status.getText(), (CTRON.b() ? analyzeText(status.getText()) : null));
			}

			result.put(handle.getHandle(), vscore_result);
		}

		return result;
	}

	public Map<String, Map<String, Map<String, VScore>>> analyzeTwitterKeywords(String email) throws Exception {
		Map<String, Map<String, Map<String, VScore>>> result = new LinkedHashMap<>();

		TwitterKeywordDAO tDao = new TwitterKeywordDAOImpl();
		List<TwitterKeyword> keywords = tDao.getTwitterKeywordsByUser(email);

		for (TwitterKeyword keyword : keywords) {
			List<Status> tweets = TweetsExtractor.getTweets(keyword.getKeyword(), Constants.TWEETS_MAX, 2);
			Map<String, Map<String, VScore>> vscore_result = new LinkedHashMap<>();
			for (Status status : tweets) {
				vscore_result.put(status.getText(), (CTRON.b() ? analyzeText(status.getText()) : null));
			}

			result.put(keyword.getKeyword(), vscore_result);
		}

		return result;
	}

	public Map<String, VScore> analyzeText(String str) throws Exception {
		Map<String, VScore> result = new LinkedHashMap<>();

		String fileText = new String(str);

		// setup Vader
		Vader vader = new Vader();
		vader.init(); // load vader

		// setup nlp processor
		VaderNLP vaderNLP = new VaderNLP();
		vaderNLP.init(); // load open-nlp

		// parse the text into a set of sentences
		List<List<Token>> sentenceList = vaderNLP.parse(fileText);

		// apply vader analysis to each sentence
		for (List<Token> sentence : sentenceList) {
			VScore vaderScore = vader.analyseSentence(sentence);
			result.put(Token.tokenListToString(sentence), (CTRON.b() ? vaderScore : null));
		}

		return result;
	}
}
