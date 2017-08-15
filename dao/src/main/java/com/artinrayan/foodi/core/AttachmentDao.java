package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Attachment;
import exceptions.AttachmentDaoException;

import java.util.List;

/**
 * Created by asus on 7/18/2017.
 */
public interface AttachmentDao {

    void save(Attachment attachment) throws AttachmentDaoException;

    List<Attachment> findAttachmentsByHostId(int hostId) throws AttachmentDaoException;

    Attachment findAttachmentById(int attachmentId) throws AttachmentDaoException;

    void delete(int attachmentId) throws AttachmentDaoException;

}