package com.chinese_character_lion;

public class Result {

    private final int mNumberOne, mNumberTwo, mNumberThree, mNumberFour, mNumberFive;
    private final float mProbability;
    private final long mTimeCost;

    public Result(float[] probs, long timeCost) {
        mNumberOne = argmax(probs);
        probs[mNumberOne] = 0.0f;
        mNumberTwo = argmax(probs);
        probs[mNumberTwo] = 0.0f;
        mNumberThree = argmax(probs);
        probs[mNumberThree] = 0.0f;
        mNumberFour = argmax(probs);
        probs[mNumberFour] = 0.0f;
        mNumberFive = argmax(probs);
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
}
