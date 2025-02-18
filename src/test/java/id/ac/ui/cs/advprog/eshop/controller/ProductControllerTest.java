package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    private ProductController productController;

    private Product sampleProduct;
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
        sampleProduct = new Product();
        sampleProduct.setProductId("12345");
        sampleProduct.setProductName("Test Product");
        sampleProduct.setProductQuantity(10);

        model = mock(Model.class);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("createProduct", viewName);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        String result = productController.createProductPost(sampleProduct, model);
        assertEquals("redirect:list", result);
        verify(productService, times(1)).create(sampleProduct);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = Arrays.asList(sampleProduct);
        when(productService.findAll()).thenReturn(productList);

        String viewName = productController.ProductListPage(model);
        assertEquals("productList", viewName);
        verify(model, times(1)).addAttribute("products", productList);
    }

    @Test
    void testEditProductPage() {
        when(productService.findById("12345")).thenReturn(sampleProduct);

        String viewName = productController.editProductPage("12345", model);
        assertEquals("editProduct", viewName);
        verify(model, times(1)).addAttribute("product", sampleProduct);
    }

    @Test
    void testEditProductPage_NotFound() {
        when(productService.findById("99999")).thenReturn(null);

        String viewName = productController.editProductPage("99999", model);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPost() {
        String result = productController.editProductPost(sampleProduct);
        assertEquals("redirect:/product/list", result);
        verify(productService, times(1)).update(sampleProduct);
    }

    @Test
    void testDeleteProduct() {
        String result = productController.deleteProduct("12345");
        assertEquals("redirect:/product/list", result);
        verify(productService, times(1)).delete("12345");
    }
}