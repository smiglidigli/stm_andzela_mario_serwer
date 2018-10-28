/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.angelika.encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angie
 */
public class FileEncoder {
    private final String fileResourcesPath = "drawing/bialystok_map.png";

    String encodeImageToBase64Binary(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            byte[] encodedBytes = java.util.Base64.getEncoder().encode(bytes);
            encodedfile = new String(encodedBytes);
        } catch (IOException e) {
            Logger.getLogger(FileEncoder.class.getName()).log(Level.WARNING, "File not found.", e);
        }
        return encodedfile;
    }

    public File getFileFromResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileResourcesPath).getFile());
        return file;
    }
}
