# Product Selling Site

This is a Spring Boot application that simulates an interactive product selling website similar to Amazon.

## Features

- Product catalog listing with images, descriptions, and prices
- Product detail pages
- Shopping cart (session-based)
- Checkout and order placement
- User registration and login (Spring Security with BCrypt)
- Order history
- H2 in-memory database for development

## Technology Stack

- Java 11
- Spring Boot 2.7.6
  - Spring MVC
  - Spring Data JPA
  - Spring Security
  - Thymeleaf
- H2 Database (in-memory)
- Maven
- Docker (optional)

## Getting Started

### Prerequisites

- Java 11
- Maven 3.6+

### Build and Run

1. Clone the repo:
   ```bash
   git clone https://github.com/youruser/product-selling-site.git
   cd product-selling-site
   ```
2. Build:
   ```bash
   mvn clean package
   ```
3. Run:
   ```bash
   mvn spring-boot:run
   ```
4. Open in browser:
   http://localhost:8080/products

### H2 Console

Access H2 console at http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:shopdb`
- User: `sa`
- Password: (leave blank)

### Docker

To build and run with Docker:

```bash
mvn clean package -DskipTests
docker build -t product-selling-site .
docker run -p 8080:8080 product-selling-site
```

## Folder Structure

```
├── src/main/java/com/example/shop
│   ├── config          // Security config
│   ├── exception       // Global exception handlers
│   ├── model/entity   // JPA entities
│   ├── repository      // Spring Data JPA repos
│   ├── service        // Service interfaces and impl
│   ├── web/controller // MVC controllers
│   └── web/dto        // Data transfer objects
├── src/main/resources
│   ├── templates      // Thymeleaf templates
│   └── static/css     // Styles
├── Dockerfile
├── pom.xml
└── README.md
```

## Future Improvements

- Persist cart in database for logged-in users
- Product search and filtering
- Admin console for managing products
- Email notifications
- Payment gateway integration

## License

MIT License
