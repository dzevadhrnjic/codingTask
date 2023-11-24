package com.zira.codingtask.controller;

import com.zira.codingtask.controller.model.CategoryDTO;
import com.zira.codingtask.controller.model.CreateCategoryBody;
import com.zira.codingtask.controller.model.UpdateCategoryBody;
import com.zira.codingtask.exception.ValidationException;
import com.zira.codingtask.exception.ValidationIdException;
import com.zira.codingtask.repository.model.Category;
import com.zira.codingtask.service.CategoryService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> getCategories(@Param("pageNumber") Integer pageNumber,
                                           @Param("pageSize") Integer pageSize) {

        return categoryService.getCategories(pageNumber, pageSize);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getCategory(@PathVariable("id") Long id) {

        try {
            CategoryDTO addCategory = categoryService.getCategory(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(addCategory);
        } catch (ValidationIdException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestBody CreateCategoryBody createCategoryBody) {
        try {
            CategoryDTO addCategory = categoryService.addCategory(createCategoryBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(addCategory);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable("id") Long id,
                                                 @RequestBody UpdateCategoryBody updateCategoryBody) {
        try {
            CategoryDTO updateCategory = categoryService.updateCategory(id, updateCategoryBody);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updateCategory);
        } catch (ValidationIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
        } catch (ValidationIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
