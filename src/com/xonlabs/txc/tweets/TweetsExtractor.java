package com.xonlabs.txc.tweets;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.Query.ResultType;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetsExtractor
{

   public static List<Status> getTweetsByHandle(String handle, int maxcount) throws Exception
   {
      ConfigurationBuilder cb = new ConfigurationBuilder();
      cb.setDebugEnabled(true).setOAuthConsumerKey("C6cUT1Spwd4GRAPZcUH7gr4qL")
               .setOAuthConsumerSecret("tUG5W0eAmouq7vw1UZxulu1C24sgBn563Z5amnWNgw4ARiqenH")
               .setOAuthAccessToken("407651040-79M1aoKKDhPlTP8LBLMypAI4TfInD2ZBEzRI6hkf")
               .setOAuthAccessTokenSecret("70K6CII1YseeOVXxLugPdxj42EkfJOPQk6jAkYQTL5rxN").setTweetModeExtended(true);

      Twitter twitter = new TwitterFactory(cb.build()).getInstance();
      Paging paging = new Paging();
      paging.setCount(maxcount);
      ResponseList<Status> result = twitter.getUserTimeline(handle, paging);

      List<Status> response = new ArrayList<>();
      for (Status s : result)
      {
         response.add(s);
      }

      return response;
   }

   public static List<Status> getTweets(String topic, int num, int mode) throws TwitterException
   {
      ConfigurationBuilder cb = new ConfigurationBuilder();
      cb.setDebugEnabled(true).setOAuthConsumerKey("C6cUT1Spwd4GRAPZcUH7gr4qL")
               .setOAuthConsumerSecret("tUG5W0eAmouq7vw1UZxulu1C24sgBn563Z5amnWNgw4ARiqenH")
               .setOAuthAccessToken("407651040-79M1aoKKDhPlTP8LBLMypAI4TfInD2ZBEzRI6hkf")
               .setOAuthAccessTokenSecret("70K6CII1YseeOVXxLugPdxj42EkfJOPQk6jAkYQTL5rxN").setTweetModeExtended(true);

      Twitter twitter = new TwitterFactory(cb.build()).getInstance();
      Query query = new Query(topic);
      int numberOfTweets = num; // no of tweets needed
      long lastID = Long.MAX_VALUE;
      ArrayList<Status> tweets = new ArrayList<Status>();
      if (mode == 0)
      {
         query.setResultType(ResultType.mixed);
      }
      else if (mode == 1)
      {
         query.setResultType(ResultType.popular);
      }
      else
      {
         query.setResultType(ResultType.recent);
      }
      QueryResult result = twitter.search(query);

      while (tweets.size() < numberOfTweets && result.getTweets().size() > 0)
      {
         if (numberOfTweets - tweets.size() > 100)
         {
            query.setCount(100);
         }
         else
         {
            query.setCount(numberOfTweets - tweets.size());
         }
         try
         {
            result = twitter.search(query);
            tweets.addAll(result.getTweets());
            System.out.println("Gathered " + tweets.size() + " tweets");
            for (Status t : tweets)
            {
               if (t.getId() < lastID)
               {
                  lastID = t.getId();
               }
            }

         }
         catch (TwitterException te)
         {
            System.out.println("Couldn't connect: " + te);
         }
         ;
         query.setMaxId(lastID - 1);
      }

      return tweets;

   }

   public static void main(String[] args) throws Exception
   {
      List<Status> tweets = getTweets("Virat Kohli", 10, -1);
      for (Status t : tweets)
      {
         System.out.println(t.getText());
         System.out.println(
                  "-------------------------------------------------------------------------------------------------------");
      }

      // getTweetsByHandle("@sachin_rt", 100);

   }

}
