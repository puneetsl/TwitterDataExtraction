package com.factset.hackathon.twitterdata;

import java.util.Date;

public class TweetDAO {

	
	String tweet;
	String date;
	float sentimentValue;
	int firstPostDate ;

	public int getFirstPostDate() {
		return firstPostDate;
	}
	public void setFirstPostDate(int firstPostDate) {
		this.firstPostDate = firstPostDate;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getSentimentValue() {
		return sentimentValue;
	}
	public void setSentimentValue(float sentimentValue) {
		this.sentimentValue = sentimentValue;
	}
	
	
	
}
