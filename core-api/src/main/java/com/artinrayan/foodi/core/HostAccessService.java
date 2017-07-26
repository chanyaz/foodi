package com.artinrayan.foodi.core;


import com.artinrayan.foodi.model.HostAccess;
import exception.BusinessException;

import java.util.List;

/**
 * Created by asus on 5/27/2017.
 */
public interface HostAccessService {

    HostAccess findHostAccessById(int hostAccessId) throws BusinessException;

    List<HostAccess> findHostAccessByUserIdAndHostId(int userId, int hostAccessId) throws BusinessException;

    List<HostAccess> findAllHostsAccesses() throws BusinessException;

    void saveHostAccess(HostAccess hostAccess) throws BusinessException;

    void deleteHostAccess(long hostAccessId) throws BusinessException;
}
