package com.artinrayan.foodi.web.util;

import org.springframework.web.context.annotation.ApplicationScope;

/**
 * Created by asus on 8/2/2017.
 */
@ApplicationScope
public class ImageResolver {

    private static final String imagesUrl = "static/images/";

    public static String getImage(String path) {
        return FoodiApplicationHelper.getServletContext().getContextPath() + "/" + imagesUrl + path;
    }


}
