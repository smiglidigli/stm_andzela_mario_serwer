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

    private static final double MIN_X = 53.129281;//right point
    private static final double MIN_Y = 23.145682;//left point
    private static final double MAX_X = 53.135650;//left point
    private static final double MAX_Y = 23.156369;//right point

    public BufferedImage cropImage(Image image, int[] pixels) {
        BufferedImage bufferedImage = new BufferedImage( pixels[1] - pixels[0],pixels[3] - pixels[2], BufferedImage.TYPE_INT_ARGB);
        bufferedImage.getGraphics().drawImage(image, 0, 0, pixels[1] - pixels[0],pixels[3] - pixels[2], pixels[0], pixels[2], pixels[1], pixels[3], null);
        return bufferedImage;
    }

    public int[] getPixelsFromCoordinates(double[] coords) {
        int[] output = new int[4];
        output[0] = (int) (1000 * (MAX_X - coords[1]) / (MAX_X - MIN_X));
        output[1] = (int) (1000 * (MAX_X - coords[0]) / (MAX_X - MIN_X));
        output[2] = (int) (1000 * (coords[2] - MIN_Y) / (MAX_Y - MIN_Y));
        output[3] = (int) (1000 * (coords[3] - MIN_Y) / (MAX_Y - MIN_Y));
        
//        int mapLength = (int) (MAX_X - MIN_X);
//        int mapHeight = (int) (MAX_Y - MIN_Y);
//        output[1] = (int) (( coords[0] - MIN_X) * 1000/mapLength);
//        output[0] = (int) (( coords[1] - MIN_X) * 1000/mapLength);
//        output[3] = (int) (( coords[2] - MIN_Y) * 1000/mapHeight);
//        output[2] = (int) (( coords[3] - MIN_Y) * 1000/mapHeight);

//        output[0] = (int) ((int) 1 / ((MIN_X + (coords[1] * mapLength) / 1000)));
//        output[3] = (int) ((int) 1 / (MIN_Y + (coords[2] * mapHeight) / 1000));
//        output[2] = (int) ((int) 1 / (MIN_Y + (coords[3] * mapHeight) / 1000));
        return output;
    }
}
