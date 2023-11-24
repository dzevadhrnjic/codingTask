package com.zira.codingtask.controller.model;

import lombok.Data;

@Data
public class UpdateProductBody {

    private String name;
    private String description;
    private Long category;
    private double price;
    private String image;
    private String coordinates;
}
