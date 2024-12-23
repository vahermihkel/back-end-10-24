package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.entity.Product;
import ee.mihkel.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

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
