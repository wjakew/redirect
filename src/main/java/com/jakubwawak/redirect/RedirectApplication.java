/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all rights reserved
 */
package com.jakubwawak.redirect;

import com.jakubwawak.redirect.database.Database;
import com.jakubwawak.redirect.maintanance.ConsoleColors;
import com.jakubwawak.redirect.maintanance.JWALog;
import com.jakubwawak.redirect.maintanance.RandomWordGeneratorEngine;
import com.jakubwawak.redirect.propertiesParser.Properties;
import com.jakubwawak.redirect.propertiesParser.redirectConfiguration.RedirectConfiguration;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Web application for creating redirection page with styling, blog functionality and project viewing
 */
@SpringBootApplication
@EnableVaadin({"com.jakubwawak"})
@Theme(value="redirectheme")
public class RedirectApplication extends SpringBootServletInitializer implements AppShellConfigurator{

	public static String version = "v1.0.0";
	public static String build = "red020924REV01";

	public static String singlePassword;

	public static int debug = 0;

	public static JWALog logger;
	public static int printLogFlag = 1;

	public static Properties properties;
	public static RedirectConfiguration redirectConfiguration;

	public static RedirectMenu menu;

	public static Database database;

	/**
	 * Main application function
	 * @param args
	 */
	public static void main(String[] args) {
		showHeader();

		logger = new JWALog("redirect");
		if ( args.length == 0 ){
			// no arguments - start the server normally
			logger.addLog("SERVICE-START","Log Init, welcome to JWALog");
			if ( debug == 1 ){
				RedirectApplicationTests rat = new RedirectApplicationTests();
				rat.runTests();
				System.exit(0);
			}
			else{
				properties = new Properties("redirect.properties");
				if (properties.fileExists){ // check if properties file exists
					properties.parsePropertiesFile(); // parse the file
					if ( properties.integrityFlag ){
						// file integrity correct
						logger.addLog("INTEGRITY","File integrity correct!");
						redirectConfiguration = new RedirectConfiguration(properties);
						// run the server + frontend
						logger.addLog("INTEGRITY","Properties loaded correctly!");
						menu = new RedirectMenu();
						if ( redirectConfiguration.databasePath.isBlank()){
							redirectConfiguration.databasePath = "redirect.db";
						}
						database = new Database(redirectConfiguration.databasePath);
						if (database.connected) {
							logger.addLog("DATABASE-CONNECTED", "Database connected correctly");
							createDirectoriesIfNotExist(); // create directories for raw data
							SpringApplication.run(RedirectApplication.class, args);
							menu.run();
						}
					}
					else{
						logger.addLog("INTEGRITY-FAILED","Failed to check integrity of the file!");
					}
				}
				else {
					properties.createPropertiesFile();
					logger.addLog("PROPERTIES-CREATED","Blank configuration created, fill the configuration file and restart the application");
				}
			}
		}
		else{
			// TODO create a menu section for creating or changing the .properties file
		}

	}

	/**
	 * Function for showing header on start
	 */
	public static void showHeader(){
		String header = "              _ _               _   \n" +
				" _ __ ___  __| (_)_ __ ___  ___| |_ \n" +
				"| '__/ _ \\/ _` | | '__/ _ \\/ __| __|\n" +
				"| | |  __/ (_| | | | |  __/ (__| |_ \n" +
				"|_|  \\___|\\__,_|_|_|  \\___|\\___|\\__|\n" +
				version+"/"+build+"/by Jakub Wawak" +"\n";
		System.out.println(ConsoleColors.GREEN_BOLD+header+ConsoleColors.RESET);
	}

	/**
	 * Function for creating directories for raw data of project and blog posts if not exist

	 */
	public static void createDirectoriesIfNotExist() {
		Path projectsRawPath = Paths.get("projects_raw");
		Path blogRawPath = Paths.get("blog_raw");

		try {
			if (!Files.exists(projectsRawPath)) {
				Files.createDirectory(projectsRawPath);
				System.out.println("Created directory: projects_raw");
			}

			if (!Files.exists(blogRawPath)) {
				Files.createDirectory(blogRawPath);
				System.out.println("Created directory: blog_raw");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
