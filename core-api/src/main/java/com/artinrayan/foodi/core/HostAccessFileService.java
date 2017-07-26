package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.HostAccessFile;
import exception.BusinessException;

import java.util.List;

/**
 * Created by asus on 7/18/2017.
 */
public interface HostAccessFileService {

    void saveHostAccessImage(HostAccessFile hostAccessFile) throws BusinessException;

    public List<HostAccessFile> findAccessFilesByHostAccessId(int hostAccessId) throws BusinessException;

    public HostAccessFile findAccessFileByHostAccessId(int accessFileId) throws BusinessException;

}
