package com.chinese_character_lion;

import java.util.ArrayList;

public class Model {

    private String ch_word;
    private String title;
    private String desc;
    private ArrayList<String> ch_mean;
    private ArrayList<String> category;

    public Model(String ch_word, String title, String desc, ArrayList<String> ch_mean, ArrayList<String> category) {
        this.ch_word = ch_word;
        this.title = title;
        this.desc = desc;
        this.ch_mean = ch_mean;
        this.category = category;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public ArrayList<String> getCh_mean() {
        return ch_mean;
    }

    public void setCh_mean(ArrayList<String> ch_mean) {
        this.ch_mean = ch_mean;
    }

    public String getCh_word() {
        return ch_word;
    }

    public void setCh_word(String ch_word) {
        this.ch_word = ch_word;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
