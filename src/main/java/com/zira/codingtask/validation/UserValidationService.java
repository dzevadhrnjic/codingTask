package com.zira.codingtask.validation;

import com.zira.codingtask.controller.model.CreateUserBody;
import com.zira.codingtask.controller.model.UpdateUserBody;
import com.zira.codingtask.exception.ValidationException;

public class UserValidationService {

    public static void createUserFieldsValidation(CreateUserBody createUserBody) {

        createUserNameValidation(createUserBody);
        createUserAddressValidation(createUserBody);
        createUserPhoneNumberValidation(createUserBody);
        userEmailValidation(createUserBody);
        userPasswordValidation(createUserBody);
    }

    public static void createUserNameValidation(CreateUserBody createUserBody) {

        if (createUserBody.getName() == null || createUserBody.getName().isEmpty()) {
            throw new ValidationException("First name field required");
        } else if (createUserBody.getName().length() > 50) {
            throw new ValidationException("First name is too long, maximum is 50 characters");
        }
    }

    public static void createUserAddressValidation(CreateUserBody createUserBody) {
        if (createUserBody.getAddress() == null || createUserBody.getAddress().isEmpty()) {
            throw new ValidationException("Address field required");
        } else if (createUserBody.getAddress().length() > 100) {
            throw new ValidationException("Address is too long, maximum is 100 characters");
        }
    }

    public static void createUserPhoneNumberValidation(CreateUserBody createUserBody) {
        if (createUserBody.getPhoneNumber() == null || createUserBody.getPhoneNumber().isEmpty()) {
            throw new ValidationException("Phone number field is required");
        } else if (createUserBody.getPhoneNumber().length() != 11) {
            throw new ValidationException("Enter field phone number with +, and 10 numbers");
        } else if (!createUserBody.getPhoneNumber().startsWith("+")) {
            throw new ValidationException("Enter field phone number with +");
        }
    }

    public static void userEmailValidation(CreateUserBody createUserBody) {
        if (createUserBody.getEmail() == null || createUserBody.getEmail().isEmpty()) {
            throw new ValidationException("Email field is required");
        } else if (createUserBody.getEmail().length() > 30) {
            throw new ValidationException("Email is too long, maximum is 30 characters");
        }
    }

    public static void userPasswordValidation(CreateUserBody createUserBody) {
        if (createUserBody.getPassword() == null || createUserBody.getPassword().isEmpty()) {
            throw new ValidationException("Password field is required");
        } else if (createUserBody.getPassword().length() > 30) {
            throw new ValidationException("Password is too long, maximum is 30 characters");
        }
    }

    public static void updateUserFieldsValidation(UpdateUserBody updateUserBody) {

        updateUserNameValidation(updateUserBody);
        updateUserAddressValidation(updateUserBody);
        updateUserPhoneNumberValidation(updateUserBody);
        updateUserEmailValidation(updateUserBody);
    }

    public static void updateUserNameValidation(UpdateUserBody updateUserBody) {

        if (updateUserBody.getName() == null || updateUserBody.getName().isEmpty()) {
            throw new ValidationException("First name field required");
        } else if (updateUserBody.getName().length() > 50) {
            throw new ValidationException("First name is too long, maximum is 50 characters");
        }
    }

    public static void updateUserAddressValidation(UpdateUserBody updateUserBody) {
        if (updateUserBody.getAddress() == null || updateUserBody.getAddress().isEmpty()) {
            throw new ValidationException("Address field required");
        } else if (updateUserBody.getAddress().length() > 100) {
            throw new ValidationException("Address is too long, maximum is 100 characters");
        }
    }

    public static void updateUserPhoneNumberValidation(UpdateUserBody updateUserBody) {
        if (updateUserBody.getPhoneNumber() == null || updateUserBody.getPhoneNumber().isEmpty()) {
            throw new ValidationException("Phone number field is required");
        } else if (updateUserBody.getPhoneNumber().length() != 11) {
            throw new ValidationException("Enter field phone number with +, and 10 numbers");
        } else if (!updateUserBody.getPhoneNumber().startsWith("+")) {
            throw new ValidationException("Enter field phone number with +");
        }
    }

    public static void updateUserEmailValidation(UpdateUserBody updateUserBody) {
        if (updateUserBody.getEmail() == null || updateUserBody.getEmail().isEmpty()) {
            throw new ValidationException("Email field is required");
        } else if (updateUserBody.getEmail().length() > 30) {
            throw new ValidationException("Email is too long, maximum is 30 characters");
        }
    }
}
