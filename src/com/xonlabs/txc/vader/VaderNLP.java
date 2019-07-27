package com.xonlabs.txc.vader;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * simple tokenizer and pos tagger using OpenNLP
 *
 */
public class VaderNLP {


    // the apache-nlp sentence boundary detector
    private SentenceDetector sentenceDetector = null;

    // the apache-nlp penn-tree tagger
    private POSTaggerME posTagger = null;

    // apache-nlp tokenizer
    private Tokenizer tokenizer;

    public VaderNLP() {
    }

    /**
     * convert a piece of text to a list of parsed tokens with POS tags
     * @param text the text to parse
     * @return a list of sentences (each sentence a list of tokens itself) that is the entire text
     * @throws IOException if things don't go as planned
     */
    public List<List<Token>> parse( String text ) throws IOException {

        if ( text != null ) {

            List<List<Token>> sentenceList = new ArrayList<>();

            // this is how it works boys and girls - apache-opennlp
            String[] sentenceArray = getSentences(text);
            for (String sentenceStr : sentenceArray) {

                List<Token> sentence = new ArrayList<>();

                // get the results of the syntactic parse
                String[] words = getTokens(sentenceStr);
                String[] posTags = getTags(words);

                for (String s: posTags)
                {
                	//System.out.print(s+"\t");
                }
                // the number of tags should always match the number of words - a little primitive
                // how open-nlp treats it
                if ( words.length != posTags.length ) {
                    throw new IOException("unmatched words / posTags in nlp-parser");
                }

                // add this sentence - the first word in the sentence gets the "is a sentence start" marker
                for ( int i = 0; i < words.length; i++ ) {
                    sentence.add( new Token( words[i], posTags[i]) );
                }

                sentenceList.add( sentence );
            }

            return sentenceList;
        }
        return null;
    }

    /**
     * invoke the OpenNLP sentence detector to split text into sentences
     * @param text the text to split
     * @return a set of string representing nlp sentences
     */
    private String[] getSentences(String text) {
        return sentenceDetector.sentDetect(text);
    }

    /**
     * turn a sentence into a set of tokens (split words and punctuation etc)
     * @param sentence a string that is a sentence
     * @return a set of tokens from that sentence in order
     */
    private String[] getTokens(String sentence) {
        return tokenizer.tokenize(sentence);
    }

    /**
     * use a pos-tagger to get the set of penn-tree tags for a given set of tokens
     * that form a sentence
     * @param tokens a sentence split into tokens
     * @return a set of penn-tags
     */
    private String[] getTags(String[] tokens) {
        return posTagger.tag(tokens);
    }

    /**
     * initialise the parser and its constituents - called from spring init
     * @throws IOException
     */
    public void init() throws IOException {

        System.out.println("VaderNLP: init()");

        // setup the sentence splitter
        {
        	System.out.println("VaderNLP: loading en-sent.bin");
            try (InputStream modelIn = getClass().getResourceAsStream("en-sent.bin") ) {
                if (modelIn == null) {
                    throw new IOException("resource en-sent.bin not found in classpath");
                }
                SentenceModel sentenceModel = new SentenceModel(modelIn);
                sentenceDetector = new SentenceDetectorME(sentenceModel);
            }
        }

        // setup the max-ent tokenizer
        {
        	System.out.println("VaderNLP: loading en-token.bin");
            try ( InputStream modelIn = getClass().getResourceAsStream("en-token.bin") ) {
                if ( modelIn == null ) {
                    throw new IOException("resource en-sent.bin not found in classpath");
                }
                TokenizerModel tokenizerModel = new TokenizerModel(modelIn);
                tokenizer = new TokenizerME(tokenizerModel);
            }
        }

        // setup the pos tagger
        {
        	System.out.println("VaderNLP: loading en-pos-maxent.bin");
            try ( InputStream modelIn = getClass().getResourceAsStream("en-pos-maxent.bin") ) {
                if (modelIn == null) {
                    throw new IOException("resource en-sent.bin not found in classpath");
                }
                POSModel posModel = new POSModel(modelIn);
                posTagger = new POSTaggerME(posModel);
            }
        }


    }


}

