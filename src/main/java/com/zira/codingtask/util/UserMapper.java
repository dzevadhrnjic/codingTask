package com.zira.codingtask.util;

import com.zira.codingtask.controller.model.UserDTO;
import com.zira.codingtask.repository.model.User;

public class UserMapper {
    public static UserDTO fromDB(User user) {

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAddress(user.getAddress());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setEmail(user.getEmail());

        return dto;
    }
}
