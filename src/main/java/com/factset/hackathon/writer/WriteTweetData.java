package com.factset.hackathon.writer;

import com.factset.hackathon.beans.CompanyKeywords;
import com.factset.hackathon.twitterdata.TweetDAO;
import com.factset.hackathon.twitterdata.TwitterDataDownloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pludu on 5/15/2015.
 */
public class WriteTweetData {
    private static String fpath;

    private boolean mkdir(String path, String foldername) {
        return (new File(path + foldername)).mkdirs();
    }

    private boolean mkdir(String foldername) {
        return (new File(foldername)).mkdirs();
    }

    public void setPath(String path) {
        fpath = path;
    }

    private long getEpoch(String dates) {
        try {
            String str = dates + " 00:00:00";
            SimpleDateFormat df = new SimpleDateFormat(
                    "MMddyyyy HH:mm:ss");
            Date date = df.parse(str);
            return date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean writeData(ArrayList<CompanyKeywords> ack, String basePath, String startDate, String endDate) {
        if (basePath == null || basePath.equals("")) {
            basePath = "TwitterData";
        }
        if (!basePath.endsWith("/")) basePath += "/";
        for (CompanyKeywords anAck : ack) {
            try {
                mkdir(basePath, anAck.getCompany_name());
                mkdir(basePath, anAck.getCompany_name() + "/complete");
                PrintWriter mainwriter = new PrintWriter(basePath + anAck.getCompany_name() + "/complete/" + startDate + ".csv", "UTF-8");
                for (int j = 0; j < anAck.getKeywords().size(); j++) {
                    List<TweetDAO> td = TwitterDataDownloader.getTweets(anAck.getKeywords().get(j).getKeyword(), String.valueOf(getEpoch(startDate)), String.valueOf(getEpoch(endDate)), anAck.getKeywords().get(j).getMax_tweets());
                    writeTweets(td, anAck.getKeywords().get(j).getKeyword(), startDate, basePath + anAck.getCompany_name(), mainwriter);
                    td.clear();
                }
                mainwriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private void writeTweets(List<TweetDAO> td, String keyword, String date, String path, PrintWriter pw) {
        try {
            PrintWriter writer = new PrintWriter(path + "/" + keyword.replace(" ", "_") + "_" + date + ".csv", "UTF-8");
            for (TweetDAO aTd : td) {
                writer.print(aTd.getDate() + "~" + aTd.getAuthorInfluenceLevel() + "~" + aTd.getRankingScore() + "~");
                writer.println(aTd.getTweet().replace("\n", " ").replace("\r", ""));
                pw.print(aTd.getDate() + "~" + aTd.getAuthorInfluenceLevel() + "~" + aTd.getRankingScore() + "~");
                pw.println(aTd.getTweet().replace("\n", " ").replace("\r", ""));
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
