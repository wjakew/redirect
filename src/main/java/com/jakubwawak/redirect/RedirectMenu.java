/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
*/
package com.jakubwawak.redirect;

import com.jakubwawak.redirect.maintanance.ConsoleColors;
import com.jakubwawak.redirect.maintanance.RandomWordGeneratorEngine;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Function for creating menu for changing settings
 */
public class RedirectMenu {

    public ArrayList<String> commandHistory;
    public boolean runFlag;

    /**
     * Constructor
     */
    public RedirectMenu(){
        commandHistory = new ArrayList<>();
        runFlag = true;
    }

    /**
     * Function for decision-making
     * @param user_input
     */
    void mind(String user_input){
        String[] words = user_input.split(" ");
        for( String word : words ){
            switch(word) {
                case "exit": {
                    if (words.length == 1) {
                        System.out.println("Application exiting..");
                        RedirectApplication.logger.closeFile();
                        System.exit(0);
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Wrong command usage, check help!" + ConsoleColors.RESET);
                    }
                    break;
                }
                case "debug": {
                    if (RedirectApplication.printLogFlag == 1) {
                        RedirectApplication.printLogFlag = 0;
                        System.out.println("Debug mode off");
                    } else {
                        RedirectApplication.printLogFlag = 1;
                        System.out.println("Debug mode on");
                    }
                }
                case "cardinfomanager": {
                    if (words.length == 1) {
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Enter header:");
                        String header = scanner.nextLine();
                        System.out.print("Enter email:");
                        String email = scanner.nextLine();
                        System.out.print("Enter phone:");
                        String phone = scanner.nextLine();
                        System.out.print("Enter quote:");
                        String quote = scanner.nextLine();
                        RedirectApplication.database.insertCardInfo(email, phone, quote, header);
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Wrong command usage, check help!" + ConsoleColors.RESET);
                    }
                    break;
                }
                default: {
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "No command called (" + user_input + ") - check help!" + ConsoleColors.RESET);
                    break;
                }
                case "startadminpanel":{
                    RandomWordGeneratorEngine rwge = new RandomWordGeneratorEngine();
                    RedirectApplication.singlePassword = rwge.generateRandomString(30,true,false);
                    System.out.println("Admin panel password: "+RedirectApplication.singlePassword);
                    System.out.println("To open the admin panel go to url: /admin/"+RedirectApplication.singlePassword);
                    break;
                }
                case "stopadminpanel":{
                    RedirectApplication.singlePassword = "";
                    System.out.println("Admin panel stopped");
                    break;
                }
            }
        }
    }

    /**
     * Function for running
     */
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(runFlag){
            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT+"redirect :3 >"+ConsoleColors.RESET);
            String userInput = scanner.nextLine();
            if( !userInput.isBlank() ){
                mind(userInput);
            }
            else{
                System.out.println("Command is empty!");
            }
        }
    }
}
