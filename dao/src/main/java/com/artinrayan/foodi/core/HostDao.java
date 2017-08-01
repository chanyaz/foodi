package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.User;
import exceptions.HostDaoException;

import java.util.List;

/**
 * Created by asus on 5/26/2017.
 */
public interface HostDao {

    List<Host> findByUserId(int userId) throws HostDaoException;

    Host findByHostId(int hostId) throws HostDaoException;

    List<Host> findAllHosts() throws HostDaoException;

    Host findHostByHostIdAndUserId(int hostId, User user) throws HostDaoException;

    void save(Host host) throws HostDaoException;

    void update(Host host) throws HostDaoException;

    void delete(int hostId) throws HostDaoException;
}
