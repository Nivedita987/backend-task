# backend-task
Spring Boot backend for student and course management

# Student and Course Management Backend

## 📌 Project Overview
This project is a backend system developed to manage students and their associated courses.
It demonstrates backend development skills including REST APIs, database relationships,
role-based authentication, and secure access control.

The system allows:
- Managing students and courses
- Assigning students to courses
- Role-based access using Spring Security

---

## 🛠 Tech Stack
- **Language:** Java
- **Framework:** Spring Boot
- **Database:** MySQL
- **ORM:** Spring Data JPA (Hibernate)
- **Security:** Spring Security (Basic Authentication)
- **API Testing:** Terminal (PowerShell) & Postman
- **Build Tool:** Maven

---

## 🗄 Database Design

### Course Table
| Field Name | Type | Description |
|-----------|------|-------------|
| course_id | BIGINT (PK) | Auto-generated ID |
| course_name | VARCHAR | Course name |
| course_code | VARCHAR (Unique) | Unique course code |
| course_duration | INTEGER | Duration in months |

### Student Table
| Field Name | Type | Description |
|-----------|------|-------------|
| id | BIGINT (PK) | Auto-generated ID |
| name | VARCHAR | Student name |
| email | VARCHAR | Student email |
| course_id | BIGINT (FK) | Associated course |

---

## 🔐 Authentication & Authorization

### Roles Implemented
| Role | Permissions |
|-----|------------|
| **ADMIN** | Full CRUD operations on students |
| **STUDENT** | View-only access |

### Credentials Used (for testing)
| Role | Username | Password |
|-----|----------|----------|
| Admin | admin | admin123 |
| Student | student | student123 |

---

## 🚀 Backend Features

- Add student with course assignment
- Fetch all students with course details
- Fetch students by course
- Update student details and course
- Delete students (restricted for data safety)
- Role-based access control
- Proper HTTP status codes and validations

---

## ▶ How to Run the Application

### Prerequisites
- Java 17+
- Maven
- MySQL running

### Steps
```bash
 .\mvnw clean
 .\mvnw spring-boot:run

### API Testing
```Tools Used
PowerShell (Invoke-RestMethod)
Postman

## Database Schema Note

This project uses **Spring Data JPA with Hibernate**.

- Database tables are **auto-generated at runtime** using entity classes.
- No manual SQL scripts are required to create tables.
- Hibernate configuration:
  ```properties
  spring.jpa.hibernate.ddl-auto=update
