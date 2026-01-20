# Expense Tracker API

A secure backend **REST API** for managing personal expenses, built with **Java and Spring Boot**, featuring **JWT-based authentication** and **PostgreSQL** persistence.

---

## 🚀 Features

* User Signup & Login with **JWT Authentication**
* Stateless security using **Spring Security**
* User-specific expense management
* Full **CRUD** operations for expenses
* Advanced filtering options:

  * Category
  * Date range
  * Amount range
* Pagination & sorting support
* PostgreSQL database integration

---

## 🛠 Tech Stack

* **Backend:** Java, Spring Boot
* **Security:** Spring Security, JWT
* **Database:** PostgreSQL, Hibernate, JPA
* **Build Tool:** Maven
* **Testing Tool:** Postman

---

## 🔐 Authentication Flow

1. User logs in and receives a **JWT token**
2. Token is sent in the `Authorization` header (`Bearer <token>`)
3. Custom JWT filter validates the token
4. `SecurityContext` is populated with the authenticated user’s email
5. APIs allow access **only to authenticated users**

---

## 📌 API Endpoints

### Auth

* `POST /api/auth/signup`
* `POST /api/auth/login`

### Expenses (Protected)

* `POST   /api/expenses`
* `GET    /api/expenses`
* `PUT    /api/expenses/{id}`
* `DELETE /api/expenses/{id}`
* `GET    /api/expenses/filter`

---

## 🗄 Database Configuration (PostgreSQL)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/expense_tracker
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

---

## ▶️ How to Run

1. Clone the repository
2. Configure PostgreSQL credentials in `application.properties`
3. Run the application:

```bash
mvn spring-boot:run
```

---

## 🔮 Future Improvements

* Dockerization
* Swagger / OpenAPI documentation
* Role-based access control
* Deployment (Render / Railway)

---

## 📖 About

A backend **Expense Tracker API** built with **Java and Spring Boot**, providing RESTful services for managing expenses and income with a clean, scalable, and secure architecture.

## ⭐️ Support

If you like this project, give it a ⭐ on GitHub!