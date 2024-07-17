/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
*/
package com.jakubwawak.redirect.database.entity;

import java.sql.ResultSet;

/**
 * Object for storing blog data
 */
public class Blog {

    public String blog_title;
    public String blog_raw_path;
    public String blog_time;
    public String blog_creator_name;

    /**
     * Constructor
     */
    public Blog(){
        blog_title = "";
        blog_raw_path = "";
        blog_time = "";
        blog_creator_name = "";
    }

    /**
     * Constructor with database support
     * @param toadd
     */
    public Blog(ResultSet toadd){
        try{
            blog_title = toadd.getString("blog_title");
            blog_raw_path = toadd.getString("blog_raw_path");
            blog_time = toadd.getString("blog_time");
            blog_creator_name = toadd.getString("blog_creator_name");
        }catch(Exception e){
            blog_title = "";
            blog_raw_path = "";
            blog_time = "";
            blog_creator_name = "";
        }
    }

}
