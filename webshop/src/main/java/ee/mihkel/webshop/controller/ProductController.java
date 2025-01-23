package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.entity.Product;
import ee.mihkel.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
                                // categoryId --> lisame meie
    //      page, size, sort   parameetrid lisab Pageable automaatikaga
    // localhost:8080/public-products?categoryId=0&page=0&size=2&sort=title,asc
    // localhost:8080/public-products?categoryId=0&page=0&size=2&sort=price,desc
    @GetMapping("public-products")
    public Page<Product> getPublicProducts(Pageable pageable, @RequestParam Long categoryId) {
        if (categoryId == 0) {
            return productRepository.findAll(pageable);
        } else {
            return productRepository.findByCategory_Id(categoryId, pageable);
        }
    }

    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product) {
//        if (productRepository.findById(product.getId()).isEmpty()) {
            productRepository.save(product);
//        }
        return productRepository.findAll();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    // Patch --> ühe kindla välja muutmiseks (active -> inactive -> active). kogus++, kogus--
    // Put --> võimaldab kõiki välju muuta
    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product) {
//        if (productRepository.findById(product.getId()).isPresent()) {
            productRepository.save(product);
//        }
        return productRepository.findAll();
    }

    @GetMapping("product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @PutMapping("product-list")
    public List<Product> addProductList(@RequestBody List<Product> products) {
        productRepository.saveAll(products);
        return productRepository.findAll();
    }
}
