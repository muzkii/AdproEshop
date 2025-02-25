package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setProductId("12345");
        sampleProduct.setProductName("Sample Product");
        sampleProduct.setProductQuantity(10);
    }

    @Test
    void testCreate() {
        when(productRepository.create(any(Product.class))).thenReturn(sampleProduct);

        Product createdProduct = productService.create(sampleProduct);

        assertNotNull(createdProduct);
        assertEquals("12345", createdProduct.getProductId());
        assertEquals("Sample Product", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());
        verify(productRepository, times(1)).create(sampleProduct);
    }

    @Test
    void testFindAll() {
        Product product2 = new Product();
        product2.setProductId("67890");
        product2.setProductName("Another Product");
        product2.setProductQuantity(5);

        List<Product> productList = Arrays.asList(sampleProduct, product2);
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Sample Product", result.get(0).getProductName());
        assertEquals("Another Product", result.get(1).getProductName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(productRepository.findById("12345")).thenReturn(sampleProduct);

        Product result = productService.findById("12345");

        assertNotNull(result);
        assertEquals("12345", result.getProductId());
        verify(productRepository, times(1)).findById("12345");
    }

    @Test
    void testUpdate() {
        when(productRepository.update(any(Product.class))).thenReturn(sampleProduct);

        Product updatedProduct = productService.update(sampleProduct);

        assertNotNull(updatedProduct);
        assertEquals("12345", updatedProduct.getProductId());
        verify(productRepository, times(1)).update(sampleProduct);
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete("12345");

        productService.delete("12345");

        verify(productRepository, times(1)).delete("12345");
    }
}
