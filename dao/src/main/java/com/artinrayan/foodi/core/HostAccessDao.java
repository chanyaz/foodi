package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Category;
import exceptions.HostAccessDaoException;

import java.util.List;

/**
 * Created by asus on 5/27/2017.
 */
public interface HostAccessDao {

    Category findHostAccessById(int hostAccessId) throws HostAccessDaoException;

    List<Category> findByUserIdAndHostId(int userId, int hostId) throws HostAccessDaoException;

    List<Category> findAllHostsAccesses() throws HostAccessDaoException;

    void save(Category hostAccess) throws HostAccessDaoException;

    void deleteById(long hostAccessId) throws HostAccessDaoException;
}
