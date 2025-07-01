# 💰 Bank Management System – Spring Boot Edition

This Bank Management System provides core banking features like creating accounts, handling deposits and withdrawals, tracking transactions, and viewing balances, all through secure and organized REST APIs using Spring Boot.

---

## 🚀 Why This Project?

Imagine a digital bank where customers can:

- 📝 Register and open accounts  
- 💵 Deposit and withdraw funds  
- 🔍 Check balances and view transactions  
- 🗂️ Manage their financial data  

Now imagine building it in Java, with the elegance of Spring Boot. That’s what this project is about—bringing banking logic to life.

---

## 🧠 Tech Stack

| Layer         | Tech Used            |
|---------------|----------------------|
| Language      | Java 17+             |
| Framework     | Spring Boot          |
| Validation    | Jakarta Validation   |
| REST          | Spring Web           |
| Design Pattern| DTO + Service Layer  |
| API Style     | RESTful APIs         |
| Database      | (Pluggable - MySQL, PostgreSQL, H2, etc.) |

---

## 🛠️ Key Features

✨ Add a new customer and open their bank account  
💸 Deposit and withdraw money securely  
📊 View account balance by email  
📜 Fetch all transactions of an account  
🧾 Add manual transactions  
🧍 Get customer details by email  
🗑️ Delete a customer account

---

## 📡 REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/add-customer-and-open-bank-account` | Create customer and open account |
| PUT    | `/api/deposit-money/{email}/{account_number}/{amount}` | Deposit money |
| PUT    | `/api/withdraw-money/{email}/{account_number}/{amount}` | Withdraw money |
| GET    | `/api/fetch-transactions/{account_number}` | View transaction history |
| GET    | `/api/fetch-balance/{email}` | Check account balance |
| DELETE | `/api/delete-account/{email}` | Remove customer's account |
| POST   | `/api/add-transaction/{accountNumber}` | Add transaction manually |
| GET    | `/api/get-customer-details-by-email/{email}` | View customer details |

---

## 🧪 Getting Started

### Prerequisites

- Java 17+
- Maven (or Gradle)
- (Optional) MySQL/PostgreSQL setup

### Run it Locally

```bash
# Clone the project
git clone https://github.com/yourusername/bank-management-system.git
cd bank-management-system

# Build the app
./mvnw clean install

# Run it
./mvnw spring-boot:run
```
**Access the API**
- Base URL: http://localhost:8080/api
---
### ✅ Future Enhancements
- Add authentication and role-based authorization

- Swagger/OpenAPI documentation

- Docker containerization

- Frontend integration (React, Angular)

### **📖 License**
This project is licensed under the MIT License. Feel free to fork, clone, or contribute!

**👨‍💻 Made with ❤️ by**
Chandan Kolloju
📧 Email: kollojuchandan123@gmail.com
