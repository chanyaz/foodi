package com.artinrayan.foodi.web.customtags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by asus on 7/19/2017.
 */
public class ImageHolder extends SimpleTagSupport {


    // adding to git
    private byte[] usrImage;

    public void setUsrImage(byte[] usrImage) {
        this.usrImage = usrImage;
    }

    @Override
    public void doTag() throws JspException, IOException {
//        System.out.println("tag lib");
//        try {
//            JspWriter out = getJspContext().getOut();
//            if (usrImage != null && usrImage.length > 0) {
//                byte[] encodeBase64 = Base64.encode(usrImage);
//                String base64Encoded = new String(encodeBase64, "UTF-8");
//                out.print("data:image/jpeg;base64,"+base64Encoded);
//
//            }
//        } catch (Exception e) {
//            throw new JspException("Error: " + e.getMessage());     }
    }
}
