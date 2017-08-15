package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Attachment;
import exception.BusinessException;

import java.util.List;

/**
 * Created by asus on 7/18/2017.
 */
public interface AttachmentService {

    void saveAttachment(Attachment attachmentId) throws BusinessException;

    public List<Attachment> findAttachmentsByHostId(int attachmentId) throws BusinessException;

    public Attachment findAttachmentByHostId(int attachmentId) throws BusinessException;

    void deleteAttachmentById(int attachmentId) throws BusinessException;

}
