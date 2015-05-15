package main;

import com.factset.hackathon.twitterdata.TweetDAO;
import com.factset.hackathon.twitterdata.TwitterDataDownloader;


import java.util.List;

/**
 * Created by puneetsingh on 5/15/15.
 */
public class Main {


    public static void main(String[] args) {
        TwitterDataDownloader.setApiKey("09C43A9B270A470B8EB8F2946A9369F3");
        List<TweetDAO> td =  TwitterDataDownloader.getTweets("puneet", "1370217600", "1430578840", 8000);
        for (int i = 0; i < td.size(); i++) {
            System.out.println(i+"\t\t"+td.get(i).getAuthorInfluenceLevel());
        }
    }
}
