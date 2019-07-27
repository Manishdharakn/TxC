
package com.xonlabs.txc.vader;

import java.util.List;

public class Main
{

   public static void main(String[] args) throws Exception
   {

      String fileText = new String(
               "Hello How are you. Hope you are doing good. Yesterday I was very happy because I talked to you. But today you are not answering my call. I am too sad.");

      // setup Vader
      Vader vader = new Vader();
      vader.init(); // load vader

      // setup nlp processor
      VaderNLP vaderNLP = new VaderNLP();
      vaderNLP.init(); // load open-nlp

      // parse the text into a set of sentences
      List<List<Token>> sentenceList = vaderNLP.parse(fileText);

      // apply vader analysis to each sentence
      for (List<Token> sentence : sentenceList)
      {
         VScore vaderScore = vader.analyseSentence(sentence);
         System.out.println("sentence:" + Token.tokenListToString(sentence));
         System.out.println("Vader score:" + vaderScore.toString());
      }

   }

}
