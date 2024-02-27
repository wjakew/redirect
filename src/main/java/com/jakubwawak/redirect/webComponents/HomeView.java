/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
*/
package com.jakubwawak.redirect.webComponents;

import com.jakubwawak.redirect.RedirectApplication;
import com.jakubwawak.redirect.propertiesParser.redirectConfiguration.RedirectConfiguration;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * Main application web view
 */
@PageTitle("redirect by Jakub Wawak")
@Route("home")
@RouteAlias("/")
public class HomeView extends VerticalLayout {

    // upper header layout
    private HorizontalLayout headerLayout;
    private Image iconImage;
    private Button blogButton;
    private Button projectsButton;
    // main headers
    private H1 pagetitleHeader;
    private H6 pagetitleDesc;

    // button row for redirecting
    private Button redirect1_btn, redirect2_btn,redirect3_btn;

    // main view configuration
    private RedirectConfiguration redirectConfiguration;
    /**
     * Constructor
     */
    public HomeView(){
        this.redirectConfiguration = RedirectApplication.redirectConfiguration;
        this.getElement().setAttribute("theme", Lumo.DARK);
        prepareComponents();
        reloadLayout();
    }

    /**
     * Function for preparing components
     */
    void prepareComponents(){
        StreamResource res = new StreamResource("redirect_icon.png", () -> {
            return HomeView.class.getClassLoader().getResourceAsStream(redirectConfiguration.getIconPath());
        });
        iconImage = new Image(res,"redirect_logo");
        iconImage.setHeight("5rem");
        iconImage.setWidth("5rem");

        blogButton = new Button("Blog", VaadinIcon.BOOK.create());
        blogButton.getStyle().set("background-color",redirectConfiguration.getMainPageButtonStyle());
        blogButton.getStyle().set("color",redirectConfiguration.getContrastColor(redirectConfiguration.getMainPageButtonStyle()));
        blogButton.getElement().getStyle().set("font-family",redirectConfiguration.getMainPageFontStyle());
        blogButton.getStyle().set("border-radius","25px");
        blogButton.setHeight("5rem");

        projectsButton = new Button("Projects",VaadinIcon.PAINT_ROLL.create());
        projectsButton.getStyle().set("background-color",redirectConfiguration.getMainPageButtonStyle());
        projectsButton.getStyle().set("color",redirectConfiguration.getContrastColor(redirectConfiguration.getMainPageButtonStyle()));
        projectsButton.getElement().getStyle().set("font-family",redirectConfiguration.getMainPageFontStyle());
        projectsButton.getStyle().set("border-radius","25px");
        projectsButton.setHeight("5rem");

        headerLayout = new HorizontalLayout();

        headerLayout.setWidth("70%");
        headerLayout.setMargin(true);
        headerLayout.getStyle().set("border-radius","15px");

        // prepare window layout and components
        FlexLayout center_layout = new FlexLayout();
        center_layout.setSizeFull();
        center_layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        center_layout.setAlignItems(FlexComponent.Alignment.CENTER);
        center_layout.add();

        FlexLayout left_layout = new FlexLayout();
        left_layout.setSizeFull();
        left_layout.setJustifyContentMode(JustifyContentMode.START);
        left_layout.setAlignItems(Alignment.CENTER);
        left_layout.setWidth("80%");
        left_layout.add(iconImage);

        FlexLayout right_layout = new FlexLayout();
        right_layout.setSizeFull();
        right_layout.setJustifyContentMode(JustifyContentMode.START);
        right_layout.setAlignItems(FlexComponent.Alignment.END);
        right_layout.add(blogButton,projectsButton);
        right_layout.setWidth("80%");

        headerLayout.add(left_layout,center_layout,right_layout);

        pagetitleHeader = new H1();
        pagetitleHeader.getElement().getStyle().set("font-family",redirectConfiguration.getMainPageFontStyle());
        pagetitleHeader.getElement().getStyle().set("font-size","10rem");
        pagetitleHeader.getElement().getStyle().set("word-wrap","break-word");

        pagetitleDesc = new H6();
        pagetitleDesc.getElement().getStyle().set("font-family",redirectConfiguration.getMainPageFontStyle());
        pagetitleDesc.getElement().getStyle().set("word-wrap","break-word");
    }

    /**
     * Function for reloading component data
     */
    void reloadComponents(){
        pagetitleHeader.setText(redirectConfiguration.pageTitleHeader);
        pagetitleDesc.setText(redirectConfiguration.pageTitleDesc);
    }

    /**
     * Function for preparing layout data
     */
    public void reloadLayout(){
        reloadComponents();
        add(headerLayout);
        add(pagetitleHeader);
        add(pagetitleDesc);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        getStyle().set("color",redirectConfiguration.getMainPageFontColor());
        getStyle().set("background-image",redirectConfiguration.getMainPageBackgroundStyle());
        getStyle().set("--lumo-font-family",redirectConfiguration.getMainPageFontStyle());
    }

}