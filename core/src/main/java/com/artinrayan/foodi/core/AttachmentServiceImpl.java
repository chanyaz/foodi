package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Attachment;
import exception.BusinessException;
import exceptions.AttachmentDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 7/18/2017.
 */
@Service("attachmentService")
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentDao attachmentDao;

    @Override
    public void saveAttachment(Attachment attachmentId) throws BusinessException {
        attachmentDao.save(attachmentId);
    }

    public List<Attachment> findAttachmentsByHostId(int attachmentId) throws BusinessException
    {
        return attachmentDao.findAttachmentsByHostId(attachmentId);
    }

    @Override
    public Attachment findAttachmentByHostId(int attachmentId) throws BusinessException {
        return attachmentDao.findAttachmentById(attachmentId);
    }

    @Override
    public void deleteAttachmentById(int attachmentId) throws BusinessException {
        try {
            attachmentDao.delete(attachmentId);
        }
        catch (AttachmentDaoException e)
        {
            throw new BusinessException(e.getMessage(), e.getCause());
        }
    }


}
