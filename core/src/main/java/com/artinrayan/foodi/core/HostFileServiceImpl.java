package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.HostFile;
import exception.BusinessException;
import exceptions.HostFileDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 7/18/2017.
 */
@Service("hostFileService")
@Transactional
public class HostFileServiceImpl implements HostFileService {

    @Autowired
    HostFileDao hostFileDao;

    @Override
    public void saveHostFile(HostFile hostFileId) throws BusinessException {
        hostFileDao.save(hostFileId);
    }

    public List<HostFile> findHostFilesByHostId(int hostFileId) throws BusinessException
    {
        return hostFileDao.findHostFilesByHostId(hostFileId);
    }

    @Override
    public HostFile findHostFileByHostId(int hostFileId) throws BusinessException {
        return hostFileDao.findHostFileById(hostFileId);
    }

    @Override
    public void deleteHostFileById(int hostFileId) throws BusinessException {
        try {
            hostFileDao.delete(hostFileId);
        }
        catch (HostFileDaoException e)
        {
            throw new BusinessException(e.getMessage(), e.getCause());
        }
    }


}
