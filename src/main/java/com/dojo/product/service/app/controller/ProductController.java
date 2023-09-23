package com.dojo.product.service.app.controller;

import com.dojo.product.service.app.model.Category;
import com.dojo.product.service.app.model.Product;
import com.dojo.product.service.app.service.CategoryService;
import com.dojo.product.service.app.service.ProductService;
import com.dojo.product.service.app.utility.DetailProductMixin;
import com.dojo.product.service.app.utility.ProductMixin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private static final Logger LOGGER= LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/total-products")
    public ResponseEntity<?> totalProducts() {
        try {
            return new ResponseEntity<String>(String.format("el total de productos es %d", productService.totalProducts()), HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list-by-category/{id}")
    public ResponseEntity<?> listProductsByCategory(@PathVariable Long id) {
        Category category;
        try {
            category = categoryService.getById(id);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (category == null)
            return new ResponseEntity<String>("No existe una categoria en la BD para el id: ".concat(id.toString()), HttpStatus.OK);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(Product.class, ProductMixin.class);
        List<Object> Products = productService.listProductsByCategory(id).stream().map(m -> {
            try {
                return objectMapper.readTree(objectMapper.writeValueAsString(m));
            } catch (JsonProcessingException e) {
                return m;
            }
        }).collect(Collectors.toList());

        return new ResponseEntity<List<Object>>(Products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(Product.class, DetailProductMixin.class);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.readTree(objectMapper.writeValueAsString(product)));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.OK).body("Sin foto");
        }
//        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping("/list-by-price/{price}")
    public ResponseEntity<?> listByPrice(@PathVariable Double price) {
        List<Product> products;
        try {
            products = productService.getProductsByPrice(price);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (products.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos para este precio");
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }


    @GetMapping("/available/{id}")
    public ResponseEntity<?> verifyStatusToProduct(@PathVariable Long id) {
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (product == null) {
            return new ResponseEntity<String>("No existe el producto en la BD!", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("producto", product);
        String availability = product.getAvailable() ? "Si" : "No";
        map.put("disponible", availability);

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @GetMapping("find/{categoryId}/{name}")
    public ResponseEntity<?> findProductByCategoryAndName(@PathVariable Long categoryId, @PathVariable String name) {
        List<Product> products;
        try {
            products = productService.listProductsByNameAndCategory(name, categoryId);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (products.size() == 0)
            return new ResponseEntity<String>("No se encontraron productos en la BD!", HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }


    @PostMapping(value = "/new-product",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createNewProduct(Product product, @RequestPart MultipartFile file){
        if (!file.isEmpty()) {
            try {
                product.setPhoto(file.getBytes());
            } catch (IOException e) {
                Map<String, Object> map = new HashMap<>();
                map.put("error", e.getCause().getMessage());
                map.put("message", "Ocurrió un error en la BD");
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
            }
        }
        try {
            Product newProduct = productService.save(product);
            return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update-product/{id}")
    public ResponseEntity<?> updateCurrentProduct(Product product, @PathVariable Long id, @RequestParam MultipartFile file)
            throws IOException {
        Product currentProduct;
        try {
            currentProduct = productService.getProductById(id);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (currentProduct == null) {
            return new ResponseEntity<String>("No existe el producto en la BD para el id: " + id, HttpStatus.NOT_FOUND);
        }
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setStock(product.getStock());
        currentProduct.setAvailable(product.getAvailable());
        currentProduct.setDscto(product.getDscto());
        if (!file.isEmpty()) {
            currentProduct.setPhoto(file.getBytes());
        }
        Product productUpdated = productService.save(currentProduct);
        return new ResponseEntity<Product>(productUpdated, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return new ResponseEntity<String>("Se eliminó correctamente el producto con id: " + id, HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
