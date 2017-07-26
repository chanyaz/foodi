package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.HostAccessFile;
import exceptions.HostAccessDaoException;
import exceptions.HostAccessImageException;

import java.util.List;

/**
 * Created by asus on 7/18/2017.
 */
public interface HostAccessFileDao {

    void save(HostAccessFile hostAccessFile) throws HostAccessImageException;

    List<HostAccessFile> findAccessFilesByHostAccessId(int hostAccessId) throws HostAccessDaoException;

    HostAccessFile findAccessFileById(int accessFileId) throws HostAccessDaoException;

}