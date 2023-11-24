package com.zira.codingtask.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

   private Long id;
   private String name;
   private String description;
   private Long category;
   private double price;
   private int views;
   private String image;
   @JsonIgnore
   private Point geog;
   public List<PriceHistoryDTO> priceHistory;
}
