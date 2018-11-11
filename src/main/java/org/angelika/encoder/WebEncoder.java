/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.angelika.encoder;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;

/**
 *
 * @author angie
 */
//@WebService(serviceName = "ImageEncoder", targetNamespace = "http://localhost:8080/ImageEncoder/WebEncoder?wsdl")
@WebService(serviceName = "ImageEncoder")
@Stateless()
public class WebEncoder {


    /**
     * Web service operation
     * @param x1
     */
    @WebMethod(operationName = "crop")
    public String crop(@WebParam(name = "x1") double x1, @WebParam(name = "x2") double x2, @WebParam(name = "y1") double y1, @WebParam(name = "y2") double y2) {
      
        FileEncoder encoder = new FileEncoder();
        File orginalFile = encoder.getFileFromResources();

        Image image = null;
        try {
            image = ImageIO.read(orginalFile);
        } catch (IOException ex) {
            Logger.getLogger(WebEncoder.class.getName()).log(Level.SEVERE, "Coudn't read file.", ex);
        }

        ImageCropComponent imageComponent = new ImageCropComponent();
        int firstLat = imageComponent.convertLatitudeDegreesToPixels(x1);
        int secLat = imageComponent.convertLatitudeDegreesToPixels(x2);

        int firstLong = imageComponent.convertLongitudeDegreesToPixels(y1);
        int secLong = imageComponent.convertLongitudeDegreesToPixels(y2);
        
        BufferedImage bufferedImage = imageComponent.cropImage(image, firstLat, firstLong,secLat, secLong);
                
        File croppedFile = new File("/Users/angie/Downloads/center.png");
        try {
            ImageIO.write(bufferedImage, "png", croppedFile);
        } catch (IOException ex) {
            Logger.getLogger(WebEncoder.class.getName()).log(Level.SEVERE, "Coudn't write file.", ex);
        }
        String encodedFile = encoder.encodeImageToBase64Binary(croppedFile);
        return encodedFile;
       
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getBinaryImage")
    public String getBinaryImage() {
        FileEncoder encoder = new FileEncoder();
        File orginalFile = encoder.getFileFromResources();
        
        String encodedFile = encoder.encodeImageToBase64Binary(orginalFile);
        return encodedFile;
    }
}
