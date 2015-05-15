package com.factset.hackathon.twitterdata;

public class TweetDAO {

	
	String tweet;
	String date;
	double authorInfluenceLevel;
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
	public double getAuthorInfluenceLevel() {
		return authorInfluenceLevel;
	}
	public void setAuthorInfluenceLevel(double authorInfluenceLevel) {
		this.authorInfluenceLevel = authorInfluenceLevel;
	}
	
	
	
}
