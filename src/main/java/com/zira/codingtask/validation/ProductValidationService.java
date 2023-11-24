package com.zira.codingtask.validation;

import com.zira.codingtask.controller.model.CreateCategoryBody;
import com.zira.codingtask.controller.model.CreateProductBody;
import com.zira.codingtask.controller.model.UpdateProductBody;
import com.zira.codingtask.exception.ValidationException;

public class ProductValidationService {

    public static void createProductFieldsValidation(CreateProductBody createProductBody) {

        createProductNameValidation(createProductBody);
        createProductDescriptionValidation(createProductBody);
        createProductPriceValidation(createProductBody);
        createProductCategoryValidation(createProductBody);
    }

    public static void createProductNameValidation(CreateProductBody createProductBody) {
        if (createProductBody.getName() == null || createProductBody.getName().equals("")) {
            throw new ValidationException("Name for product can't be empty");
        } else if (createProductBody.getName().length() > 30) {
            throw new ValidationException("Name for product is too long");
        }
    }

    public static void createProductDescriptionValidation(CreateProductBody createProductBody) {
        if (createProductBody.getDescription() == null || createProductBody.getDescription().equals("")) {
            throw new ValidationException("Description for product can't be empty");
        } else if (createProductBody.getDescription().length() > 50) {
            throw new ValidationException("Description is too long");
        }
    }

    public static void createProductCategoryValidation(CreateProductBody createProductBody) {
        if (createProductBody.getCategory() == 0) {
            throw new ValidationException("Category can't be empty");
        }
    }

    public static void createProductPriceValidation(CreateProductBody createProductBody) {
        if (createProductBody.getPrice() == 0 || createProductBody.getPrice() < 0) {
            throw new ValidationException("Price for product can't be 0 or less than 0");
        }
    }

    public static void updateProductFieldsValidation(UpdateProductBody updateProductBody) {

        updateProductNameValidation(updateProductBody);
        updateProductDescriptionValidation(updateProductBody);
        updateProductPriceValidation(updateProductBody);
        updateProductCategoryValidation(updateProductBody);
    }

    public static void updateProductNameValidation(UpdateProductBody updateProductBody) {
        if (updateProductBody.getName() == null || updateProductBody.getName().equals("")) {
            throw new ValidationException("Name for product can't be empty");
        } else if (updateProductBody.getName().length() > 30) {
            throw new ValidationException("Name for product is too long");
        }
    }

    public static void updateProductDescriptionValidation(UpdateProductBody updateProductBody) {
        if (updateProductBody.getDescription() == null || updateProductBody.getDescription().equals("")) {
            throw new ValidationException("Description for product can't be empty");
        } else if (updateProductBody.getDescription().length() > 50) {
            throw new ValidationException("Description is too long");
        }
    }

    public static void updateProductCategoryValidation(UpdateProductBody updateProductBody) {
        if (updateProductBody.getCategory() == 0) {
            throw new ValidationException("Category can't be empty");
        }
    }

    public static void updateProductPriceValidation(UpdateProductBody updateProductBody) {
        if (updateProductBody.getPrice() == 0 || updateProductBody.getPrice() < 0) {
            throw new ValidationException("Price for product can't be 0 or less than 0");
        }
    }
}
