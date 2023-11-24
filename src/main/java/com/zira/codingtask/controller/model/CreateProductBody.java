package com.zira.codingtask.controller.model;

import lombok.Data;

@Data
public class CreateProductBody {

    private String name;
    private String description;
    private Long category;
    private double price;
    private int views;
    private String image;
    private String coordinates;
}
