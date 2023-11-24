package com.zira.codingtask.service;

import com.zira.codingtask.controller.model.CategoryDTO;
import com.zira.codingtask.controller.model.CreateCategoryBody;
import com.zira.codingtask.controller.model.UpdateCategoryBody;
import com.zira.codingtask.exception.ValidationIdException;
import com.zira.codingtask.repository.CategoryRepository;
import com.zira.codingtask.repository.model.Category;
import com.zira.codingtask.util.CategoryMapper;
import com.zira.codingtask.validation.CategoryValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryDTO> getCategories(Integer pageNumber, Integer pageSize) {

        Pageable paging = PageRequest.of(pageNumber, pageSize);

        List<CategoryDTO> result = categoryRepository.findAll(paging).
                stream()
                .map(x -> CategoryMapper.fromDb(x))
                .collect(Collectors.toList());

        return result;
    }

    public CategoryDTO getCategory(Long id) throws ValidationIdException {

        Category category = categoryRepository.getCategoryById(id);

        if (category == null) {
            throw new ValidationIdException("No category with that id");
        }

        return CategoryMapper.fromDb(category);
    }

    public CategoryDTO addCategory(CreateCategoryBody createCategoryBody) {

        CategoryValidationService.createCategoryFieldsValidation(createCategoryBody);

        Category category = new Category();
        category.setName(createCategoryBody.getName());

        categoryRepository.save(category);

        return CategoryMapper.fromDb(category);
    }

    public CategoryDTO updateCategory(Long id, UpdateCategoryBody updateCategoryBody) {

        CategoryValidationService.updateCategoryFieldsValidation(updateCategoryBody);
        getCategory(id);

        Category category = new Category();
        category.setId(id);
        category.setName(updateCategoryBody.getName());
        categoryRepository.save(category);

        return CategoryMapper.fromDb(category);
    }

    public void deleteCategory(Long id) {

        getCategory(id);
        categoryRepository.deleteById(id);
    }
}
