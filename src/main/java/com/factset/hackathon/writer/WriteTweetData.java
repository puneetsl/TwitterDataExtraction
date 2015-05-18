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
    private boolean mkdir(String path,String foldername)
    {
        return (new File(path+foldername)).mkdirs();
    }
    private boolean mkdir(String foldername)
    {
        return (new File(foldername)).mkdirs();
    }
    public void setPath(String path)
    {
        fpath = path;
    }
    private long getEpoch(String dates){
        try {
            String str = dates + " 00:00:00 EST";
            SimpleDateFormat df = new SimpleDateFormat(
                    "MMDDYYYY HH:mm:ss zzz");
            Date date = df.parse(str);
            return date.getTime();
        }
        catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public boolean writeData(ArrayList<CompanyKeywords> ack,String basePath,String startDate,String endDate)
    {


        if(basePath==null||basePath.equals(""))
        {
            basePath="TwitterData";
        }
        if(!basePath.endsWith("/")) basePath+="/";
        for (int i = 0; i < ack.size(); i++) {
            mkdir(basePath,ack.get(i).getCompany_name());
            for (int j = 0; j < ack.get(i).getKeywords().size(); j++) {
                List<TweetDAO> td =  TwitterDataDownloader.getTweets(ack.get(i).getKeywords().get(j).getKeyword(), String.valueOf(getEpoch(startDate)), String.valueOf(getEpoch(startDate)), ack.get(i).getKeywords().get(j).getMax_tweets());
                writeTweets(td,ack.get(i).getKeywords().get(j).getKeyword(),startDate,basePath+ack.get(i).getCompany_name());
                td.clear();
            }

        }
        return true;
    }

    private void writeTweets(List<TweetDAO> td, String keyword,String date, String path) {
        try{
            PrintWriter writer = new PrintWriter(path+"/"+keyword.replace(" ","_")+"_"+date+".csv", "UTF-8");
            for (int i = 0; i < td.size(); i++) {
                writer.print(td.get(i).getDate() + "~" + td.get(i).getAuthorInfluenceLevel() + "~"+ td.get(i).getRankingScore() + "~");
                writer.println(td.get(i).getTweet().replace("\n", " ").replace("\r", ""));
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
