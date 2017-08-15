package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Category;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 5/27/2017.
 */
@Service("hostAccessService")
@Transactional
public class HostAccessServiceImpl implements HostAccessService {

    @Autowired
    HostAccessDao hostAccessDao;

    @Autowired
    UserDao userDao;


    @Override
    public Category findHostAccessById(int hostAccessId) throws BusinessException {
        return hostAccessDao.findHostAccessById(hostAccessId);
    }

    @Override
    public List<Category> findHostAccessByUserIdAndHostId(int userId, int hostAccessId) throws BusinessException {
        return hostAccessDao.findByUserIdAndHostId(userId, hostAccessId);
    }

    @Override
    public List<Category> findAllHostsAccesses() throws BusinessException {
        return hostAccessDao.findAllHostsAccesses();
    }

    @Override
    public void saveHostAccess(Category hostAccess) throws BusinessException {
        hostAccessDao.save(hostAccess);
    }

    @Override
    public void deleteHostAccess(long hostAccessId) throws BusinessException {
        hostAccessDao.deleteById(hostAccessId);
    }
}
