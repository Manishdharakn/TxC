package com.xonlabs.txc.vader;

import java.util.List;

/**
 *
 * a token, usually a single word, with POS (part of speech) tag
 *
 */
public class Token {

    private String value; // the value of the token
    private String posTag; // the part of speech tag (POS) of this item of text
    private double wordScore; // the vader individual word score

    public Token() {
    }

    // pretty print
    public String toString() {
        return value + ":" + posTag;
    }

    /**
     * conver a list of tokens to a readable string / very crude
     * for demo purposes
     * @param tokenList the list to convert
     * @return a string for the list
     */
    public static String tokenListToString( List<Token> tokenList ) {
        if ( tokenList != null ) {
            StringBuilder sb = new StringBuilder();
            for ( Token token : tokenList ) {
                sb.append( token.getValue() ).append(" ");
            }
            return sb.toString();
        }
        return "";
    }

    public Token(String value, String posTag) {
        this.value = value;
        this.posTag = posTag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPosTag() {
        return posTag;
    }

    public void setPosTag(String posTag) {
        this.posTag = posTag;
    }


    public double getWordScore() {
        return wordScore;
    }

    public void setWordScore(double wordScore) {
        this.wordScore = wordScore;
    }

}



