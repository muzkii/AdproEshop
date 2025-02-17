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

    @Test
    void testCreateProduct_WithValidId_ShouldStoreProduct() {
        Product product = new Product();
        product.setProductId("valid-id");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        
        Product createdProduct = productRepository.create(product);
        
        assertNotNull(createdProduct);
        assertEquals("valid-id", createdProduct.getProductId());
        assertEquals("Test Product", createdProduct.getProductName());
    }

    @Test
    void testCreateProduct_WithNullId_ShouldGenerateUUID() {
        Product product = new Product();
        product.setProductName("Generated ID Product");
        product.setProductQuantity(5);

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId(), "Product ID should be auto-generated");
        assertFalse(createdProduct.getProductId().isEmpty(), "Product ID should not be empty");
    }

    @Test
    void testFindAll_ShouldReturnAllProducts() {
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        
        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(15);
        
        productRepository.create(product1);
        productRepository.create(product2);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testFindById_ShouldReturnCorrectProduct() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Find Me");
        product.setProductQuantity(20);

        productRepository.create(product);

        Product foundProduct = productRepository.findById("123");
        assertNotNull(foundProduct);
        assertEquals("Find Me", foundProduct.getProductName());
        assertEquals(20, foundProduct.getProductQuantity());
    }

    @Test
    void testFindById_WithNonExistingId_ShouldReturnNull() {
        Product foundProduct = productRepository.findById("non-existing-id");
        assertNull(foundProduct);
    }

    @Test
    void testUpdateProduct_ShouldModifyExistingProduct() {
        Product product = new Product();
        product.setProductId("update-id");
        product.setProductName("Old Name");
        product.setProductQuantity(5);

        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("update-id");
        updatedProduct.setProductName("New Name");
        updatedProduct.setProductQuantity(15);

        Product result = productRepository.update(updatedProduct);

        assertNotNull(result);
        assertEquals("New Name", result.getProductName());
        assertEquals(15, result.getProductQuantity());
    }

    @Test
    void testUpdateProduct_WithNonExistingId_ShouldReturnNull() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("non-existing-id");
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(10);

        Product result = productRepository.update(updatedProduct);

        assertNull(result, "Update should return null if product does not exist");
    }

    @Test
    void testDeleteProduct_ShouldRemoveProduct() {
        Product product = new Product();
        product.setProductId("delete-id");
        product.setProductName("Delete Me");
        product.setProductQuantity(5);

        productRepository.create(product);
        productRepository.delete("delete-id");

        assertNull(productRepository.findById("delete-id"));
    }

    @Test
    void testDeleteProduct_WithNonExistingId_ShouldNotAffectList() {
        Product product = new Product();
        product.setProductId("keep-id");
        product.setProductName("Keep Me");
        product.setProductQuantity(5);

        productRepository.create(product);
        productRepository.delete("non-existing-id");

        assertNotNull(productRepository.findById("keep-id"));
    }

    @Test
    void testCreateProduct_WithEmptyId_ShouldGenerateUUID() {
        Product product = new Product();
        product.setProductId(""); // Empty ID
        product.setProductName("Empty ID Product");
        product.setProductQuantity(5);

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId(), "Product ID should be auto-generated");
        assertFalse(createdProduct.getProductId().isEmpty(), "Product ID should not be empty");
    }
    @Test
void testUpdateProduct_Success_ProductIdMatches() {
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
void testUpdateProduct_Fail_ProductIdDoesNotMatch() {
    // Arrange
    Product product = new Product();
    product.setProductId("123");
    product.setProductName("Old Name");
    product.setProductQuantity(10);
    productRepository.create(product);

    // Act
    Product updatedProduct = new Product();
    updatedProduct.setProductId("456"); // Different ID
    updatedProduct.setProductName("New Name");
    updatedProduct.setProductQuantity(20);

    Product result = productRepository.update(updatedProduct);

    // Assert
    assertNull(result, "Update should return null if product ID does not match");
}
}
