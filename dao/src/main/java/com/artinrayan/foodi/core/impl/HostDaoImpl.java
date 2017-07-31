package com.artinrayan.foodi.core.impl;

import com.artinrayan.foodi.core.AbstractDao;
import com.artinrayan.foodi.core.HostDao;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.User;
import exceptions.HostDaoException;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 5/26/2017.
 */
@Repository("hostDao")
public class HostDaoImpl extends AbstractDao<Integer, Host> implements HostDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public List<Host> findByUserId(int userID) throws HostDaoException {
        try {
//            getSession().createCriteria(Host.class).list();
//            Query query = createQuery("from Host host join host.user user WHERE user.id = :userId ");
//            query.setParameter("userId", userID);
//            List<Host> hostList =query.list();

            Query query =  getSession().getNamedQuery(Host.GET_HOST_BY_USER_ID);
            query.setInteger("userId", userID);
            return query.list();

//            Criteria criteria = createEntityCriteria().addOrder(Order.asc("hostName"));
//            criteria.add( Restrictions.eq("user", user));
//            List<Host> hostLists = (List<Host>) criteria.list();

//            criteria.add(Restrictions.eq("user", userID));
//            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid dupl

//            if (hostList != null && hostList.size() > 0) {
//                for (Host host : hostList)
//                    Hibernate.initialize(host.getHostAccesses());
//            }
//            return hostList;
        }
        catch (QueryException e)
        {
            throw new HostDaoException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Host findByHostId(int hostId) throws HostDaoException {
        Query query =  getSession().getNamedQuery(Host.GET_HOST_BY_HOST_ID);
        query.setInteger("hostId", hostId);
        Host host = (Host) query.uniqueResult();

        if (host != null) {
            Hibernate.initialize(host.getHostFiles());
        }

        return host;
    }


    @Override
    public List<Host> findAllHosts() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("hostName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Host> hostList = criteria.list();

        return hostList;
    }

    @Override
    public Host findHostByHostIdAndUserId(int hostId, User user) {
        logger.info("hostId : {}", hostId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("hostId", hostId));
        crit.add(Restrictions.eq("user", user));
        Host Host = (Host)crit.uniqueResult();
        return Host;
    }

//    @Override
//    public Host findHostByHostIdWithHostAccesses(int hostId) {
//        Query query = getSession().getNamedQuery(Host.GET_HOST_BY_ID).setInteger("id", hostId);
//        query.setInteger("id", hostId);
//        Host host = (Host) query.list().get(0);
//        if (host != null) {
//            Hibernate.initialize(host.getHostAccesses());
//        }
//        return host;
//    }

    @Override
    public void save(Host host) {
        persist(host);
    }

    @Override
    public void update(Host host) {
        persist(host);
    }

    @Override
    public void delete(int hostId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("hostId", hostId));
        Host host = (Host) criteria.uniqueResult();
        if (host != null)
            delete(host);
    }
}
