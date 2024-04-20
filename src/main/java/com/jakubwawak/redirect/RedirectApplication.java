/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all rights reserved
 */
package com.jakubwawak.redirect;

import com.jakubwawak.redirect.maintanance.ConsoleColors;
import com.jakubwawak.redirect.maintanance.JWALog;
import com.jakubwawak.redirect.propertiesParser.Properties;
import com.jakubwawak.redirect.propertiesParser.redirectConfiguration.RedirectConfiguration;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Web application for creating redirection page with styling, blog functionality and project viewing
 */
@SpringBootApplication
@EnableVaadin({"com.jakubwawak"})
@Theme(value="redirectheme")
public class RedirectApplication extends SpringBootServletInitializer implements AppShellConfigurator{

	public static String version = "v1.0.0";
	public static String build = "red200424REV01emmet";

	public static int debug = 0;

	public static JWALog logger;

	public static Properties properties;
	public static RedirectConfiguration redirectConfiguration;

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
						SpringApplication.run(RedirectApplication.class, args);
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

}
