package com.example.demo_spring_boot.service;

import com.example.demo_spring_boot.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product saveProduct(Product product);

    void deleteProduct(int id);

    Product getProductById(int id);

    List<Product> findByNameLike(String name);

    Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
