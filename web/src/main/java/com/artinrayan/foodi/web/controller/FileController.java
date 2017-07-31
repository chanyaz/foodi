package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.HostFileService;
import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.HostFile;
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
    HostFileService hostFileService;

    @Autowired
    HostService hostService;

    /**
     *
     * @param fileId
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/displayFileByFileId", method = RequestMethod.GET)
    public void displayFileByFileId(@RequestParam("id") Integer fileId, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        try {
            HostFile hostFile = hostFileService.findHostFileByHostId(fileId);

            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(hostFile.getFileContent());


            response.getOutputStream().close();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param hostId
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/displayFileByHostId", method = RequestMethod.GET)
    public void displayFileByHostFileId(@RequestParam("id") Integer hostId, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        try {
            Host host = hostService.findHostByHostId(hostId);

            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            HostFile hostFile = (HostFile) host.getHostFiles().toArray()[0];
            response.getOutputStream().write(hostFile.getFileContent());

            response.getOutputStream().close();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
