package com.chinese_character_lion;

import java.util.ArrayList;

public class Result {

    private final int mNumberOne, mNumberTwo, mNumberThree, mNumberFour, mNumberFive;
    private final float mProbability;
    private final long mTimeCost;
    private ArrayList<Float> mProbs;

    public ArrayList<Float> getmProbs() {
        return mProbs;
    }

    public Result(float[] probs, long timeCost) {
        mProbs = new ArrayList<>();

        mNumberOne = argmax(probs);
        mProbs.add(maxper(probs));
        probs[mNumberOne] = 0.0f;

        mNumberTwo = argmax(probs);
        mProbs.add(maxper(probs));
        probs[mNumberTwo] = 0.0f;

        mNumberThree = argmax(probs);
        mProbs.add(maxper(probs));
        probs[mNumberThree] = 0.0f;

        mNumberFour = argmax(probs);
        mProbs.add(maxper(probs));
        probs[mNumberFour] = 0.0f;

        mNumberFive = argmax(probs);
        mProbs.add(maxper(probs));
        mProbability = probs[mNumberOne];

        mTimeCost = timeCost;
    }

    public int getmNumberOne() {
        return mNumberOne;
    }

    public int getmNumberTwo() {
        return mNumberTwo;
    }

    public int getmNumberThree() {
        return mNumberThree;
    }

    public int getmNumberFour() {
        return mNumberFour;
    }

    public int getmNumberFive() {
        return mNumberFive;
    }

    public float getProbability() {
        return mProbability;
    }

    public long getTimeCost() {
        return mTimeCost;
    }

    private static int argmax(float[] probs) {
        int maxIdx = -1;
        float maxProb = 0.0f;
        for (int i = 0; i < probs.length; i++) {
            if (probs[i] > maxProb) {
                maxProb = probs[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    private static float maxper(float[] probs) {
        int maxIdx = -1;
        float maxProb = 0.0f;
        for (int i = 0; i < probs.length; i++) {
            if (probs[i] > maxProb) {
                maxProb = probs[i];
                maxIdx = i;
            }
        }
        return maxProb;
    }

}
