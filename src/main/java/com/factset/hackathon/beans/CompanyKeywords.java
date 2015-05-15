package com.factset.hackathon.beans;

import java.util.ArrayList;

/**
 * Created by pludu on 5/15/2015.
 */
public class CompanyKeywords {
    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    private String company_name;

    public ArrayList<Keyword> getKeywords() {
        return keywords;
    }
    public void addKeyword(String keyword, int max_tweets)
    {
        Keyword k = new Keyword(keyword,max_tweets);
        keywords.add(k);
    }
    private ArrayList<Keyword> keywords;

}
