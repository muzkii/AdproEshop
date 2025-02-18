package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    @Test
    void testHome() {
        HomeController homeController = new HomeController();
        String viewName = homeController.home();
        assertEquals("Home", viewName, "The home method should return the view name 'Home'");
    }
}