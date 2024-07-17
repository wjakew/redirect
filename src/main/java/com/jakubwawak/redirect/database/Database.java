/**
 * by Jakub Wawak
 * all rights reserved
 * kubawawak@gmail.com
 */
package com.jakubwawak.redirect.database;

import com.jakubwawak.redirect.RedirectApplication;
import com.jakubwawak.redirect.maintanance.RandomWordGeneratorEngine;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class for managing the SQLite database connection
 */
public class Database {
    private Connection connection;
    public boolean connected;

    /**
     * Constructor
     * @param filePath
     */
    public Database(String filePath) {
        connected = false;
        try {
            // Load the SQLite JDBC driver (if necessary)
            Class.forName("org.sqlite.JDBC");
            // Establish a connection to the database (or create it if not exists)
            String url = "jdbc:sqlite:" + filePath;
            connection = DriverManager.getConnection(url);
            createTables();
            System.out.println("Connection to SQLite has been established.");
            // Additional setup like checking if tables exist or creating them can be added here
            connected = true;
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection to SQLite database failed.");
            e.printStackTrace();
        }
    }

    /**
     * Method for creating the tables in the database
     */
    public void createTables() {
        createCardInfoTable();
        createBlogManagerTable();
    }

    /**
     * Method for creating card info table
     */
    private void createCardInfoTable(){
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS personal_card_info (" +
                "email TEXT PRIMARY KEY," +
                "phone TEXT NOT NULL," +
                "quote TEXT NOT NULL," +
                "header TEXT NOT NULL);";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreateTable);
            System.out.println("Tables created/verified successfully.");
            RedirectApplication.logger.addLog("DB-TABLE-CREATION", "Tables created/verified successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create/verify tables in the database.");
            RedirectApplication.logger.addLog("DB-TABLE-CREATION-FAILED", "Failed to create/verify tables in the database (" + e.toString() + ")");
            e.printStackTrace();
        }
    }

    /**
     * Method for creating blog manager data table
     */
    private void createBlogManagerTable(){
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS blog_manager_data (" +
                "blog_manager_key TEXT NOT NULL);";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreateTable);
            System.out.println("Tables created/verified successfully.");
            RedirectApplication.logger.addLog("DB-TABLE-CREATION", "Tables created/verified successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create/verify tables in the database.");
            RedirectApplication.logger.addLog("DB-TABLE-CREATION-FAILED", "Failed to create/verify tables in the database (" + e.toString() + ")");
            e.printStackTrace();
        }
    }

    /**
     * Method for closing the connection to the database
     */
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection to SQLite has been closed.");
                connected = false;
            }
        } catch (SQLException e) {
            System.out.println("Failed to close the connection.");
            e.printStackTrace();
        }
    }

    // Additional methods to interact with the database can be added here

    /**
     * Function for inserting card info into the database
     * @param email
     * @param phone
     * @param quote
     * @param header
     * @return Integer
     */
    public int insertCardInfo(String email, String phone, String quote, String header){
        String query = "DELETE FROM personal_card_info;";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            RedirectApplication.logger.addLog("DB-CARD-DELETE-ALL", "All card info deleted from the database");
        } catch (SQLException e) {
            RedirectApplication.logger.addLog("DB-CARD-DELETE-ALL-FAILED", "Failed to delete all card info from the database (" + e.toString() + ")");
            e.printStackTrace();
        }
        query = "INSERT OR REPLACE INTO personal_card_info (email, phone, quote, header) VALUES (?,?,?,?);";
        try {
            PreparedStatement ppst = connection.prepareStatement(query);
            ppst.setString(1,email);
            ppst.setString(2,phone);
            ppst.setString(3,quote);
            ppst.setString(4,header);
            ppst.execute();
            RedirectApplication.logger.addLog("DB-CARD-UPDATE","Updating card info in the database");
            return 1;
        } catch (SQLException e) {
            RedirectApplication.logger.addLog("DB-CARD-UPDATE-FAILED","Failed to update card info in the database ("+e.toString()+")");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Function for getting card info from the database
     * @return ArrayList
     */
    public ArrayList<String> getCardInfo() {
        ArrayList<String> data = new ArrayList<>();
        String query = "SELECT * FROM personal_card_info;";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data.add(rs.getString("email"));
                data.add(rs.getString("phone"));
                data.add(rs.getString("quote"));
                data.add(rs.getString("header"));
            }
            RedirectApplication.logger.addLog("DB-CARD-GET", "Getting card info from the database");
        } catch (SQLException e) {
            RedirectApplication.logger.addLog("DB-CARD-GET-FAILED", "Failed to get card info from the database (" + e.toString() + ")");
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Function for enabling blog manager in the database
     * @return String
     */
    public String enableBlogManager(){
        String query = "DELETE FROM BLOG_MANAGER_DATA;";
        try{
            Statement stmt = connection.createStatement();
            stmt.execute(query);
            RedirectApplication.logger.addLog("DB-BLOG-MANAGER-ENABLE","Enabling blog manager in the database");
            RandomWordGeneratorEngine rwge = new RandomWordGeneratorEngine();
            query = "INSERT INTO BLOG_MANAGER_DATA (blog_manager_key) VALUES (?);";
            PreparedStatement ppst = connection.prepareStatement(query);
            String key = rwge.generateRandomString(50,true,false);
            ppst.setString(1,key);
            ppst.execute();
            return key;
        } catch (SQLException e){
            RedirectApplication.logger.addLog("DB-BLOG-MANAGER-ENABLE-FAILED","Failed to enable blog manager in the database ("+e.toString()+")");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Function for verifying blog manager key in the database
     * @param blogKeyProvided
     * @return boolean
     */
    public boolean verifyBlogKey(String blogKeyProvided){
        String query = "SELECT * FROM BLOG_MANAGER_DATA;";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String blogKey = rs.getString("blog_manager_key");
                if(blogKey.equals(blogKeyProvided)){
                    RedirectApplication.logger.addLog("DB-BLOG-MANAGER-VERIFY", "Blog manager key verified");
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            RedirectApplication.logger.addLog("DB-BLOG-MANAGER-VERIFY-FAILED", "Failed to verify blog manager key (" + e.toString() + ")");
            e.printStackTrace();
            return false;
    }
}

    /**
     * Function for clearing blog manager in the database
     */
    public void clearBlogManager(){
        String query = "DELETE FROM BLOG_MANAGER_DATA;";
        try{
            Statement stmt = connection.createStatement();
            stmt.execute(query);
            RedirectApplication.logger.addLog("DB-BLOG-MANAGER-CLEAR","Clearing blog manager in the database");
        } catch (SQLException e){
            RedirectApplication.logger.addLog("DB-BLOG-MANAGER-CLEAR-FAILED","Failed to clear blog manager in the database ("+e.toString()+")");
            e.printStackTrace();
        }
    }

}