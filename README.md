# Expense Tracker API

A secure backend REST API for managing personal expenses with JWT-based authentication and PostgreSQL persistence.

## Features :
- User Signup & Login with JWT Authentication
- Stateless Security using Spring Security
- User-specific Expense Management
- Create, Read, Update, Delete Expenses
- Advanced Filtering:
  - Category
  - Date Range
  - Amount Range
  - Pagination & Sorting
- PostgreSQL Database Integration


## Tech Stack :
* Backend: Java, Spring Boot
* Security: Spring Security, JWT
* Database: PostgreSQL, Hibernate, JPA
* Build Tool: Maven
* Testing Tool: Postman

## Authentication Flow :
1. User logs in and receives JWT token
2. Token sent in Authorization header
3. Custom JWT filter validates token
4. SecurityContext populated with user email
5. APIs allow access only to authenticated users

## API Endpoints :

    Auth:

        POST /api/auth/signup
        POST /api/auth/login

    Expenses (Protected):

        POST   /api/expenses
        GET    /api/expenses
        PUT    /api/expenses/{id}
        DELETE /api/expenses/{id}
        GET    /api/expenses/filter

## Database Configuration (PostgreSQL) :

    spring.datasource.url=jdbc:postgresql://localhost:5432/expense_tracker
    spring.datasource.username=postgres
    spring.datasource.password=your_password

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect


## How to Run:
1. Clone the repository
2. Configure PostgreSQL credentials in `application.properties`
3. Run `mvn spring-boot:run`


## Future Improvements :
- Dockerization
- Swagger API Documentation
- Role-based access
- Deployment (Render / Railway)