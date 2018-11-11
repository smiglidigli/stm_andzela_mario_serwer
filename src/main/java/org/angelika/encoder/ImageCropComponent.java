/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.angelika.encoder;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author angie
 */
public class ImageCropComponent {

    public BufferedImage cropImage(Image image, int firstCordX, int firstCordY, int secondCordX, int secondCordY) {
        BufferedImage bufferedImage = new BufferedImage(secondCordX, secondCordY, BufferedImage.TYPE_INT_ARGB);
        bufferedImage.getGraphics().drawImage(image, 0, 0, secondCordX, secondCordY, firstCordX, firstCordY, firstCordX + secondCordX, firstCordY + secondCordY, null);
        return bufferedImage;
    }

    public int convertLatitudeDegreesToPixels(double degrees) {
        double centerLatitude = 53.135650;
        int pixels;
        if (degrees <= centerLatitude) {
            double difference = centerLatitude - degrees;
            pixels = (int) (1000 * difference / 0.006453);
        } else {
            throw new RuntimeException("Latitude should be less then " + centerLatitude);
        }
        return pixels;
    }

    public int convertLongitudeDegreesToPixels(double degrees) {
        double centerLongitude = 23.145682;
        int pixels;
        if (degrees >= centerLongitude) {
            double difference = degrees - centerLongitude;
            pixels = (int) (1000*difference/0.010708);
        } else {
            throw new RuntimeException("Longitude should be more then " + centerLongitude);
        }
        return pixels;
    }
}
