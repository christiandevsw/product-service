package com.dojo.product.service.app.controller;

import com.dojo.product.service.app.model.entity.Category;
import com.dojo.product.service.app.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        Category category;
        try {
            category = categoryService.getById(id);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error en la BD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la categoria");
        }
        //corregire esto de abajo pa que me cargue la foto en detalle
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.addMixIn(Product.class, DetailProductMixin.class);
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.readTree(objectMapper.writeValueAsString(product)));
//        } catch (JsonProcessingException e) {
//            return ResponseEntity.status(HttpStatus.OK).body("Sin foto");
//        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }



    @PostMapping("/new-category")
    public ResponseEntity<?> createNewCategory(Category category, @RequestPart MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                category.setPhoto(file.getBytes());
            } catch (IOException e) {
                Map<String, Object> map = new HashMap<>();
                map.put("error", e.getCause().getMessage());
                map.put("message", "Ocurrió un error en la BD");
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
            }
        }
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
    public ResponseEntity<?> updateCurrentCategory( Category category, @PathVariable Long id, @RequestParam MultipartFile file)
            throws IOException {
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
        if (!file.isEmpty()) {
            currentCategory.setPhoto(file.getBytes());
        }
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
