/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.angelika.encoder;

import java.io.File;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

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
        File file = encoder.getFileFromResources();
        String encodedFile = encoder.encodeImageToBase64Binary(file);
        return encodedFile;
    }
}
