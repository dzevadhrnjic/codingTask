package com.zira.codingtask.controller.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceHistoryDTO {

    private Long id;
    private Long productId;
    private Double price;
    private LocalDateTime timestamp;
}
