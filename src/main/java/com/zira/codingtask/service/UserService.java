package com.zira.codingtask.service;

import com.zira.codingtask.controller.model.CreateUserBody;
import com.zira.codingtask.controller.model.UpdateUserBody;
import com.zira.codingtask.controller.model.UserDTO;
import com.zira.codingtask.exception.ValidationIdException;
import com.zira.codingtask.repository.UserRepository;
import com.zira.codingtask.repository.model.User;
import com.zira.codingtask.util.HashUtils;
import com.zira.codingtask.util.UserMapper;
import com.zira.codingtask.validation.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    HashUtils hashUtils = new HashUtils();

    @Autowired
    UserRepository userRepository;

    public UserDTO getUserById(Long userId) {

        User user = userRepository.getUserById(userId);

        if (user == null) {
            throw new ValidationIdException("No user with that id");
        }

        return UserMapper.fromDB(user);
    }

    public UserDTO createUser(CreateUserBody createUserBody) {

        UserValidationService.createUserFieldsValidation(createUserBody);

        User user = new User();

        user.setName(createUserBody.getName());
        user.setAddress(createUserBody.getAddress());
        user.setPhoneNumber(createUserBody.getPhoneNumber());
        user.setEmail(createUserBody.getEmail());
        user.setPassword(hashUtils.generateHash(createUserBody.getPassword()));

        userRepository.save(user);

        return UserMapper.fromDB(user);
    }

    public UserDTO updateUser(Long userId, UpdateUserBody updateUserBody) {

        UserValidationService.updateUserFieldsValidation(updateUserBody);
        getUserById(userId);

        User user = new User();

        user.setId(userId);
        user.setName(updateUserBody.getName());
        user.setAddress(updateUserBody.getAddress());
        user.setPhoneNumber(updateUserBody.getPhoneNumber());
        user.setEmail(updateUserBody.getEmail());
        userRepository.save(user);

        return UserMapper.fromDB(user);
    }

    public void deleteUser(Long userId) {

        getUserById(userId);
        userRepository.deleteById(userId);
    }
}
