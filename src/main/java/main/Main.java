package main;

import com.factset.hackathon.beans.CompanyKeywords;
import com.factset.hackathon.reader.ReadCompanyList;
import com.factset.hackathon.twitterdata.TwitterDataDownloader;

import java.util.ArrayList;


/**
 * Created by puneetsingh on 5/15/15.
 */
public class Main {


    public static void main(String[] args) {
        TwitterDataDownloader.setApiKey("09C43A9B270A470B8EB8F2946A9369F3");
        ReadCompanyList rcl = new ReadCompanyList();
        String bp = "C:\\Users\\pludu\\Desktop\\dev\\TwitterDataExtraction\\TwitterData";
        ArrayList<CompanyKeywords> ck = rcl.loadList("C:\\Users\\pludu\\Desktop\\dev\\TwitterDataExtraction\\LookupData\\CompanyList.txt");
    }
}
