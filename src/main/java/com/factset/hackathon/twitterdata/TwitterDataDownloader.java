package com.factset.hackathon.twitterdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TwitterDataDownloader {

	// final static String STRING_URL =

	// http://api.topsy.com/v2/content/tweets.json?q=obama&mintime=1370217600&maxtime=1370893329
	// &include_metrics=1&apikey=09C43A9B270A470B8EB8F2946A9369F3&mintime=1430445614&maxtime=1430578840&perpage=1

	// http://api.topsy.com/v2/content/tweets.json?q=obama&mintime=1370217600&maxtime=1370893329
	// &include_metrics=1&apikey=09C43A9B270A470B8EB8F2946A9369F3&perpage=1000

	/**
	 * q mintime maxtime include_metrics apikey perpage
	 */
	final static String STRING_URL = "http://api.topsy.com/v2/content/tweets.json";

	final static int PERPAGE_MAX = 1000;
	
	public static String apiKey;

	public static void setApiKey(String apiKey) {
		TwitterDataDownloader.apiKey = apiKey;
	}

	public static void main(String[] args) {

		setApiKey("09C43A9B270A470B8EB8F2946A9369F3");
		getTweets("google", "1370217600", "1430578840", 20);
	}

	public static List<TweetDAO> getTweets(String company, String startTime,
			String endTime, int maxTweetCount) {
		List<TweetDAO> totalTweets = new ArrayList<TweetDAO>();
		System.out.println("Params to get tweets: "+ company+"\n"+startTime+"\n"+endTime+"\n"+maxTweetCount);
		boolean moreResults=false;
		int offset = 0;
		int perPage =0;
		if (maxTweetCount > PERPAGE_MAX)
			perPage = PERPAGE_MAX;
		else
			perPage = maxTweetCount;
		int countTweetsRetrieved = 0;
		URL url;
		do {
			try {
				url = new URL(buildUrl(STRING_URL, company, startTime,
						endTime, offset,maxTweetCount));
				URL obj = new URL(url.toString());
				HttpURLConnection con = (HttpURLConnection) obj
						.openConnection();
				// optional default is GET
				con.setRequestMethod("GET");

				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				List<TweetDAO> temp = parseTweetsFromJSON(response.toString());
				totalTweets.addAll(temp);
				countTweetsRetrieved+= temp.size();
				offset = countTweetsRetrieved+1;
				
				if ((maxTweetCount - countTweetsRetrieved)> PERPAGE_MAX)
					perPage = PERPAGE_MAX;
				else
					perPage = maxTweetCount - countTweetsRetrieved;
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (moreResults && countTweetsRetrieved < maxTweetCount );
		return totalTweets;
	}

	public static String buildUrl(String url, String company, String startTime,
			String endTime, int offset,int count) {
		if (!url.endsWith("?"))
			url += "?";

		List<NameValuePair> params = new LinkedList<NameValuePair>();

		params.add(new BasicNameValuePair("q", String.valueOf(company)));
		params.add(new BasicNameValuePair("mintime", String.valueOf(startTime)));
		params.add(new BasicNameValuePair("maxtime", String.valueOf(endTime)));
		params.add(new BasicNameValuePair("include_metrics", String.valueOf("1")));
		params.add(new BasicNameValuePair("apikey", String.valueOf(apiKey)));
		params.add(new BasicNameValuePair("offset", String.valueOf(offset)));
		params.add(new BasicNameValuePair("perpage", String.valueOf(count)));

		String paramString = URLEncodedUtils.format(params, "utf-8");

		url += paramString;
		return url;

	}
	
	public static List<TweetDAO> parseTweetsFromJSON(String jsonString)
	{

		List<TweetDAO> tweets = new ArrayList<TweetDAO>();
		
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray listOfTweets = jsonObject.getJSONObject("response").getJSONObject("results").getJSONArray("list");
			for (int i = 0; i < listOfTweets.length(); ++i) {
				TweetDAO tweet = new TweetDAO();
			    JSONObject rec = listOfTweets.getJSONObject(i);
			    tweet.setTweet(rec.getJSONObject("tweet").getString("text"));
			    tweet.setDate(rec.getJSONObject("tweet").getString("created_at"));
			    tweet.setFirstPostDate(rec.getInt("firstpost_date"));
				if(rec.getJSONObject("author").has("influence_level"))
			    	tweet.setAuthorInfluenceLevel(rec.getJSONObject("author").getInt("influence_level"));
				if(rec.getJSONObject("metrics").has("ranking_score"))
					tweet.setRankingScore(rec.getJSONObject("metrics").getDouble("ranking_score"));
			    tweets.add(tweet);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tweets;
	}

}
