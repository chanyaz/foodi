package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.HostFile;
import exceptions.HostFileDaoException;

import java.util.List;

/**
 * Created by asus on 7/18/2017.
 */
public interface HostFileDao {

    void save(HostFile hostFile) throws HostFileDaoException;

    List<HostFile> findHostFilesByHostId(int hostId) throws HostFileDaoException;

    HostFile findHostFileById(int fileId) throws HostFileDaoException;

    void delete(int hostFileId) throws HostFileDaoException;

}