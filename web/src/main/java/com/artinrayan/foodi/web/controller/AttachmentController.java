package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.AttachmentService;
import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.Attachment;
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
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    HostService hostService;

    /**
     *
     * @param id
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/displayAttachmentById", method = RequestMethod.GET)
    public void displayAttachmentById(@RequestParam("id") Integer id, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        try {
            Attachment attachment = attachmentService.findAttachmentByHostId(id);

            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(attachment.getFileContent());


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
    @RequestMapping(value = "/displayAttachmentByHostId", method = RequestMethod.GET)
    public void displayAttachmentByHostId(@RequestParam("id") Integer hostId, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        try {
            Host host = hostService.findHostByHostId(hostId);

            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            Attachment attachment = (Attachment) host.getAttachments().toArray()[0];
            response.getOutputStream().write(attachment.getFileContent());

            response.getOutputStream().close();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
