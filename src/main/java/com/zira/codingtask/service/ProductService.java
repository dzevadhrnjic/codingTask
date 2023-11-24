package com.zira.codingtask.service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.zira.codingtask.controller.model.CreateProductBody;
import com.zira.codingtask.controller.model.ProductDTO;
import com.zira.codingtask.controller.model.UpdateProductBody;
import com.zira.codingtask.repository.PriceHistoryRepository;
import com.zira.codingtask.repository.model.PriceHistory;
import com.zira.codingtask.repository.ProductRepository;
import com.zira.codingtask.repository.ProductSpecification;
import com.zira.codingtask.exception.ValidationIdException;
import com.zira.codingtask.repository.model.Product;
import com.zira.codingtask.validation.ProductValidationService;
import com.zira.codingtask.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public static final int DISTANCE_IN_METERS = 1000;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PriceHistoryRepository priceHistoryRepository;

    public List<ProductDTO> getProducts(Integer pageNumber, Integer pageSize, String order, String productName) {

        Pageable paging = PageRequest.of(pageNumber, pageSize);

        Specification<Product> specification = Specification.where(null);

        if (!productName.isEmpty()) {
            specification = specification.and(ProductSpecification.hasName(productName));
        }

        if (order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc")) {
            specification = specification.and(ProductSpecification.orderAscOrDesc(order));
        }

        List<ProductDTO> result = productRepository.findAll(specification, paging)
                .stream()
                .map(x -> ProductMapper.fromDB(x))
                .collect(Collectors.toList());

        return result;
    }

    public List<ProductDTO> getClosestProducts(String coordinates) {
        String[] points = coordinates.split(",");
        if (points.length != 2) throw new IllegalArgumentException("Excepted lang and lat values");

        return productRepository.getClosestProducts(Double.valueOf(points[0]), Double.valueOf(points[1]), DISTANCE_IN_METERS)
                .stream()
                .map(x -> ProductMapper.fromDB(x))
                .collect(Collectors.toList());
    }

    public ProductDTO getProduct(Long id) {

        Product product = productRepository.getProductById(id);

        if (product == null) {
            throw new ValidationIdException("No product with that id");
        }

        List<PriceHistory> priceHistory = priceHistoryRepository.findPriceForProductHistory(product.getId());

        product.setViews(product.getViews() + 1);
        productRepository.save(product);

        return ProductMapper.fromDB(product, priceHistory);
    }

    public ProductDTO addProduct(CreateProductBody createProductBody) {

        ProductValidationService.createProductFieldsValidation(createProductBody);

        Product product = new Product();
        product.setName(createProductBody.getName());
        product.setDescription(createProductBody.getDescription());
        product.setCategory(createProductBody.getCategory());
        product.setPrice(createProductBody.getPrice());
        product.setViews(0);
        product.setImage(createProductBody.getImage());


        String[] points = createProductBody.getCoordinates().split(",");
        if (points.length != 2) throw new IllegalArgumentException("Excpected lang and lat values");

        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(Double.parseDouble(points[0]), Double.parseDouble(points[1]));
        Point point = geometryFactory.createPoint(coordinate);

        product.setGeog(point);

        productRepository.save(product);

        saveProductHistory(product);

        return ProductMapper.fromDB(product, new ArrayList<>());
    }

    public ProductDTO updateProduct(Long id, UpdateProductBody updateProductBody) {

        ProductValidationService.updateProductFieldsValidation(updateProductBody);
        ProductDTO productDTO = getProduct(id);

        Product product = new Product();
        product.setId(id);
        product.setName(updateProductBody.getName());
        product.setDescription(updateProductBody.getDescription());
        product.setCategory(updateProductBody.getCategory());
        product.setPrice(updateProductBody.getPrice());
        product.setViews(productDTO.getViews() - 1);
        product.setImage(updateProductBody.getImage());
        product.setGeog(productDTO.getGeog());

        productRepository.save(product);

        if (productDTO.getPrice() != updateProductBody.getPrice()) {
            saveProductHistory(product);
        }

        return ProductMapper.fromDB(product, new ArrayList<>());
    }

    public void deleteProduct(Long id) {

        getProduct(id);
        productRepository.deleteById(id);
    }

    public void saveProductHistory(Product product) {

        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setProductId(product.getId());
        priceHistory.setPrice(product.getPrice());
        LocalDateTime localDateTime = LocalDateTime.now();
        priceHistory.setTimestamp(localDateTime);

        priceHistoryRepository.save(priceHistory);
    }
}
