package com.asanchez.springboot.apirest.apirestjpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asanchez.springboot.apirest.apirestjpa.entities.Product;
import com.asanchez.springboot.apirest.apirestjpa.services.IProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(originPatterns = "*", origins = { "http://localhost:5000" })
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;
    // @Autowired
    // private ProductValidation productValidation;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Product> oProduct = productService.findById(id);
        if (oProduct.isPresent()) {
            return ResponseEntity.ok(oProduct.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        // productValidation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        Product productDb = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDb);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Product product, BindingResult result) {
        // productValidation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        
        Optional<Product> oProduct = productService.update(id, product);
        if (oProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(oProduct.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> oProduct = productService.findById(id);

        if (oProduct.isPresent()) {
            Optional<Product> oProductDelete = productService.deleteById(id);
            return ResponseEntity.ok(oProductDelete.get());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}