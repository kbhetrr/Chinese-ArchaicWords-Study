package com.chinese_character_lion;

public class Hanja {

    private String HanjaText;
    private String HanjaMean;

    public Hanja(String hanjaText, String hanjaMean) {
        this.HanjaText = hanjaText;
        this.HanjaMean = hanjaMean;
    }

    public String getHanjaText() {
        return HanjaText;
    }

    public String getHanjaMean() {
        return HanjaMean;
    }
}
