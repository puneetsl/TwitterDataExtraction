package com.factset.hackathon.beans;

/**
 * Created by pludu on 5/15/2015.
 */
public class Keyword {
    private String keyword;

    public Keyword(String keyword, int max_tweets) {
        this.keyword=keyword;
        this.max_tweets=max_tweets;
    }

    public int getMax_tweets() {
        return max_tweets;
    }

    public void setMax_tweets(int max_tweets) {
        this.max_tweets = max_tweets;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    private int max_tweets;
}
