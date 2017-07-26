package com.artinrayan.foodi.core.impl;

import com.artinrayan.foodi.core.AbstractDao;
import com.artinrayan.foodi.core.HostAccessFileDao;
import com.artinrayan.foodi.model.HostAccessFile;
import exceptions.HostAccessDaoException;
import exceptions.HostAccessImageException;
import exceptions.HostDaoException;
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
@Repository("hostAccessFileDao")
public class HostAccessFileDaoImpl extends AbstractDao<Integer, HostAccessFile> implements HostAccessFileDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void save(HostAccessFile hostAccessFile) throws HostAccessImageException {
        persist(hostAccessFile);
    }

    @Override
    public List<HostAccessFile> findAccessFilesByHostAccessId(int hostAccessId) throws HostAccessDaoException {
        try {
            Criteria criteria = createEntityCriteria().addOrder(Order.asc("creationDate"));
            criteria.add(Restrictions.eq("hostAccess", hostAccessId));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid dupl
            return  (List<HostAccessFile>) criteria.list();
        }
        catch (QueryException e)
        {
            throw new HostDaoException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public HostAccessFile findAccessFileById(int accessFileId) throws HostAccessDaoException {
        logger.info("accessFileId : {}", accessFileId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", accessFileId));
        return  (HostAccessFile)crit.uniqueResult();
    }


}
