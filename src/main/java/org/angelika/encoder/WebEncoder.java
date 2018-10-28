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
@WebService(serviceName = "ImageEncoder")
@Stateless()
public class WebEncoder {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getBinaryImage")
    public String getBinaryImage(@WebParam(name = "i") String i) {
        FileEncoder encoder = new FileEncoder();
        File orginalFile = encoder.getFileFromResources();
        
        String encodedFile = encoder.encodeImageToBase64Binary(orginalFile);
        return encodedFile;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "crop")
    public String crop(@WebParam(name = "x1") Integer x1, @WebParam(name = "x2") Integer x2, @WebParam(name = "y1") Integer y1, @WebParam(name = "y2") Integer y2) {
        FileEncoder encoder = new FileEncoder();
        File orginalFile = encoder.getFileFromResources();

        Image image = null;
        try {
            image = ImageIO.read(orginalFile);
        } catch (IOException ex) {
            Logger.getLogger(WebEncoder.class.getName()).log(Level.SEVERE, "Coudn't read file.", ex);
        }
        
        //BufferedImage dst = new BufferedImage(x2, y2, BufferedImage.TYPE_INT_ARGB);
        //dst.getGraphics().drawImage(image, 0, 0, x2, y2, x1, y1, x1 + x2, y1 + y2, null);
        ImageCropComponent imageComponent = new ImageCropComponent();
        BufferedImage bufferedImage = imageComponent.cropImage(image, x1, y1, x2, y2);
                
        File croppedFile = new File("/Users/angie/Downloads/bialystok_new.png");
        try {
            ImageIO.write(bufferedImage, "png", croppedFile);
        } catch (IOException ex) {
            Logger.getLogger(WebEncoder.class.getName()).log(Level.SEVERE, "Coudn't write file.", ex);
        }
        String encodedFile = encoder.encodeImageToBase64Binary(croppedFile);
        return encodedFile;
    }
}
