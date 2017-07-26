package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.HostAccessFile;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 7/18/2017.
 */
@Service("hostAccessFileService")
@Transactional
public class HostAccessFileServiceImpl implements HostAccessFileService {

    @Autowired
    HostAccessFileDao hostAccessFileDao;

    @Override
    public void saveHostAccessImage(HostAccessFile hostAccessFile) throws BusinessException {
        hostAccessFileDao.save(hostAccessFile);
    }

    public List<HostAccessFile> findAccessFilesByHostAccessId(int hostAccessId) throws BusinessException
    {
        return hostAccessFileDao.findAccessFilesByHostAccessId(hostAccessId);
    }

    @Override
    public HostAccessFile findAccessFileByHostAccessId(int accessFileId) throws BusinessException {
        return hostAccessFileDao.findAccessFileById(accessFileId);
    }


}
