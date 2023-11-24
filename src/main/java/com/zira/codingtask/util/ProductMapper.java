package com.zira.codingtask.util;

import com.zira.codingtask.controller.model.PriceHistoryDTO;
import com.zira.codingtask.controller.model.ProductDTO;
import com.zira.codingtask.repository.model.PriceHistory;
import com.zira.codingtask.repository.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductDTO fromDB(Product product) {

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setViews(product.getViews());
        dto.setImage(product.getImage());
        dto.setGeog(product.getGeog());
        dto.setPriceHistory(null);
        return dto;
    }

    public static ProductDTO fromDB(Product product, List<PriceHistory> priceHistory) {

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setViews(product.getViews());
        dto.setImage(product.getImage());
        dto.setGeog(product.getGeog());

        List<PriceHistoryDTO> priceHistoryDTO = priceHistory.stream()
                .map(x -> fromPriceHistoryDB(x))
                .collect(Collectors.toList());

        dto.setPriceHistory(priceHistoryDTO);

        return dto;
    }

    private static PriceHistoryDTO fromPriceHistoryDB(PriceHistory priceHistory) {


        PriceHistoryDTO priceHistoryDTO = new PriceHistoryDTO();
        priceHistoryDTO.setId(priceHistory.getId());
        priceHistoryDTO.setProductId(priceHistory.getProductId());
        priceHistoryDTO.setPrice(priceHistory.getPrice());
        LocalDateTime localDateTime = LocalDateTime.now();
        priceHistoryDTO.setTimestamp(priceHistory.getTimestamp());

        return priceHistoryDTO;
    }
}
