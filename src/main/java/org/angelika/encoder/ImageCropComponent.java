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
}
