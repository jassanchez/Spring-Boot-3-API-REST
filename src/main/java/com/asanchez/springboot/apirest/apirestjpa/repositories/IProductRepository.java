package com.asanchez.springboot.apirest.apirestjpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asanchez.springboot.apirest.apirestjpa.entities.Product;

@Repository
public interface IProductRepository extends CrudRepository<Product, Long> {
    boolean existsBySku(String sku);
}
