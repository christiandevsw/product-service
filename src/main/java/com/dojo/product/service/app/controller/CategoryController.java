package com.dojo.product.service.app.controller;

import com.dojo.product.service.app.model.Category;
import com.dojo.product.service.app.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        try {
            return new ResponseEntity<Iterable<Category>>(categoryService.listCategories(), HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new-category")
    public ResponseEntity<?> createNewCategory(@RequestBody Category category) {
        try {
            Category newCategory = categoryService.save(category);
            return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<?> updateCurrentCategory(@RequestBody Category category, @PathVariable Long id) {
        Category currentCategory;
        try {
            currentCategory = categoryService.getById(id);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (currentCategory == null) {
            return new ResponseEntity<String>("No existe categoria en la BD para el id: " + id, HttpStatus.NOT_FOUND);
        }
        currentCategory.setName(category.getName());
        currentCategory.setDescription(category.getDescription());
        Category categoryUpdated=categoryService.save(currentCategory);
        return new ResponseEntity<Category>(categoryUpdated, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategorie(@PathVariable Long id){
        try {
            categoryService.deleteById(id);
            return new ResponseEntity<String>("Se eliminó correctamente la categoria con id: "+id, HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
