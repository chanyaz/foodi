package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Category;
import com.artinrayan.foodi.model.Host;
import exceptions.CategoryDaoException;
import exceptions.UserDaoException;

import java.util.List;

/**
 * Created by asus on 8/14/2017.
 */
public interface CategoryDao {

    void save(Category category) throws CategoryDaoException;

    void delete(long categoryId) throws CategoryDaoException;

    public List<Category> findCategoryByHostId(Host host);
}
