package com.dojo.product.service.app.service;

import com.dojo.product.service.app.model.Product;
import com.dojo.product.service.app.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Integer totalProducts() {
        return productRepository.getTotalProducts();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByPrice(Double price) {
        return productRepository.findByPrice(price.intValue());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> listProductsByCategory(Long categoryId) {
        return productRepository.getProductsByCategory(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> listProductsByNameAndCategory(String name, Long categoryId) {
        return productRepository.findByNameAndCategory(name,categoryId);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
