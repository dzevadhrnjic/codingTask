package com.zira.codingtask.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    @JsonIgnore
    private String password;
}
