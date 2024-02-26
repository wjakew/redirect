/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all rights reserved
 */
package com.jakubwawak.redirect;

import com.jakubwawak.redirect.propertiesParser.Properties;
import com.jakubwawak.redirect.propertiesParser.PropertiesField;

/**
 * Object for testing
 */
public class RedirectApplicationTests {

    /**
     * Constructor
     */
    public RedirectApplicationTests(){
        System.out.println("Running tests...");
        runTests();
    }

    /**
     * Function for running tests
     */
    public void runTests(){
        Properties properties = new Properties("redirect.properties");
        properties.createPropertiesFile();
    }
}
