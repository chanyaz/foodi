package com.artinrayan.foodi.core;


import com.artinrayan.foodi.model.Category;
import exception.BusinessException;

import java.util.List;

/**
 * Created by asus on 5/27/2017.
 */
public interface HostAccessService {

    Category findHostAccessById(int hostAccessId) throws BusinessException;

    List<Category> findHostAccessByUserIdAndHostId(int userId, int hostAccessId) throws BusinessException;

    List<Category> findAllHostsAccesses() throws BusinessException;

    void saveHostAccess(Category hostAccess) throws BusinessException;

    void deleteHostAccess(long hostAccessId) throws BusinessException;
}
