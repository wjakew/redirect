/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
*/
package com.jakubwawak.redirect;

import com.jakubwawak.redirect.maintanance.ConsoleColors;

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
            switch(word){
                case "exit":
                {
                    if ( words.length == 1 ){
                        System.out.println("Application exiting..");
                        RedirectApplication.logger.closeFile();
                        System.exit(0);
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"Wrong command usage, check help!"+ConsoleColors.RESET);
                    }
                    break;
                }
                //TODO create basic logic
                default:
                {
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT+"No command called ("+user_input+") - check help!" + ConsoleColors.RESET);
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
