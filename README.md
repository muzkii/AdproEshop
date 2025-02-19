# Advanced Programming EShop
by Andriyo Averill Fahrezi, NPM of 2306172325

## Module 1

### Reflection 1

#### Clean Code Principles Applied

1. **Single Responsibility Principle (SRP):**
   - Each class has a clear and well-defined responsibility. The `ProductController` handles HTTP requests, the `ProductService` manages business logic, and the `ProductRepository` is responsible for data storage.

2. **Meaningful Naming Conventions:**
   - Variables, methods, and class names are descriptive (`findById`, `update`, `delete`), making the code easier to read and understand.

3. **Code Reusability and Maintainability:**
   - Methods like `findById`, `update`, and `delete` are implemented in a modular way, reducing redundancy and making future modifications easier.

#### Secure Coding Practices Applied

1. **Proper Input Validation:**
   - Forms in `editProduct.html` and `createProduct.html` enforce required fields to prevent null or empty values.

2. **Preventing Direct Object Manipulation:**
   - Product IDs are assigned using `UUID.randomUUID()`, reducing the risk of predictable or duplicated IDs.

3. **Safe URL Parameters:**
   - Thymeleaf templates safely pass `productId` using `th:href`, reducing the risk of injection attacks.

4. **Confirmation Before Deleting Data:**
   - The delete button includes a JavaScript confirmation prompt to prevent accidental deletions.

#### Areas for Improvement

1. **Implementing Proper Exception Handling:**
   - Currently, if an invalid product ID is provided in `findById`, it returns `null`. Instead, a proper exception (`ProductNotFoundException`) should be thrown and handled with `@ControllerAdvice`.

2. **Issues on Comments**
   - On `ProductServiceImpl.java` there are comments to state the newly added functions, making it somewhat useless because of having the function already explained by itself, e.g.
     
     ```java
      // Method to find the product by its ID, later used to edit and delete the products
      @Override
      public Product findById(String productId) {
          return productRepository.findById(productId);
      }
     ```

3. **CSRF Protection for Forms:**
   - Spring Security should be configured to prevent CSRF attacks, especially in POST requests.

By applying these improvements which I haven't made, the EShop can be more robust and secure.

### Reflection 2

After writing the unit tests, I feel that they provide a great level of confidence in the correctness of the program. They allow us to detect bugs early and ensure that each function behaves as expected. Writing tests also helps to better understand the code and its requirements. However, at times, creating tests can feel repetitive and time-consuming, especially when ensuring that all edge cases are covered.

The number of unit tests in a class depends on its complexity and the number of methods that require validation. Ideally, every method should have at least one corresponding test, but additional tests should be written to cover different scenarios, such as edge cases and invalid inputs.

To ensure that our unit tests are sufficient to verify our program, we can use code coverage tools. Code coverage measures the percentage of the codebase that is executed during testing. A high code coverage percentage (e.g., 80-90%) generally indicates a well-tested application. However, achieving 100% code coverage does not guarantee that the code is free of bugs. Some issues, such as logical errors or integration problems, may not be captured by unit tests alone.

Suppose after writing the `CreateProductFunctionalTest.java`, I was asked to create another functional test suite to verify the number of items in the product list. If I simply copy the previous test structure and make minor modifications, the new test suite may introduce code duplication and reduce code quality. This can lead to maintainability issues, as any future changes will need to be made in multiple places, increasing the risk of inconsistencies.

#### Potential clean code issues in this scenario include:

1. Code Duplication – Having similar test setup procedures and instance variables in multiple test classes leads to redundancy.

2. Lack of Reusability – Instead of reusing common functionality, each test suite may implement its own version of setup and test logic.

3. Poor Maintainability – If there are changes to the testing framework, URLs, or configurations, they need to be updated in multiple places, making the code harder to maintain.

#### To improve code cleanliness, we can:

1. Extract Common Setup Code – Move shared setup logic (e.g., server configuration, WebDriver initialization) to a base test class.

2. Use Parameterized Tests – Instead of writing multiple similar tests, use parameterized tests to check different input scenarios within a single method.

3. Modularize Helper Methods – Extract repeated test logic into utility methods, improving readability and reducing redundancy.

