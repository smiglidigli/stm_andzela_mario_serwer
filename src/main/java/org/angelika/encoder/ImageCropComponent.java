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

    private static final double MIN_X = 53.129281;
    private static final double MIN_Y = 23.145682;
    private static final double MAX_X = 53.135650;
    private static final double MAX_Y = 23.156369;
    
     public BufferedImage cropImage(Image image, int[] pixels) {
        BufferedImage bufferedImage = new BufferedImage(pixels[1], pixels[3], BufferedImage.TYPE_INT_ARGB);
        bufferedImage.getGraphics().drawImage(image, 0, 0, pixels[1], pixels[3], pixels[0], pixels[2], pixels[0] + pixels[1], pixels[2] + pixels[3], null);
        return bufferedImage;
    }

    public int[] getPixelsFromCoordinates(double[] coords) {
        int[] outputPixels = new int[4];
        outputPixels[0] = (int) (1000 * (MAX_X - coords[0]) / (MAX_X - MIN_X));
        outputPixels[1] = (int) (1000 * (MAX_X - coords[1]) / (MAX_X - MIN_X));
        outputPixels[2] = (int) (1000 * (coords[2] - MIN_Y) / (MAX_Y - MIN_Y));
        outputPixels[3] = (int) (1000 * (coords[3] - MIN_Y) / (MAX_Y - MIN_Y));
        return outputPixels;
    }
}
