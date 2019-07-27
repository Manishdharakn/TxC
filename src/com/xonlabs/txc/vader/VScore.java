package com.xonlabs.txc.vader;

import java.text.DecimalFormat;

/**
 *
 * a vader score element for a sentence
 *
 */
public class VScore {
    private double positive;
    private double neutral;
    private double negative;
    private double compound;

    public VScore() {
    }

    public VScore( double positive, double neutral, double negative, double compound ) {
        this.positive = positive;
        this.neutral = neutral;
        this.negative = negative;
        this.compound = compound;
    }

    public String toString() {
        DecimalFormat df3 = new DecimalFormat("#.###");
        DecimalFormat df4 = new DecimalFormat("#.####");
        return "{'neg': " + df3.format(negative) + ", 'neu': " + df3.format(neutral) +
                ", 'pos': " + df3.format(positive) + ", 'compound': " + df4.format(compound) +"}";
    }

    public double getPositive() {
        return positive;
    }

    public double getNeutral() {
        return neutral;
    }

    public double getNegative() {
        return negative;
    }

    public double getCompound() {
        return compound;
    }
}

