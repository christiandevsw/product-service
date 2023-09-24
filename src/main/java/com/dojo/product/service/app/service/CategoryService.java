package com.dojo.product.service.app.service;

import com.dojo.product.service.app.model.entity.Category;

public interface CategoryService {
    Iterable<Category> listCategories();
    Category getById(Long id);
    Category save(Category category);

    void deleteById(Long id);

}
