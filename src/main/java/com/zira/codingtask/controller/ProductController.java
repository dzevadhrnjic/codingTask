package com.zira.codingtask.controller;

import com.zira.codingtask.controller.model.CreateProductBody;
import com.zira.codingtask.controller.model.ProductDTO;
import com.zira.codingtask.controller.model.UpdateProductBody;
import com.zira.codingtask.exception.ValidationException;
import com.zira.codingtask.exception.ValidationIdException;
import com.zira.codingtask.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getProducts(@RequestParam("pageNumber") Integer pageNumber,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam(value = "order", required = false) String order,
                                        @RequestParam(value = "productName", required = false) String productName) {

        return productService.getProducts(pageNumber, pageSize, order, productName);
    }

    @GetMapping("closest")
    public List<ProductDTO> getClosestProducts(@RequestParam("coordinates") String coordinates) {
        return productService.getClosestProducts(coordinates);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") Long id) {

        try {
            ProductDTO product = productService.getProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (ValidationIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody CreateProductBody createProductBody) {

        try {
            ProductDTO addProduct = productService.addProduct(createProductBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(addProduct);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") Long id,
                                                @RequestBody UpdateProductBody updateProductBody) {

        try {
            ProductDTO addProduct = productService.updateProduct(id, updateProductBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(addProduct);
        } catch (ValidationIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
        } catch (ValidationIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
