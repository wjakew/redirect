/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
*/
package com.jakubwawak.redirect.webComponents.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * Object for creating UI for managing blog data
 */
public class BlogManagerComponent extends VerticalLayout {

    /**
     * Constructor
     */
    public BlogManagerComponent(){
        super();
        setSizeFull();
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    /**
     * Function for preparing components
     */
    void prepareComponents(){

    }
}
