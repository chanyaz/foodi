package com.artinrayan.foodi.core.impl;

import com.artinrayan.foodi.core.AbstractDao;
import com.artinrayan.foodi.core.HostFileDao;
import com.artinrayan.foodi.model.HostFile;
import exceptions.HostDaoException;
import exceptions.HostFileDaoException;
import org.hibernate.Criteria;
import org.hibernate.QueryException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 7/18/2017.
 */
@Repository("hostFileDao")
public class HostFileDaoImpl extends AbstractDao<Integer, HostFile> implements HostFileDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void save(HostFile hostFile) throws HostFileDaoException {
        persist(hostFile);
    }

    @Override
    public List<HostFile> findHostFilesByHostId(int hostId) throws HostFileDaoException {
        try {
            Criteria criteria = createEntityCriteria().addOrder(Order.asc("creationDate"));
            criteria.add(Restrictions.eq("host", hostId));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid dupl
            return  (List<HostFile>) criteria.list();
        }
        catch (QueryException e)
        {
            throw new HostDaoException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public HostFile findHostFileById(int hostFileId) throws HostFileDaoException {
        logger.info("accessFileId : {}", hostFileId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", hostFileId));
        return  (HostFile)crit.uniqueResult();
    }

    @Override
    public void delete(int hostFileId) throws HostFileDaoException {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("id", hostFileId));
            HostFile hostFile = (HostFile) criteria.uniqueResult();
            if (hostFile != null)
                delete(hostFile);
        }
        catch (QueryException e)
        {
            throw new HostFileDaoException(e.getMessage(), e.getCause());
        }
    }


}
