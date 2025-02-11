package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    // NEW TESTS FOR EDIT FEATURE
    @Test
    void testUpdateProduct_Success() {
        // Arrange
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Old Name");
        product.setProductQuantity(10);
        productRepository.create(product);

        // Act
        Product updatedProduct = new Product();
        updatedProduct.setProductId("123");
        updatedProduct.setProductName("New Name");
        updatedProduct.setProductQuantity(20);

        Product result = productRepository.update(updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("123", result.getProductId());
        assertEquals("New Name", result.getProductName());
        assertEquals(20, result.getProductQuantity());
    }

    @Test
    void testUpdateProduct_Fail_ProductNotFound() {
        // Arrange
        Product product = new Product();
        product.setProductId("non-existent");
        product.setProductName("Non-existent Product");
        product.setProductQuantity(50);

        // Act
        Product result = productRepository.update(product);

        // Assert
        assertNull(result);
    }

    // NEW TESTS FOR DELETE FEATURE
    @Test
    void testDeleteProduct_Success() {
        // Arrange
        Product product = new Product();
        product.setProductId("456");
        product.setProductName("To be deleted");
        product.setProductQuantity(5);
        productRepository.create(product);

        // Act
        productRepository.delete("456");
        Product deletedProduct = productRepository.findById("456");

        // Assert
        assertNull(deletedProduct);
    }

    @Test
    void testDeleteProduct_Fail_ProductNotFound() {
        // Act
        productRepository.delete("non-existent");

        // Assert
        // Ensure no exception is thrown, and nothing crashes
        assertTrue(true);
    }

    // POSITIVE TEST CASES FOR EDIT
    @Test
    void testUpdateProduct_Success_ProductUpdatedCorrectly() {
        // Create product
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Original Name");
        product.setProductQuantity(50);
        productRepository.create(product);

        // Update product
        Product updatedProduct = new Product();
        updatedProduct.setProductId("123");
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(75);

        Product result = productRepository.update(updatedProduct);
        assertNotNull(result);
        assertEquals("Updated Name", result.getProductName());
        assertEquals(75, result.getProductQuantity());
    }

    @Test
    void testUpdateProduct_Success_OnlySpecificFieldsChanged() {
        // Create product
        Product product = new Product();
        product.setProductId("789");
        product.setProductName("Test Product");
        product.setProductQuantity(30);
        productRepository.create(product);

        // Partial update: Only change quantity
        Product updatedProduct = new Product();
        updatedProduct.setProductId("789");
        updatedProduct.setProductName("Test Product"); // Same name
        updatedProduct.setProductQuantity(99); // New quantity

        Product result = productRepository.update(updatedProduct);
        assertNotNull(result);
        assertEquals("Test Product", result.getProductName()); // Name should be unchanged
        assertEquals(99, result.getProductQuantity()); // Quantity should be updated
    }

    @Test
    void testUpdateProduct2_Fail_ProductNotFound() {
        Product product = new Product();
        product.setProductId("999"); // This ID does not exist
        product.setProductName("Non-existent Product");
        product.setProductQuantity(10);

        Product result = productRepository.update(product);
        assertNull(result, "Updating a non-existent product should return null.");
    }

    @Test
    void testDeleteProduct_Success_ProductIsRemoved() {
        Product product = new Product();
        product.setProductId("456");
        product.setProductName("Item To Delete");
        product.setProductQuantity(20);
        productRepository.create(product);

        productRepository.delete("456");

        Product result = productRepository.findById("456");
        assertNull(result, "Product should be deleted and not found.");
    }

    @Test
    void testDeleteProduct_Success_NoOtherProductsAffected() {
        Product product1 = new Product();
        product1.setProductId("001");
        product1.setProductName("Product 1");
        product1.setProductQuantity(50);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("002");
        product2.setProductName("Product 2");
        product2.setProductQuantity(100);
        productRepository.create(product2);

        productRepository.delete("001"); // Delete first product

        assertNull(productRepository.findById("001")); // Should be deleted
        assertNotNull(productRepository.findById("002")); // Should still exist
    }

    @Test
    void testDeleteProduct2_Fail_ProductNotFound() {
        productRepository.delete("999"); // ID does not exist

        // Ensure that delete operation does not throw an error
        assertEquals(0, productRepository.findAll().hasNext() ? 1 : 0, "Repository should still be empty.");
    }
}
