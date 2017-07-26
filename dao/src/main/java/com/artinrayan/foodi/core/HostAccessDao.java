package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.HostAccess;
import exceptions.HostAccessDaoException;

import java.util.List;

/**
 * Created by asus on 5/27/2017.
 */
public interface HostAccessDao {

    HostAccess findHostAccessById(int hostAccessId) throws HostAccessDaoException;

    List<HostAccess> findByUserIdAndHostId(int userId, int hostId) throws HostAccessDaoException;

    List<HostAccess> findAllHostsAccesses() throws HostAccessDaoException;

    void save(HostAccess hostAccess) throws HostAccessDaoException;

    void deleteById(long hostAccessId) throws HostAccessDaoException;
}
