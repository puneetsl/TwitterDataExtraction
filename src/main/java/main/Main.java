package main;

import com.factset.hackathon.twitterdata.TwitterDataDownloader;

/**
 * Created by puneetsingh on 5/15/15.
 */
public class Main {


    public static void main(String[] args) {
        TwitterDataDownloader.setApiKey("09C43A9B270A470B8EB8F2946A9369F3");
        TwitterDataDownloader.getTweets("google", "1370217600", "1430578840", 20);
    }
}
