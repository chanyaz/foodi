package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Category;
import com.artinrayan.foodi.model.Host;

import java.util.List;

/**
 * Created by asus on 8/14/2017.
 */
public interface CategoryService {

    void saveCategory (Category category);

    List<Category> findHostCategoriesByHostId(Host host);
}
