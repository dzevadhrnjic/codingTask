package com.zira.codingtask.controller.model;

import lombok.Data;

@Data
public class UpdateUserBody {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}
