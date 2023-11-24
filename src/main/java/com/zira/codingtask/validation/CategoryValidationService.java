package com.zira.codingtask.validation;

import com.zira.codingtask.controller.model.CreateCategoryBody;
import com.zira.codingtask.controller.model.UpdateCategoryBody;
import com.zira.codingtask.exception.ValidationException;

public class CategoryValidationService {

    public static void createCategoryFieldsValidation(CreateCategoryBody createCategoryBody) {

        if (createCategoryBody.getName() == null || createCategoryBody.getName().equals("")) {
            throw new ValidationException("Category name can't be empty");
        } else if (createCategoryBody.getName().length() > 30) {
            throw new ValidationException("Name for category is too long");
        }
    }

    public static void updateCategoryFieldsValidation(UpdateCategoryBody updateCategoryBody) {

        if (updateCategoryBody.getName() == null || updateCategoryBody.getName().equals("")) {
            throw new ValidationException("Category name can't be empty");
        } else if (updateCategoryBody.getName().length() > 30) {
            throw new ValidationException("Name for category is too long");
        }
    }
}