4. By following these improvements, we can ensure that our test suite remains maintainable, scalable, and efficient while still providing comprehensive coverage of the application's functionality.

## Module 2

### Reflection

#### Fixed Code Quality Issues

1. Low Maintainability : "Remove the declaration of unused imports on `HomeControllerTest.java`"

   Removing unused import, and that is `import org.springframework.ui.Model;`. To fix this it is very easy, just by deleting the line of code.
   - Before :
     
     ```java
      package id.ac.ui.cs.advprog.eshop.controller;

      import org.junit.jupiter.api.Test;
      import org.springframework.ui.Model;
      import static org.junit.jupiter.api.Assertions.assertEquals;
      ...
     ```
   - After :
  
     ```java
      package id.ac.ui.cs.advprog.eshop.controller;

      import org.junit.jupiter.api.Test;

      import static org.junit.jupiter.api.Assertions.assertEquals;
      ...
     ```

2. Low Maintanability : "Remove the declaration of unused imports on `ProductControllerTest.java`"

   Removing unused import, and that is `import org.mockito.InjectMocks;`. To fix this it is very easy, just by deleting the line of code.
   - Before :
     
     ```java
      ...
      import org.junit.jupiter.api.Test;
      import org.junit.jupiter.api.extension.ExtendWith;
      import org.mockito.InjectMocks;
      import org.mockito.Mock;
      ...
     ```
   - After :
  
     ```java
      ...
      import org.junit.jupiter.api.Test;
      import org.junit.jupiter.api.extension.ExtendWith;

      import org.mockito.Mock;
      ...
     ```

3. Low Maintanability : "Remove the declaration of unused imports on `ProductRepositoryTest.java`"

   Removing unused import, and that is `import org.mockito.InjectMocks;`. To fix this it is very easy, just by deleting the line of code.
   - Before :
     
     ```java
      ...
      import org.mockito.InjectMocks;
      import org.mockito.junit.jupiter.MockitoExtension;
      import org.springframework.boot.test.mock.mockito.MockBean;
      ...
     ```
   - After :
  
     ```java
      ...
      import org.mockito.InjectMocks;
      import org.mockito.junit.jupiter.MockitoExtension;

      ...
     ```

4. Low Maintanability : "Remove the declaration of thrown exception 'java.lang.Exception', as it cannot be thrown from method's body on `CreateProductFunctionalTest.java`

   Removing `throws Exception` on each of the function that uses it. To fix this it is very easy, just by deleting the Exception.
   - Before :
     
     ```java
      ...
      @Test
       void createProductPage_hasRequiredFields(ChromeDriver driver) throws Exception {
           driver.get(baseUrl + "/product/create");
      ...
     ```
   - After :
  
     ```java
      ...
      @Test
          void createProductPage_hasRequiredFields(ChromeDriver driver) {
              driver.get(baseUrl + "/product/create");
      ...
     ```

5. Low Maintanability : "Remove the declaration of thrown exception 'java.lang.Exception', as it cannot be thrown from method's body on `HomePageFunctionalTest.java`

   Removing `throws Exception` on each of the function that uses it. To fix this it is very easy, just by deleting the Exception.
   - Before :
     
     ```java
      ...
      @Test
          void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
              // Exercise
              driver.get(baseUrl);
              String pageTitle = driver.getTitle();
      ...

      ...
      @Test
          void welcomeMessage_homePage_isCorrect(ChromeDriver driver) throws Exception {
              // Exercise
              driver.get(baseUrl);
              String welcomeMessage = driver.findElement(By.tagName("h3"))
     ...
     ```
   - After :
  
     ```java
      ...
      @Test
          void pageTitle_isCorrect(ChromeDriver driver) {
              // Exercise
              driver.get(baseUrl);
              String pageTitle = driver.getTitle();
      ...

      ...
      @Test
          void welcomeMessage_homePage_isCorrect(ChromeDriver driver) {
              // Exercise
              driver.get(baseUrl);
              String welcomeMessage = driver.findElement(By.tagName("h3"))
     ...
     ```

