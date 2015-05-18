package main;

import com.factset.hackathon.beans.CompanyKeywords;
import com.factset.hackathon.reader.ReadCompanyList;
import com.factset.hackathon.utils.*;
import com.factset.hackathon.twitterdata.TwitterDataDownloader;
import com.factset.hackathon.writer.WriteTweetData;
import org.joda.time.LocalDate;

import java.util.ArrayList;


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
        LocalDate start = new LocalDate(2015, 4 , 1);//yyyy,mm,dd
        LocalDate end = new LocalDate(2015, 5, 15);
        for (LocalDate date : new LocalDateRange(start, end))
        {
            wtd.writeData(ck, bp, getDate(date.toString()), getDate(date.plusDays(1).toString()));
        }
    }
    static String getDate(String date)
    {
        String[] dateArr = date.split("-");
        return dateArr[1]+dateArr[2]+dateArr[0];
    }
}
