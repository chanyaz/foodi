package com.artinrayan.foodi.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by asus on 8/6/2017.
 */
@WebServlet(value = "/imageLocator")
public class ImageServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        ServletContext cntx= req.getServletContext();
//        // Get the absolute path of the image
//        String filename = cntx.getRealPath("Images/button.png");
//        // retrieve mimeType dynamically
//        String mime = cntx.getMimeType(filename);
//        if (mime == null) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            return;
//        }
//
//        resp.setContentType(mime);
//        File file = new File(filename);
//        resp.setContentLength((int)file.length());
//
//        FileInputStream in = new FileInputStream(file);
//        OutputStream out = resp.getOutputStream();
//
//        // Copy the contents of the file to the output stream
//        byte[] buf = new byte[1024];
//        int count = 0;
//        while ((count = in.read(buf)) >= 0) {
//            out.write(buf, 0, count);
//        }
//        out.close();
//        in.close();


        File file = new File(req.getContextPath() + "/static/images/small/" + req.getParameter("img"));
        BufferedImage image = ImageIO.read(file);
        ImageIO.write(image, "PNG", resp.getOutputStream());
    }
}
