package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.HostFile;
import exception.BusinessException;

import java.util.List;

/**
 * Created by asus on 7/18/2017.
 */
public interface HostFileService {

    void saveHostFile(HostFile hostFileId) throws BusinessException;

    public List<HostFile> findHostFilesByHostId(int hostFileId) throws BusinessException;

    public HostFile findHostFileByHostId(int hostFileId) throws BusinessException;

    void deleteHostFileById(int hostFileId) throws BusinessException;

}
