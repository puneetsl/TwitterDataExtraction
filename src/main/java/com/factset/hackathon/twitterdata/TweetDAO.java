package com.factset.hackathon.twitterdata;

public class TweetDAO {

	
	String tweet;
	String date;
	int authorInfluenceLevel;
	int firstPostDate ;

	public double getRankingScore() {
		return rankingScore;
	}

	public void setRankingScore(double rankingScore) {
		this.rankingScore = rankingScore;
	}

	double rankingScore;

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
	public int getAuthorInfluenceLevel() {
		return authorInfluenceLevel;
	}
	public void setAuthorInfluenceLevel(int authorInfluenceLevel) {
		this.authorInfluenceLevel = authorInfluenceLevel;
	}
	
	
	
}
