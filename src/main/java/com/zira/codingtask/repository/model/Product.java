package com.zira.codingtask.repository.model;

import lombok.Data;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long category;
    private double price;
    private int views;
    private String image;
    @Column(columnDefinition = "geography")
    private Point geog;
}
