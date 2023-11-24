package com.zira.codingtask.repository;

import com.zira.codingtask.repository.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> orderAscOrDesc(String order) {
        return (root, query, criteriaBuilder) -> {
            if (order.equalsIgnoreCase("asc")) {
                query.orderBy(criteriaBuilder.asc(root.get("id")));
            } else if (order.equalsIgnoreCase("desc")) {
                query.orderBy(criteriaBuilder.desc(root.get("id")));
            }
            return query.getRestriction();
        };
    }

    public static Specification<Product> hasName(String productName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), productName);
    }
}
