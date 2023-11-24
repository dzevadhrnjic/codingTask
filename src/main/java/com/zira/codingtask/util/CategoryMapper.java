package com.zira.codingtask.util;

import com.zira.codingtask.repository.model.Category;
import com.zira.codingtask.controller.model.CategoryDTO;

public class CategoryMapper {
    public static CategoryDTO fromDb(Category category) {

        CategoryDTO dto = new CategoryDTO();

        dto.setId(category.getId());
        dto.setName(category.getName());

        return dto;
    }
}
