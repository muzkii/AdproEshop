# Advanced Programming EShop
by Andriyo Averill Fahrezi, NPM of 2306172325

## Reflection 1

### Clean Code Principles Applied

1. **Single Responsibility Principle (SRP):**
   - Each class has a clear and well-defined responsibility. The `ProductController` handles HTTP requests, the `ProductService` manages business logic, and the `ProductRepository` is responsible for data storage.

2. **Meaningful Naming Conventions:**
   - Variables, methods, and class names are descriptive (`findById`, `update`, `delete`), making the code easier to read and understand.

3. **Code Reusability and Maintainability:**
   - Methods like `findById`, `update`, and `delete` are implemented in a modular way, reducing redundancy and making future modifications easier.

### Secure Coding Practices Applied

1. **Proper Input Validation:**
   - Forms in `editProduct.html` and `createProduct.html` enforce required fields to prevent null or empty values.

2. **Preventing Direct Object Manipulation:**
   - Product IDs are assigned using `UUID.randomUUID()`, reducing the risk of predictable or duplicated IDs.

3. **Safe URL Parameters:**
   - Thymeleaf templates safely pass `productId` using `th:href`, reducing the risk of injection attacks.

4. **Confirmation Before Deleting Data:**
   - The delete button includes a JavaScript confirmation prompt to prevent accidental deletions.

### Areas for Improvement

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

