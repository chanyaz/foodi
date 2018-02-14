package com.artinrayan.foodi.core.impl;

import com.artinrayan.foodi.core.AbstractDao;
import com.artinrayan.foodi.core.CategoryDao;
import com.artinrayan.foodi.model.Category;
import com.artinrayan.foodi.model.Host;
import exceptions.CategoryDaoException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 8/14/2017.
 */
@Repository("categoryDao")
public class CategoryDaoImpl extends AbstractDao<Integer, Category> implements CategoryDao {

    static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);


    /**
     *
     * @param category
     * @throws CategoryDaoException
     */
    @Override
    public void save(Category category) throws CategoryDaoException {
        persist(category);
    }

    /**
     *
     * @param categoryId
     * @throws CategoryDaoException
     */
    @Override
    public void delete(long categoryId) throws CategoryDaoException {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", categoryId));
        Category category = (Category) criteria.uniqueResult();
        if (category != null)
            delete(category);
    }

    /**
     *
     * @param host
     * @return
     */
    @Override
    public List<Category> findCategoryByHostId(Host host) {
        logger.info("hostId : {}", host.getHostId());
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("host", host));
        List<Category> categories = crit.list();
        return categories;
    }
}
