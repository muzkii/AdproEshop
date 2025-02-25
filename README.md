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

#### Code Coverage 

![image](https://github.com/user-attachments/assets/f95a6478-fdb3-4104-a497-2ebf3994b5a2)

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

4. Low Maintanability : "Remove the declaration of thrown exception 'java.lang.Exception', as it cannot be thrown from method's body on `CreateProductFunctionalTest.java`"

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

5. Low Maintanability : "Remove the declaration of thrown exception 'java.lang.Exception', as it cannot be thrown from method's body on `HomePageFunctionalTest.java`"

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

6. High Maintanability : "@Autowired Remove this field injection and use constructor injection instead on `ProductController.java`"

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

Additionally, the integration of SonarCloud analysis in `sonarcloud.yml` ensures that code quality and security vulnerabilities are continuously assessed. This process helps maintain high-quality code. The use of the OpenSSF Scorecard workflow further strengthens security by analyzing the supply chain risks associated with the project, which is an important aspect of maintaining software integrity in CI/CD.

For deployment, the `deploy.yml` workflow automates the process of building and pushing Docker images to Docker Hub and subsequently deploying to Koyeb. While Koyeb's autobuilder may replace the need for manual deployment, having a defined CI/CD workflow for deployment ensures consistency. However, since Koyeb uses an autobuilder, the explicit deployment steps may not be necessary.

Overall, the current implementation meets the core definition of CI/CD by automating code testing, quality analysis, and deployment processes. However, improvements could be made by solving some of the code quality issues stated on SonarCloud. Even though I have already fixed 90% of them, there is still some leftovers.
   
## Module 3 

### Reflection 

#### SOLID Principles That I Applied to My Project
   In this project, I have applied three **SOLID** principles—**Single Responsibility Principle (SRP), Open/Closed Principle, and Liskov Subtitution Principle (LSP)**—to improve maintanability and as way to learn development conventions.
   
1. **SRP (Single-Responsibility Principle)**

      The Single Responsibility Principle (SRP) states that a class or module should have one responsibility. Initially, we have already implemented this bty having Services such as `CarServiceImpl` and `ProductServiceImpl` to handle all the logic, meanwhile we would have Repositories `CarRepository` and `ProductRepository` to focus on data persistence. I applied this to `ProductController.java` in the Controller module by moving `CarController` class that originally extends the `ProductController` class to a new file called `CarController.java`. This way, both `CarController.java` and `ProductController.java` only handles the data access of respective objects.

2. **OCP (Open/Closed Principle)**

      The Open/Closed Principle (OCP) states that software entities should be open for extension but closed for modification. Previously, adding a new Repository required modifying existing Service classes. We can change that by implementing OCP. Here, we would have two new files called `IProductRepository.java` and `ICarRepository.java` which is basically an interface for both of the respective repositories. So, the original `CarRepository.java` and `ProductRepository.java` both implements their own interface repository. Now, with the help of OCP, new repositories extend the existing interfaces without affecting old implementations. For example, if we need a new repository called `MotorcycleRepository`, we can implement a new interface without changing old code.

3. **LSP (Liskov Substitution Principle)**

      The Liskov Subtitution Principle (LSP) states that objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program. Here, I added a new file called `IRepository.java` that acts as a base for both `ICarRepository` and `IProductRepository`. This would ensure that any future repositories can be swapped in without breaking functionality. I applied it to `ICarRepository.java`, `IProductRepository.java`, `CarRepository.java`, and `ProductRepository.java`. 
   
#### Advantages of Applying SOLID Principles 

1. **Easier to Modify and Scale**
   
   i.       After applying SOLID Principles, if we need to add new functionality, OCP ensures that we don't have to modify existing classes.

   ii.      SRP makes each class easier to understand and update without unintended side effects.

   Example:

   Suppose we need to add a new entity called **Motorcycle** alongisde Car and Product. Because of OCP, we only need to create `IBikeRepository` and `BikeRepository` without modifying existing repository classes. Services can directly interact wth the `IBikeRepository` interface, ensuring consistency across all product types. **Without OCP**, we have to modify `CarServiceImpl` and `ProductServiceImpl` everytime a new repository is added, this could break or make an error on breaking existing functionality.

   - Before (OCP Violation)
     ```java
     ...
      import java.util.UUID; 
      
      @Repository
      public class CarRepository {
      
          static int id = 0 ;
          private List<Car> carData = new ArrayList<>();
     ...
     ```
   - After (OCP Applied)
     ```java
     ...
      import java.util.UUID; 
      
      @Repository
      public class CarRepository implements ICarRepository {
      
          static int id = 0 ;
          private List<Car> carData = new ArrayList<>();
     ...
     ```
     
2. **More Flexible Code Structure**
   
   i.       LSP ensures that all repositories behave consistently, allowing us to swap implementations seamlessly.

   Example:

   Right now, both `ICarRepository` and `IProductRepository` follow a similar contract with methods like `create()`, `findAll()`, `findById()`, `delete()`. Because of LSP, we can create a generic interface like `IRepository<T>` to enforce consistency accross all repositories. **Without LSP**, if `CarRepository` returned `Iterator<Car>` but `ProductRepository` returned `List<Product>`, the service layer would have to handle them differently, making the code harder to manage.

   - Before (LSP Violation)
     ```java
      public interface ICarRepository {
          Car create(Car car);
          Iterator<Car> findAll();
          Car findById(String carId);
          Car update(String carId, Car car);
          void delete(String carId);
      }
      
      public interface IProductRepository {
          Product create(Product product);
          Iterator<Product> findAll();
          Product findById(String productId);
          Product update(Product product);
          void delete(String productId);
      }
     ```
   - After (LSP Applied)
     ```java
      public interface IRepository<T> {
          T create(T entity);
          Iterator<T> findAll();
          T findById(String id);
          T update(String id, T entity);
          void delete(String id);
      }

     public interface ICarRepository extends IRepository<Car> {}
     public interface IProductRepository extends IRepository<Product> {}
     ```
      
3. **Improved Readability and Maintainability**

   i.       SRP ensures that each class has a clear and well-defined responsibility, making the codebase easier to navigate.

   Example:

   Before applying SRP, `ProductController` was handling both mapping for `Product` and `Car`. After refactoring `ProductController.java` only handles mapping for products, meanwhile `CarController.java` handles mapping for cars. **Without SRP**, if the implementation were mixed into the repository, any future changes would require modifyign repository code.

   - Before (SRP Violation)
     ```java
     // ProductController.java
     ...
      @Controller
      @RequestMapping("/car")
      class CarController extends ProductController {
      
          @Autowired
          private CarServiceImpl carservice;
      
          public CarController(ProductService service) {
              super(service);
          }
     ...
     ```
   - After (SRP Applied)
     ```java
     // CarController.java
     ...
      import org.springframework.ui.Model;
      import org.springframework.web.bind.annotation.*;
      
      import java.util.List;
      
      @Controller
      @RequestMapping("/car")
      public class CarController {
      
          private final CarService carservice;
      
          public CarController(CarService carService) {
              this.carservice = carService;
          }
     ...
     ```
   
#### Disadvantages of Not Applying SOLID Principles

1. **Harder to Extend (OCP Violation)**

   Without OCP, adding a new repository requirees modifying existing service.

   Example:

   If `CarServiceImpl` directly depends on `CarRepository`, adding a new `MotorcycleRepository` means we must modify the service class
   
2. **Inconsistent and Error-Prone Code (LSP Violation)**

   Example:

   If `findAll()` returns different types across repositories, the service layer must handle each case separately, leading to bugs and inconsistent behavior. At first, `ICarRepository` returns an Iterator, meanwhile `IProductRepository` returns a List.

   - Before (LSP Violation)
     ```java
      public interface ICarRepository {
          Iterator<Car> findAll();  // Returns Iterator
      }
      
      public interface IProductRepository {
          List<Product> findAll();  // Returns List
      }
     ```
   - After (LSP Applied)
     ```java
     // CarController.java
      public interface IRepository<T> {
          Iterator<T> findAll();
      }
     ```
     
3. **Code Becomes Harder to Maintain (SRP Violation)**

   Example:

   If we want to have a new `MotorcycleController`, we can't just straight extends it to the `ProductController`.

   

By applying SRP, OCP, and LSP, we have improved the modularity, scalability, and maintainability of our project. Future modifications and extensions can be implemented without modifying existing functionality, ensuring a more stable and flexible codebase.
