/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
*/
package com.jakubwawak.redirect.webComponents;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jakubwawak.redirect.RedirectApplication;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Main application web view
 */
@PageTitle("card")
@Route("card")
public class CardView extends VerticalLayout {

    VerticalLayout mainComponent;


    /**
     * Constructor
     */
    public CardView(){
        addClassName("homeview");
        prepareLayout();
    }

    /**
     * Function for preparing components
     */
    void prepareComponents(){
        ArrayList<String> data = RedirectApplication.database.getCardInfo();
        mainComponent = new VerticalLayout();
        mainComponent.addClassName("cardlayout");
        mainComponent.setJustifyContentMode(JustifyContentMode.CENTER);
        mainComponent.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        if (data.isEmpty()){
            mainComponent.add(new H1("No card data set!"));
            mainComponent.add(new H6("Please set the card data in the database"));
        }
        else{

            HorizontalLayout mainHorizontalLayout = new HorizontalLayout();
            mainHorizontalLayout.setSizeFull();
            mainHorizontalLayout.setAlignItems(Alignment.CENTER);
            mainHorizontalLayout.setVerticalComponentAlignment(Alignment.CENTER);

            VerticalLayout leftLayout = new VerticalLayout();
            leftLayout.setSizeFull();
            leftLayout.setJustifyContentMode(JustifyContentMode.CENTER);
            leftLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

            VerticalLayout photo = new VerticalLayout();
            photo.addClassName("cardlayout");
            photo.setWidthFull();photo.setHeight("50%");

            try {
                if ( !RedirectApplication.redirectConfiguration.qrCodeLink.isBlank() ){
                    StreamResource qrCodeResource = generateQRCodeStreamResource(RedirectApplication.redirectConfiguration.qrCodeLink, 512, 512);
                    Image qrCodeImage = new Image(qrCodeResource, "QR Code");
                    // Set the image size to 100% to make it responsive and fit the parent layout
                    qrCodeImage.setWidth("100%");
                    qrCodeImage.setHeight("100%");
                    photo.add(qrCodeImage); // Assuming mainComponent is your VerticalLayout
                }
            } catch (Exception e) {}

            leftLayout.add(photo,new H1("card"));

            VerticalLayout rightLayout = new VerticalLayout();
            rightLayout.setSizeFull();
            rightLayout.setJustifyContentMode(JustifyContentMode.CENTER);
            rightLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

            rightLayout.add(new H1(data.get(3)));
            rightLayout.add(new H6(data.get(0)));
            rightLayout.add(new H4(data.get(1)));

            mainHorizontalLayout.add(leftLayout,rightLayout);
            mainComponent.add(mainHorizontalLayout);
        }
    }

    /**
     * Function for preparing layout data
     */
    void prepareLayout(){
        prepareComponents();
        ArrayList<String> data = RedirectApplication.database.getCardInfo();

        if (RedirectApplication.properties.getValue("cardEnabled").equals("1")){
            // create card
            if ( !data.isEmpty() ){
                add(new H6(data.get(2)));
            }
            else{
                add(new H1("No card data set!"));
            }
            add(mainComponent);
            add(new H6("created using redirect"));
        }
        else{
            add(new H1("Card is disabled"));
        }

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    /**
     * Function for generating QR code
     * @param text
     * @param width
     * @param height
     * @return
     */
    StreamResource generateQRCodeStreamResource(String text, int width, int height){
        try{
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            // Specify the colors: black for the QR code, transparent for the background
            MatrixToImageConfig config = new MatrixToImageConfig(0xFF000000, 0x00FFFFFF); // ARGB format

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, config);
            byte[] pngData = pngOutputStream.toByteArray();
            return new StreamResource("qrCode.png", () -> new ByteArrayInputStream(pngData));
        }catch(Exception ex){
            RedirectApplication.logger.addLog("QR-GENERATOR","Error: "+ex.toString());
        }
        return null;
    }

}