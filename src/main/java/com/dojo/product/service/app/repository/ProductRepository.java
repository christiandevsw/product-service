package com.dojo.product.service.app.repository;

import com.dojo.product.service.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select m from Product m where cast( floor (m.price) as integer )=?1")
    List<Product> findByPrice(Integer price);

    @Query("select m from Product m where m.category.id=?1")
    List<Product> getProductsByCategory(Long categoryId);

    @Query("select count(m) from Product m ")
    int getTotalProducts();

    @Query("select m from Product m where m.name like %?1% and m.category.id=?2")
    List<Product> findByNameAndCategory(String param, Long categoryId);

}
