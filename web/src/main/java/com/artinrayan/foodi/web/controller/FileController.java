package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.HostAccessFileService;
import com.artinrayan.foodi.model.HostAccessFile;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus on 7/20/2017.
 */
@Controller
public class FileController {

    @Autowired
    HostAccessFileService hostAccessFileService;

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Integer itemId, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        try {
            HostAccessFile hostAccessFile = hostAccessFileService.findAccessFileByHostAccessId(itemId);

            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(hostAccessFile.getFileContent());


            response.getOutputStream().close();
        } catch (BusinessException e) {
            e.printStackTrace();
        }


    }
}
