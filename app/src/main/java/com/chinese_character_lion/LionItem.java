package com.chinese_character_lion;

public class LionItem {
    private String Ch_Word;
    private String Ko_Word;
    private String Mean;

    public LionItem(String ch_Word, String ko_Word, String mean) {
        Ch_Word = ch_Word;
        Ko_Word = ko_Word;
        Mean = mean;
    }

    public String getCh_Word() {
        return Ch_Word;
    }

    public String getKo_Word() {
        return Ko_Word;
    }

    public String getMean() {
        return Mean;
    }
}
