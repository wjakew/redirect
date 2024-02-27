/**
 * by Jakub Wawak
 * all rights reserved
 * kubawawak@gmail.com
 */
package com.jakubwawak.redirect.propertiesParser.redirectConfiguration;

import com.jakubwawak.redirect.RedirectApplication;
import com.jakubwawak.redirect.propertiesParser.Properties;

import java.lang.reflect.Field;

/**
 * Object for storing application configuration
 */
public class RedirectConfiguration {
    public String pageTitleHeader;
    public String pageTitleDesc;
    public String pageTitleOwner;
    public String iconPath;
    public  String projectsFlag;
    public String blogFlag;

    // btn 1
    public String redirectBtnEnableFlag1;
    public String redirectBtnText1;
    public String redirectBtnUrl1;

    // btn 2
    public String redirectBtnEnableFlag2;
    public String redirectBtnText2;
    public String redirectBtnUrl2;

    // btn 3
    public String redirectBtnEnableFlag3;
    public String redirectBtnText3;
    public String redirectBtnUrl3;

    // database connector

    public String databaseIP;
    public String databaseName;
    public String databaseUser;
    public String databasePassword;

    // styling values
    public String mainPageBackgroundStyle,mainPageFontColor,mainPageFontStyle,mainPageButtonStyle;


    /**
     * Constructor
     * @param properties
     */
    public RedirectConfiguration(Properties properties){
        pageTitleHeader = properties.getValue("pagetitleHeader");
        pageTitleDesc = properties.getValue("pagetitleDesc");
        pageTitleOwner = properties.getValue("pagetitleOwner");
        iconPath = properties.getValue("iconPath");
        projectsFlag = properties.getValue("projectsFlag");
        blogFlag = properties.getValue("blogFlag");

        redirectBtnEnableFlag1 = properties.getValue("redirectBtnEnableFlag1");
        redirectBtnText1 = properties.getValue("redirectBtnText1");
        redirectBtnUrl1 = properties.getValue("redirectBtnUrl1");

        redirectBtnEnableFlag2 = properties.getValue("redirectBtnEnableFlag2");
        redirectBtnText2 = properties.getValue("redirectBtnText2");
        redirectBtnUrl2 = properties.getValue("redirectBtnUrl2");

        redirectBtnEnableFlag3 = properties.getValue("redirectBtnEnableFlag3");
        redirectBtnText3 = properties.getValue("redirectBtnText3");
        redirectBtnUrl3 = properties.getValue("redirectBtnUrl3");

        databaseIP = properties.getValue("databaseIP");
        databaseName = properties.getValue("databaseName");
        databaseUser = properties.getValue("databaseUser");
        databasePassword = properties.getValue("databasePassword");

        mainPageBackgroundStyle = properties.getValue("mainPageBackgroundStyle");
        mainPageFontColor = properties.getValue("mainPageFontColor");
        mainPageFontStyle = properties.getValue("mainPageFontStyle");
        mainPageButtonStyle = properties.getValue("mainPageButtonStyle");
    }

    public String getPageTitleHeader() {
        if ( !pageTitleHeader.equals("")){
            return pageTitleHeader;
        }
        return "template_title";
    }

    public String getPageTitleDesc() {
        if ( !pageTitleDesc.equals("")){
            return pageTitleDesc;
        }
        return  "template_desc";
    }

    public String getPageTitleOwner() {
        if ( !pageTitleOwner.equals("")) {
            return pageTitleOwner;
        }
        return "redirect_admin";
    }

    public String getIconPath() {
        if ( !iconPath.equals("")) {
            return iconPath;
        }
        return "images/redirect_icon.png";
    }

    public String getProjectsFlag() {
        if ( !projectsFlag.equals("")) {
            return projectsFlag;
        }
        return "0";
    }

    public String getBlogFlag() {
        if ( !blogFlag.equals("") ) {
            return blogFlag;
        }
        return "0";
    }

    public String getRedirectBtnEnableFlag1() {
        if ( !redirectBtnEnableFlag1.equals("")) {
            return redirectBtnEnableFlag1;
        }
        return "0";
    }

    public String getRedirectBtnText1() {
        if ( !redirectBtnText1.equals("")) {
            return redirectBtnText1;
        }
        return "text";
    }

    public String getRedirectBtnUrl1() {
        if ( !redirectBtnUrl1.equals("")) {
            return redirectBtnUrl1;
        }
        return "https://github.com/wjakew";
    }

    public String getRedirectBtnEnableFlag2() {
        if (!redirectBtnEnableFlag2.equals("")){
            return redirectBtnEnableFlag2;
        }
        return "0";
    }

    public String getRedirectBtnText2() {
        if ( !redirectBtnText2.equals("")) {
            return redirectBtnText2;
        }
        return "text";
    }

    public String getRedirectBtnUrl2() {
        if ( !redirectBtnUrl2.equals("")) {
            return redirectBtnUrl2;
        }
        return "https://github.com/wjakew";
    }

    public String getRedirectBtnEnableFlag3() {
        if (!redirectBtnEnableFlag3.equals("")){
            return redirectBtnEnableFlag3;
        }
        return "0";
    }

    public String getRedirectBtnText3() {
        if ( !redirectBtnText3.equals("")) {
            return redirectBtnText3;
        }
        return "text";
    }

    public String getRedirectBtnUrl3() {
        if ( !redirectBtnUrl3.equals("")) {
            return redirectBtnUrl3;
        }
        return "https://github.com/wjakew";
    }

    public String getDatabaseIP() {
        if ( !databaseIP.equals(""))
            return databaseIP;
        else
            return "localhost";
    }

    public String getDatabaseName() {
        if ( !databaseName.equals(""))
            return databaseName;
        else
            return "redirect_database";
    }

    public String getDatabaseUser() {
        if ( !databaseUser.equals(""))
            return databaseUser;
        else
            return "root";
    }

    public String getDatabasePassword() {
        if ( !databasePassword.equals(""))
            return databasePassword;
        else
            return "password";
    }

    public String getMainPageBackgroundStyle() {
        if ( !mainPageBackgroundStyle.equals(""))
            return mainPageBackgroundStyle;
        else
            return "radial-gradient(blue,black)";
    }

    public String getMainPageFontColor() {
        if ( !mainPageFontColor.equals(""))
            return mainPageFontColor;
        else
            return "#000000";
    }

    public String getMainPageFontStyle() {
        if ( !mainPageFontStyle.equals(""))
            return mainPageFontStyle;
        else
            return "Monospace";
    }

    public String getMainPageButtonStyle() {
        if ( !mainPageButtonStyle.equals(""))
            return mainPageButtonStyle;
        else
            return "#FFFFFF";
    }

    /**
     * Function for getting contrast color to given one
     * @param hexColor
     * @return
     */
    public String getContrastColor(String hexColor) {
        // Remove the "#" symbol from the hex code if present
        hexColor = hexColor.replace("#","");

        // Check if the hex code is valid (6 characters)
        if (hexColor.length() != 6) {
            throw new IllegalArgumentException("Invalid hex color code. Please provide a 6-character hex code.");
        }

        // Convert the hex code to RGB values
        int r = Integer.parseInt(hexColor.substring(0, 2), 16);
        int g = Integer.parseInt(hexColor.substring(2, 4), 16);
        int b = Integer.parseInt(hexColor.substring(4, 6), 16);

        // Calculate the relative luminance
        double y = (0.2126 * r + 0.7152 * g + 0.0722 * b) / 255;

        // Determine the contrasting color based on relative luminance
        return (y > 0.5) ? "#000000" : "#FFFFFF";
    }
}
