/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all rights reserved
 */
package com.jakubwawak.redirect.webComponents.windows;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;

/**
 * Window for logging user to the app
 */
public class PasswordWindow {

    // variables for setting x and y of window
    public String width = "50%";
    public String height = "50%";

    // main login components
    public Dialog main_dialog;
    VerticalLayout main_layout;

    PasswordField password_field;
    Button login_button;

    /**
     * Constructor
     */
    public PasswordWindow(){
        main_dialog = new Dialog();
        main_layout = new VerticalLayout();
        main_dialog.addClassName("window");
        prepare_dialog();
    }

    /**
     * Function for preparing components
     */
    void prepare_components(){
        // set components
        password_field = new PasswordField("Admin Password");
        password_field.addClassName("input");
        password_field.setWidthFull();
        login_button = new Button("Login");
        login_button.getStyle().set("color","green");
    }

    /**
     * Function for preparing layout
     */
    void prepare_dialog(){
        prepare_components();
        // set layout

        main_layout.add(new H6("Admin Panel Locked"));
        main_layout.add(password_field);
        main_layout.add(login_button);

        main_layout.setSizeFull();
        main_layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        main_layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        main_layout.getStyle().set("text-align", "center");

        main_dialog.add(main_layout);
        main_dialog.setWidth(width);main_dialog.setHeight(height);
    }
}