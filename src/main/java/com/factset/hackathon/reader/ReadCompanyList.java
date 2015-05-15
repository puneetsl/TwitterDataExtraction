package com.factset.hackathon.reader;

import com.factset.hackathon.beans.CompanyKeywords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pludu on 5/15/2015.
 */
public class ReadCompanyList {
    private ArrayList<CompanyKeywords> loadList(String path) {
        ArrayList<CompanyKeywords> ack = new ArrayList<CompanyKeywords>();
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            for (String line; (line = br.readLine()) != null; ) {
                if(!line.startsWith("#"))
                {
                    CompanyKeywords ck = new CompanyKeywords();
                    String[] companyInfo= line.split("=");
                    ck.setCompany_name(companyInfo[0]);
                    String[] keywordList = companyInfo[1].split(",");
                    for (int i = 0; i < keywordList.length; i++) {
                        String[] keyword = keywordList[i].split("\\[");
                        String max_tweets = keyword[1].split("\\]")[0];
                        ck.addKeyword(keyword[0].trim(),Integer.parseInt(max_tweets));
                    }
                    ack.add(ck);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ack;
    }
}
