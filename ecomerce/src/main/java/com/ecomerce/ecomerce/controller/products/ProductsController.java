package com.ecomerce.ecomerce.controller.products;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/products")
public class ProductsController {
    // TODO: Do the CRUD operations for products
    @GetMapping
    public String getMethodName() {
        return "Prueba exitosa";
    }
    

}
