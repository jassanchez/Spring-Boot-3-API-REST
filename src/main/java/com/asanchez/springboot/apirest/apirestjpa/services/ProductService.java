package com.asanchez.springboot.apirest.apirestjpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asanchez.springboot.apirest.apirestjpa.entities.Product;
import com.asanchez.springboot.apirest.apirestjpa.repositories.IProductRepository;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> oProduct = productRepository.findById(id);
        if (oProduct.isPresent()) {
            Product productBd = oProduct.orElseThrow();

            productBd.setSku(product.getSku());
            productBd.setDescription(product.getDescription());
            productBd.setName(product.getName());
            productBd.setPrice(product.getPrice());

            return Optional.of(productRepository.save(productBd));
        }
        return oProduct;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Product product) {
        Optional<Product> oProduct = productRepository.findById(product.getId());

        oProduct.ifPresent(productBd -> {
            productRepository.delete(productBd);
        });

        return oProduct;
    }

    @Transactional
    @Override
    public Optional<Product> deleteById(Long id) {
        Optional<Product> oProducto = productRepository.findById(id);
        oProducto.ifPresent(productBd -> {
            productRepository.delete(productBd);
        });

        return oProducto;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }
}
