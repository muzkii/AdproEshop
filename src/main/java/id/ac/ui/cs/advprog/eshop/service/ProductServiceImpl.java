package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IProductRepository productRepository;
    
    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> allProduct = productRepository.findAll();
        return allProduct;
    }
    
    // Method to find the product by its ID, later used to edit and delete the products
    @Override
    public Product findById(String productId) {
        return productRepository.findById(productId);
    }

    // Method to edit the product
    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public void delete(String productId) {
        productRepository.delete(productId);
    }
}
