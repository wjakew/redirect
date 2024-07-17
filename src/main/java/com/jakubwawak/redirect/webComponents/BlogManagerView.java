/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
*/
package com.jakubwawak.redirect.webComponents;

import com.jakubwawak.redirect.RedirectApplication;
import com.jakubwawak.redirect.webComponents.components.BlogManagerComponent;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

/**
 * Main application web view
 */
@PageTitle("Blog Manager")
@Route("/blogmanager")
public class BlogManagerView extends VerticalLayout implements HasUrlParameter<String> {


    /**
     * Constructor
     */
    public BlogManagerView(){
        addClassName("homeview");
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

        BlogManagerComponent bmc = new BlogManagerComponent();
        add(bmc);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {
        if (parameter != null){
            if (RedirectApplication.database.verifyBlogKey(parameter)){
                prepareLayout();
            }
            else {
                add(new H6("Key not found! Access forbidden!"));
            }
        }
        else{
            add(new H6("No key provided, check the link!"));
        }
    }

}