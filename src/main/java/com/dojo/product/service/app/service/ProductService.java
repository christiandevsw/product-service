package com.dojo.product.service.app.service;

import com.dojo.product.service.app.model.entity.Product;

import java.util.List;

public interface ProductService {
    Integer totalProducts();
    Product getProductById(Long id);
    List<Product> getProductsByPrice(Double price);
    List<Product> listProductsByCategory(Long categoryId);
    List<Product> listProductsByNameAndCategory(String name, Long categoryId);
    Product save(Product product);
    void deleteById(Long id);

}
