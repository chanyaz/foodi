package com.artinrayan.foodi.core.impl;

import com.artinrayan.foodi.core.AbstractDao;
import com.artinrayan.foodi.core.HostAccessDao;
import com.artinrayan.foodi.model.Category;
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
public class HostAccessDaoImpl extends AbstractDao<Integer, Category> implements HostAccessDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public Category findHostAccessById(int hostAccessId) throws HostAccessDaoException {
        logger.info("hostAccessId : {}", hostAccessId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("hostAccessId", hostAccessId));
        return  (Category)crit.uniqueResult();
    }

    @Override
    public List<Category> findByUserIdAndHostId(int userId, int hostAccessId) throws HostAccessDaoException {
        try {
            Criteria criteria = createEntityCriteria().addOrder(Order.asc("hostName"));
            criteria.add(Restrictions.eq("hostAccessId", hostAccessId));
            criteria.add(Restrictions.eq("host", userId));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid dupl
            List<Category> hostAccesses = (List<Category>) criteria.list();

            return hostAccesses;
        }
        catch (QueryException e)
        {
            throw new HostDaoException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Category> findAllHostsAccesses() throws HostAccessDaoException {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Category> hostAccesses = (List<Category>) criteria.list();
        if(hostAccesses!=null){
            for (Category access : hostAccesses)
            {
                Hibernate.initialize(access.getHost());
            }
        }

        return hostAccesses;
    }

    @Override
    public void save(Category hostAccess) throws HostAccessDaoException {
        persist(hostAccess);
    }

    @Override
    public void deleteById(long hostAccessId) throws HostAccessDaoException {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("hostAccessId", hostAccessId));
        Category hostAccess = (Category) criteria.uniqueResult();
        if (hostAccess != null)
            delete(hostAccess);
    }
}
