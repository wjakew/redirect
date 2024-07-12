/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
*/
package com.jakubwawak.redirect.webComponents;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Main application web view
 */
@PageTitle("redirect by Jakub Wawak")
@Route("template")
public class TemplateView extends VerticalLayout {


    /**
     * Constructor
     */
    public TemplateView(){
        addClassName("homeview");
        prepareLayout();
    }

    /**
     * Function for preparing components
     */
    void prepareComponents(){

    }

    /**
     * Function for preparing layout data
     */
    void prepareLayout(){
        prepareComponents();

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}