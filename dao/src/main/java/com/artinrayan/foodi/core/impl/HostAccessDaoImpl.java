package com.artinrayan.foodi.core.impl;

import com.artinrayan.foodi.core.AbstractDao;
import com.artinrayan.foodi.core.HostAccessDao;
import com.artinrayan.foodi.model.HostAccess;
import com.artinrayan.foodi.model.User;
import exceptions.HostAccessDaoException;
import exceptions.HostDaoException;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.QueryException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 5/27/2017.
 */
@Repository("hostAccessDao")
public class HostAccessDaoImpl extends AbstractDao<Integer, HostAccess> implements HostAccessDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public HostAccess findHostAccessById(int hostAccessId) throws HostAccessDaoException {
        logger.info("hostAccessId : {}", hostAccessId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("hostAccessId", hostAccessId));
        return  (HostAccess)crit.uniqueResult();
    }

    @Override
    public List<HostAccess> findByUserIdAndHostId(int userId, int hostAccessId) throws HostAccessDaoException {
        try {
            Criteria criteria = createEntityCriteria().addOrder(Order.asc("hostName"));
            criteria.add(Restrictions.eq("hostAccessId", hostAccessId));
            criteria.add(Restrictions.eq("host", userId));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid dupl
            List<HostAccess> hostAccesses = (List<HostAccess>) criteria.list();

            return hostAccesses;
        }
        catch (QueryException e)
        {
            throw new HostDaoException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<HostAccess> findAllHostsAccesses() throws HostAccessDaoException {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<HostAccess> hostAccesses = (List<HostAccess>) criteria.list();
        if(hostAccesses!=null){
            for (HostAccess access : hostAccesses)
            {
                Hibernate.initialize(access.getHost());
            }
        }

        return hostAccesses;
    }

    @Override
    public void save(HostAccess hostAccess) throws HostAccessDaoException {
        persist(hostAccess);
    }

    @Override
    public void deleteById(long hostAccessId) throws HostAccessDaoException {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("hostAccessId", hostAccessId));
        HostAccess hostAccess = (HostAccess) criteria.uniqueResult();
        if (hostAccess != null)
            delete(hostAccess);
    }
}
