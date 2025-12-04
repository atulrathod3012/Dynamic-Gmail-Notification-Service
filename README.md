# 🚀 Dynamic Gmail Notification Service

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)

---

<p align="center">
  <img src="https://via.placeholder.com/600x200?text=Dynamic+Gmail+Notification+Service" alt="Project Banner"/>
</p>

## 🌟 Overview
A modern Spring Boot REST API to send Gmail notifications using dynamic SMTP configuration loaded from PostgreSQL. No hardcoded mail settings—everything is fetched at runtime for maximum flexibility and security.

---

## 🧩 Features
- **POST** `/api/sendNotification` endpoint
- Input validation
- Mail config fetched from DB
- Email sent using dynamic config
- Success/failure JSON response
- Clean error handling and logging

---

## 🛠️ Technologies
- ![Java](https://img.shields.io/badge/Java-17-blue)
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
- ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-yellow)
- ![Spring Mail](https://img.shields.io/badge/Spring%20Mail-orange)
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)

---

## 📁 Project Structure
```
src/main/java/com/redberyl/notification/
├── controller/           # REST controllers
├── service/              # Business logic
├── repository/           # Data access layer
├── entity/               # JPA entities
├── dto/                  # Data Transfer Objects
└── NotificationServiceApplication.java # Main class
```

<p align="center">
  <img src="https://via.placeholder.com/500x300?text=Project+Structure" alt="Project Structure"/>
</p>

---

## 🗄️ Database Setup
Create the `mail_config` table in PostgreSQL:
```sql
CREATE TABLE mail_config (
  id SERIAL PRIMARY KEY,
  host VARCHAR(255),
  port INTEGER,
  username VARCHAR(255),
  password VARCHAR(255),
  protocol VARCHAR(20),
  properties TEXT
);
```
Insert your Gmail SMTP config into this table.

---

## ⚙️ Configuration
Edit `src/main/resources/application.properties`:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/notificationdb
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
logging.level.org.springframework=INFO
```

---

## 🏗️ Build & Run
Make sure Maven is installed and available in your PATH.
```
mvn clean install
mvn spring-boot:run
```

---

## 📡 API Usage
### Request
```
POST /api/sendNotification
Content-Type: application/json
{
  "tomail": "recipient@gmail.com",
  "subject": "Email Subject",
  "body": "Email Body Content"
}
```
### Response
Success:
```
{ "status": "SUCCESS", "message": "Email sent successfully" }
```
Failure:
```
{ "status": "FAILED", "message": "Unable to send email" }
```

---

## 💡 Notes
- No hardcoded mail config in code.
- All config fetched from DB at runtime.
- Proper error handling and logging.

---

<p align="center">
  <img src="https://via.placeholder.com/400x100?text=Thank+You+for+using+Dynamic+Gmail+Notification+Service!" alt="Thank You"/>
</p>
