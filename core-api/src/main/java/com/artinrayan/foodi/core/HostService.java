package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.User;
import exception.BusinessException;

import java.util.List;

/**
 * Created by asus on 5/25/2017.
 */
public interface HostService {

    List<Host> findHostByUserId(int userId) throws BusinessException;

    Host findHostByHostId(int hostId) throws BusinessException;

    List<Host> findAllHosts() throws BusinessException;

    Host findHostByHostIdAndUserId(int hostId, User user) throws BusinessException;

    void saveHost(Host host) throws BusinessException;

    void updateHost(Host host) throws BusinessException;

    void deleteHost(int hostId) throws BusinessException;
}