6. High Maintanability : "@Autowired Remove this field injection and use constructor injection instead on `ProductController.java`

   Here it is a bit complicated. By using constructor injection, the dependencies are explicit and must be passed during an object’s construction. This avoids the possibility of instantiating an object in an invalid state and makes types more testable. Fields can be declared final, which makes the code easier to understand, as dependencies don’t change after instantiation. I used SonarQube's IDE to solve the issue by using constructor injection. The `ProductService` dependency is injected via the constructor, and the field is declared as final. By the changes, it would make the dependencies explicit and ensures that they are provided during the construction of the `ProductController` object.

   - Before :
     
     ```java
      ...
      package id.ac.ui.cs.advprog.eshop.controller;
      
      import id.ac.ui.cs.advprog.eshop.model.Product;
      import id.ac.ui.cs.advprog.eshop.service.ProductService;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.stereotype.Controller;
      import org.springframework.ui.Model;
      import org.springframework.web.bind.annotation.*;
      
      import java.util.List;
      
      @Controller
      @RequestMapping("/product")
      public class ProductController {
          
          @Autowired
          private ProductService service;
     ...
     ```
   - After :
  
     ```java
      package id.ac.ui.cs.advprog.eshop.controller;
      
      import id.ac.ui.cs.advprog.eshop.model.Product;
      import id.ac.ui.cs.advprog.eshop.service.ProductService;
      import org.springframework.stereotype.Controller;
      import org.springframework.ui.Model;
      import org.springframework.web.bind.annotation.*;
      
      import java.util.List;
      
      @Controller
      @RequestMapping("/product")
      public class ProductController {
          private final ProductService service;
          public ProductController(ProductService service) {
              this.service = service;
          }
     ...
     ```
     
7. Not related to Code Issue. But changes to `ProductController.java` makes `ProductControllerTest.java` to be changed too.

   Since we have removed InjectMocks, we would not be using it again. To fix this issue, we need to ensure that the `ProductControllerTest` class is properly set up to test the `ProductController` with constructor injection. Additionally, we need to make sure that the `ProductService` mock is correctly injected into the `ProductController`.

   - Before :
     
     ```java
      ...
          @Mock
          private ProductService productService;
      
          @InjectMocks
          private ProductController productController;
      
          private Product sampleProduct;
      ...
      class ProductControllerTest {
          @BeforeEach
          void setUp() {
              MockitoAnnotations.openMocks(this);
              sampleProduct = new Product();
              sampleProduct.setProductId("12345");
              sampleProduct.setProductName("Test Product");
     ...
     ```
   - After :
  
     ```java
     ...
          @Mock
          private ProductService productService;
      
          private ProductController productController;
      
          private Product sampleProduct;
      ...
      class ProductControllerTest {
          @BeforeEach
          void setUp() {
              MockitoAnnotations.openMocks(this);
              productController = new ProductController(productService);
              sampleProduct = new Product();
              sampleProduct.setProductId("12345");
              sampleProduct.setProductName("Test Product");
     ...
     ```

#### CI/CD Workflows Have Met The Definition of Continuous Integration and Continuous Deployment

**Yes**, the current implementation of the CI/CD pipeline follows the principles of Continuous Integration (CI) and Continuous Deployment (CD). The pipeline automatically runs test suites through the `ci.yml` workflow, ensuring that every push and pull request is validated before merging. This aligns with the concept of CI, where automated testing is essential to detect issues early in the development cycle.

Additionally, the integration of SonarCloud analysis in `sonarcloud.yml` ensures that code quality and security vulnerabilities are continuously assessed. This process provides valuable feedback to developers, helping maintain high-quality code. The use of the OpenSSF Scorecard workflow further strengthens security by analyzing the supply chain risks associated with the project, which is an important aspect of maintaining software integrity in CI/CD.

For deployment, the `deploy.yml` workflow automates the process of building and pushing Docker images to Docker Hub and subsequently deploying to Koyeb. While Koyeb's autobuilder may replace the need for manual deployment, having a defined CI/CD workflow for deployment ensures consistency. However, since Koyeb uses an autobuilder, the explicit deployment steps may not be necessary and could be optimized further.

Overall, the current implementation meets the core definition of CI/CD by automating code testing, quality analysis, and deployment processes. However, improvements could be made to streamline the deployment workflow, ensuring that only necessary steps are executed based on the specific PaaS requirements.
   
