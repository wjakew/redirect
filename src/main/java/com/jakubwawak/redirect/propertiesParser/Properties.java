/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all rights reserved
 */
package com.jakubwawak.redirect.propertiesParser;

import com.jakubwawak.redirect.RedirectApplication;
import com.jakubwawak.redirect.RedirectApplicationTests;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Function for managing and creating properties file
 */
public class Properties {

    int INTEGRITY_CHECK_FLAG = 19;
    String EXPECTED_FILEVERSION = "1";

    String propertiesFile;

    public boolean error;
    public boolean integrityFlag;
    public boolean fileVersionFlag;
    public String propertiesFileVersion;

    ArrayList<PropertiesField> propertiesCollection;
    public boolean fileExists;


    /**
     * Constructor
     * @param propertiesFile
     */
    public Properties(String propertiesFile){
        this.propertiesFile = propertiesFile;
        propertiesCollection = new ArrayList<>();
        error = false;

        // flag for checking
        File checkFile = new File(propertiesFile);
        fileExists = checkFile.exists();
    }

    /**
     * Function for getting properties value from collection by key given
     * @param key
     * @return String
     */
    public String getValue(String key){
        for( PropertiesField pf : propertiesCollection ){
            if ( pf.propertiesKey.equals(key)){
                return pf.propertiesValue;
            }
        }
        return null;
    }

    /**
     * Function for parsing properties file
     */
    public void parsePropertiesFile(){
        File configurationFile = new File(propertiesFile);
        if ( configurationFile.exists() ){
            try{
                Scanner propertiesScanner = new Scanner(configurationFile);
                while (propertiesScanner.hasNextLine()) {
                    String line = propertiesScanner.nextLine();

                    if ( line.startsWith("$") ){
                        // getting file version
                        if ( line.contains("$fileVersion") ){
                            propertiesFileVersion = valueOfKey(line);
                            fileVersionFlag = propertiesFileVersion.equals(EXPECTED_FILEVERSION);
                        }
                        else{
                            propertiesCollection.add(new PropertiesField(line.split("=")[0].replaceAll("$",""),valueOfKey(line)));
                        }
                    }
                }
                RedirectApplication.logger.addLog("PROPERTIES-PARSER","Loaded "+propertiesCollection.size()+" properties from configuration file.");
                integrityFlag = propertiesCollection.size() == INTEGRITY_CHECK_FLAG;
                propertiesScanner.close();
            }catch(Exception ex){
                RedirectApplication.logger.addLog("PROPERTIES-PARSER-FAILED","Failed to pars");
            }

        }
    }

    /**
     * Function for retriving everyting after the "=" in string
     * @param content
     * @return String
     */
    String valueOfKey(String content){
        // Find the index of the "=" sign.
        int indexEquals = content.indexOf('=');

        // If the "=" sign is not found, return null.
        if (indexEquals == -1) {
            return null;
        }
        // Return the substring after the "=" sign.
        return content.substring(indexEquals + 1);
    }

    /**
     * Function for creating properties file
     */
    public void createPropertiesFile(){
        try{
            FileWriter writer = new FileWriter(propertiesFile);
            writer.write("#redirect properties file\n");
            writer.write("$fileVersion=1\n");
            writer.write("#by Jakub Wawak / all rights reserved / kubawawak@gmail.com\n");
            writer.write("#do not change this file! to reload to default file use command in the app 'clear-properties'\n");
            writer.write("#to reload content based on this file use 'reload-properties'\n");
            writer.write("#-------------------------------------------------------------\n");
            writer.write("# main page information\n");
            writer.write("# pagetitleHeader stores page name, max 250 signs\n");
            writer.write("$pagetitleHeader=\n");
            writer.write("# pagetitleDesc stores description of the page for the main page, max 500 signs\n");
            writer.write("$pagetitleDesc=\n");
            writer.write("# pagetitleOwner stores information about the page owner\n");
            writer.write("$pagetitleOwner=\n");
            writer.write("# iconPath stores path to the website icon\n");
            writer.write("$iconPath=\n");
            writer.write("#-------------------------------------------------------------\n");
            writer.write("# feature flags\n");
            writer.write("# 0 - disabled, 1 - enabled\n");
            writer.write("$projectsFlag=0\n");
            writer.write("$blogFlag=0\n");
            writer.write("#-------------------------------------------------------------\n");
            writer.write("# redirect buttons\n");
            writer.write("# app can show three main redirect buttons, they can redirect your viewers to other sites or social media\n");
            writer.write("# set the enableFlag to 1 to every button you want to show\n");
            writer.write("#-----btn1\n");
            writer.write("$redirectBtnEnableFlag1=\n");
            writer.write("$redirectBtnText1=\n");
            writer.write("$redirectBtnUrl1=\n");
            writer.write("#-----btn2\n");
            writer.write("$redirectBtnEnableFlag2=\n");
            writer.write("$redirectBtnText2=\n");
            writer.write("$redirectBtnUrl2=\n");
            writer.write("#-----btn3\n");
            writer.write("$redirectBtnEnableFlag=\n");
            writer.write("$redirectBtnText3=\n");
            writer.write("$redirectBtnUrl3=\n");
            writer.write("#-------------------------------------------------------------\n");
            writer.write("# database support\n");
            writer.write("# database connection information for storing project or blog data\n");
            writer.write("# app supports MySQL or MariDB database\n");
            writer.write("$databaseIP=\n");
            writer.write("$databaseName=\n");
            writer.write("$databaseUser=\n");
            writer.write("$databasePassword=\n");
            writer.write("#-------------------------------------------------------------\n");
            writer.write("# styling data\n");
            writer.write("# values for changing styles on the website\n");
            writer.write("$mainPageBackgroundStyle=\n");
            writer.write("$mainPageFontColor=\n");
            writer.write("$mainPageFontStyle=\n");
            writer.write("$mainPageButtonStyle=\n");
            writer.close();
        }catch(Exception ex){
            error = true;
            RedirectApplication.logger.addLog("PROPERTIES","Failed to create .properties file ("+ex.toString()+")");
        }
    }
}
