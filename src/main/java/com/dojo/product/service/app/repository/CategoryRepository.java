package com.dojo.product.service.app.repository;

import com.dojo.product.service.app.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
