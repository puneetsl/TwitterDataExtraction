package main;

import com.factset.hackathon.beans.CompanyKeywords;
import com.factset.hackathon.reader.ReadCompanyList;
import com.factset.hackathon.twitterdata.TweetDAO;
import com.factset.hackathon.twitterdata.TwitterDataDownloader;
import com.factset.hackathon.writer.WriteTweetData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pludu on 5/15/2015.
 */
public class GetDataMain {
    public static void main(String[] args) {
        TwitterDataDownloader.setApiKey("09C43A9B270A470B8EB8F2946A9369F3");
        WriteTweetData wtd = new WriteTweetData();
        ReadCompanyList rcl = new ReadCompanyList();
        String bp = "TwitterData";
        wtd.setPath(bp);
        ArrayList<CompanyKeywords> ck = rcl.loadList("LookupData/CompanyList.txt");
        for (int i = 1; i < 10; i++) {
            wtd.writeData(ck,bp,"05"+getDate(i)+"2015","05"+getDate(i+1)+"2015");
        }

    }
    static String getDate(int i)
    {
        String date;
        if(i<10)
            date="0"+i;
        else
            date=String.valueOf(i);
        return date;
    }
}
