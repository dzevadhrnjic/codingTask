package com.zira.codingtask.util;

import com.zira.codingtask.controller.model.PriceHistoryDTO;
import com.zira.codingtask.repository.model.PriceHistory;

public class PriceHistoryMapper {
    public static PriceHistoryDTO fromDB(PriceHistory priceHistory){

        PriceHistoryDTO dto = new PriceHistoryDTO();

        dto.setId(priceHistory.getId());
        dto.setProductId(priceHistory.getProductId());
        dto.setPrice(priceHistory.getPrice());
        dto.setTimestamp(priceHistory.getTimestamp());

        return dto;
    }
}
