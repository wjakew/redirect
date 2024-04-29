/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
*/
package com.jakubwawak.redirect.webComponents;

import com.jakubwawak.redirect.RedirectApplication;
import com.jakubwawak.redirect.propertiesParser.redirectConfiguration.RedirectConfiguration;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
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
import org.springframework.web.servlet.tags.form.ButtonTag;

/**
 * Main application web view
 */
@PageTitle("redirect by Jakub Wawak")
@Route("home")
@RouteAlias("/")
public class HomeView extends VerticalLayout {

    // upper header layout
    private HorizontalLayout headerLayout;
    private VerticalLayout mainLayout;
    private HorizontalLayout footerLayout;
    private HorizontalLayout linkbuttonLayout;

    private Button link1_button, link2_button, link3_button;

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
        addClassName("homeview");
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
        blogButton.addClassName("redirectbtn");
        projectsButton = new Button("Projects",VaadinIcon.PAINT_ROLL.create());
        projectsButton.addClassName("redirectbtn");

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

        // link button layout creator
        linkbuttonLayout = new HorizontalLayout();
        linkbuttonLayout.setAlignItems(Alignment.CENTER);
        linkbuttonLayout.setVerticalComponentAlignment(Alignment.CENTER);

        link1_button = new Button("Link 1"); link1_button.addClassName("redirectbtn-links");
        link2_button = new Button("Link 2"); link2_button.addClassName("redirectbtn-links");
        link3_button = new Button("Link 3"); link3_button.addClassName("redirectbtn-links");
        link1_button.setVisible(false); link2_button.setVisible(false); link3_button.setVisible(false);

        if ( RedirectApplication.properties.getValue("redirectBtnEnableFlag1")!= null){
            if (!RedirectApplication.properties.getValue("redirectBtnEnableFlag1").isEmpty()) {
                link1_button.setVisible(true);
                link1_button.setText(RedirectApplication.properties.getValue("redirectBtnText1"));
            }
        }
        if ( RedirectApplication.properties.getValue("redirectBtnEnableFlag2")!= null){
            if (!RedirectApplication.properties.getValue("redirectBtnEnableFlag2").isEmpty()) {
                link2_button.setVisible(true);
                link2_button.setText(RedirectApplication.properties.getValue("redirectBtnText2"));
            }
        }
        if ( RedirectApplication.properties.getValue("redirectBtnEnableFlag3")!= null){
            if (!RedirectApplication.properties.getValue("redirectBtnEnableFlag3").isEmpty()) {
                link3_button.setVisible(true);
                link3_button.setText(RedirectApplication.properties.getValue("redirectBtnText3"));
            }
        }

        ComponentEventListener<ClickEvent<Button>> listener = event -> {
            Button selectedButton = event.getSource();
            if (selectedButton.equals(link1_button)){
                if (RedirectApplication.properties.getValue("$redirectBtnUrl1").contains("http") ){
                    UI.getCurrent().getPage().executeJs("window.open('" + RedirectApplication.properties.getValue("$redirectBtnUrl1") + "', '_blank');");
                }
                else
                    UI.getCurrent().getPage().executeJs("window.open('https://" + RedirectApplication.properties.getValue("$redirectBtnUrl1") + "', '_blank');");
            }
            if (selectedButton.equals(link2_button)){
                if (RedirectApplication.properties.getValue("$redirectBtnUrl2").contains("http") ){
                    UI.getCurrent().getPage().executeJs("window.open('" + RedirectApplication.properties.getValue("$redirectBtnUrl2") + "', '_blank');");
                }
                else
                    UI.getCurrent().getPage().executeJs("window.open('https://" + RedirectApplication.properties.getValue("$redirectBtnUrl2") + "', '_blank');");
            }
            if (selectedButton.equals(link3_button)){
                if (RedirectApplication.properties.getValue("$redirectBtnUrl3").contains("http") ){
                    UI.getCurrent().getPage().executeJs("window.open('" + RedirectApplication.properties.getValue("$redirectBtnUrl3") + "', '_blank');");
                }
                else
                    UI.getCurrent().getPage().executeJs("window.open('https://" + RedirectApplication.properties.getValue("$redirectBtnUrl3") + "', '_blank');");
            }
        };

        link1_button.addClickListener(listener);
        link2_button.addClickListener(listener);
        link3_button.addClickListener(listener);

        linkbuttonLayout.add(link1_button,link2_button,link3_button);

        headerLayout.add(left_layout,center_layout,right_layout);

        // setting buttons visiblity
        if ( redirectConfiguration.blogFlag.equals("1") ){
            blogButton.setVisible(true);
        }
        else{
            blogButton.setVisible(false);
        }
        if ( redirectConfiguration.projectsFlag.equals("1")){
            projectsButton.setVisible(true);
        }
        else{
            projectsButton.setVisible(false);
        }

        pagetitleHeader = new H1();
        pagetitleHeader.addClassName("header");

        pagetitleDesc = new H6();
        pagetitleDesc.addClassName("desc");

        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        mainLayout.getStyle().set("text-align", "center");
        mainLayout.add(pagetitleHeader,pagetitleDesc,linkbuttonLayout);


        footerLayout = new HorizontalLayout();
        footerLayout.setWidthFull();
        footerLayout.add(new H6("by redirect and "+redirectConfiguration.pageTitleOwner));
        footerLayout.setAlignItems(Alignment.CENTER);
        footerLayout.setVerticalComponentAlignment(Alignment.CENTER);
        footerLayout.addClassName("footer");
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
        add(mainLayout);
        add(footerLayout);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
}