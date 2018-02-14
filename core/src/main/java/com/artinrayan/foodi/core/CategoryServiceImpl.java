package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Category;
import com.artinrayan.foodi.model.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 8/14/2017.
 */
@Service
@Transactional("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired CategoryDao categoryDao;

    @Override
    public void saveCategory(Category category) {

    }

    @Override
    public List<Category> findHostCategoriesByHostId(Host host) {
        return categoryDao.findCategoryByHostId(host);
    }
}
