package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.User;
import exception.BusinessException;
import exceptions.HostDaoException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 5/25/2017.
 */
@Service("hostService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class HostServiceImpl implements HostService {

    @Autowired
    private HostDao hostDao;

    @Override
    @AfterThrowing(pointcut = "businessService()", throwing = "ex")
    public List<Host> findHostByUserId(int userID) throws BusinessException {

        try
        {
            return hostDao.findByUserId(userID);
        }
        catch (HostDaoException e)
        {
            throw new BusinessException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public Host findHostByHostId(int hostId) throws BusinessException {

        try
        {
            return hostDao.findByHostId(hostId);
        }
        catch (HostDaoException e)
        {
            throw new BusinessException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Host> findAllHosts() throws BusinessException {
        try {
            return hostDao.findAllHosts();
        }
        catch (HostDaoException e)
        {
            throw new BusinessException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Host findHostByHostIdAndUserId(int hostId, User user) throws BusinessException {
        try {
            return hostDao.findHostByHostIdAndUserId(hostId, user);
        }
        catch (HostDaoException e)
        {
            throw new BusinessException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void saveHost(Host host) throws BusinessException{
        try {
            hostDao.save(host);
        }
        catch (HostDaoException e)
        {
            throw new BusinessException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void updateHost(Host host) throws BusinessException{
        try {
            Host entity = hostDao.findByHostId(host.getHostId());
            if (entity != null)
            {
                entity.setHostName(host.getHostName());
                entity.setCreationDate(host.getCreationDate());
                entity.setEnabled(host.getEnabled());
                entity.setHostDetail(host.getHostDetail());
                entity.setHostAddress(host.getHostAddress());
                entity.setHostCity(host.getHostCity());
                entity.setHostCountry(host.getHostCountry());
                entity.setHostPhoneNumber(host.getHostPhoneNumber());
                entity.setHostWebSite(host.getHostWebSite());
                entity.setLatitude(host.getLatitude());
                entity.setLongitude(host.getLongitude());
                entity.setAttachments(host.getAttachments());
            }
        }
        catch (HostDaoException e)
        {
            throw new BusinessException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void deleteHost(int hostId) throws BusinessException{
        try {
            hostDao.delete(hostId);
        }
        catch (HostDaoException e)
        {
            throw new BusinessException(e.getMessage(), e.getCause());
        }
    }
}
