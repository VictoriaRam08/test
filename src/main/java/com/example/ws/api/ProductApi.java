package com.example.ws.api;

import com.example.ws.domain.Product;
import com.example.ws.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductApi {
    private final ProductService productService;

    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:7070/api/products
    @GetMapping
    public List<Product> findAll(){
        return productService.loadAll();
    }

    // http://localhost:7070/api/products/search?keyword
    // This it's to search an specific word
    @GetMapping("/search")
    public List<Product> search(@RequestParam String keyword){ // i need to put the word keyword in the path to work
        return productService.search(keyword);
    }

    @PostMapping // map to post request
    public ResponseEntity<Product> addNew(@RequestBody Product product){
        productService.insert(product);
        return ResponseEntity.status(201).body(product); // status create
    }

}
