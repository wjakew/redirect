/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all rights reserved
 */
package com.jakubwawak.redirect.maintanance;

import com.jakubwawak.redirect.RedirectApplication;

import java.awt.geom.RectangularShape;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Simple class for grabbing and storing log data from the application.
 * Object saves the log to the .log file
 */
public class JWALog {

    FileWriter logWritter;
    public String currentFileName;
    public ArrayList<String> logCollector;
    public boolean errorFlag;

    /**
     * Constructor
     */
    public JWALog(String applicationName){
        try{
            currentFileName = applicationName+ LocalDateTime.now().toString().replaceAll("-","'").replaceAll(":","")+".log";
            logWritter = new FileWriter(currentFileName);
            logCollector = new ArrayList<>();
            errorFlag = false;
        }catch(Exception ex){
            errorFlag = true;
        }
    }

    /**
     * Function for adding log
     * @param logCategory
     * @param logText
     */
    public void addLog(String logCategory, String logText){
        try{
            String log = logCategory+"-"+LocalDateTime.now().toString()+"-"+logText;
            logCollector.add(log);
            logWritter.write(log+"\n");
            if (RedirectApplication.printLogFlag == 1 )
                System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+LocalDateTime.now()+" - "+log+ConsoleColors.RESET);
        }catch(Exception ex){
            errorFlag = true;
        }
    }

    /**
     * Function for closing file
     */
    public void closeFile(){
        try{
            logWritter.close();
        }catch(Exception ex){
            errorFlag = true;
        }

    }
}
