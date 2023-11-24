package com.zira.codingtask.repository;

import com.zira.codingtask.repository.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll(Specification<Product> specification, Pageable paging);

    @Query("select p from Product p where p.id = ?1")
    Product getProductById(Long id);

    @Query(value = "SELECT * FROM product " +
            "WHERE ST_DWithin(product.geog, ST_MakePoint(?1, ?2)\\:\\:geography, ?3) " +
            "ORDER BY product.geog <-> ST_MakePoint(?1, ?2)\\:\\:geography; ", nativeQuery = true)
    List<Product> getClosestProducts(Double lang, Double lat, int meters);
}
