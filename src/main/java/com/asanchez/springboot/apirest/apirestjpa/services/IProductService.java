package com.asanchez.springboot.apirest.apirestjpa.services;

import java.util.List;
import java.util.Optional;

import com.asanchez.springboot.apirest.apirestjpa.entities.Product;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> update(Long id, Product product);
    Optional<Product> delete(Product product);
    Optional<Product> deleteById(Long id);
    boolean existsBySku(String sku);
}
