# ⚙️ PaperTrail Backend

The backend service for **PaperTrail**, a full-stack academic resource platform built using **Spring Boot** and **MySQL**.

It provides REST APIs for managing academic resources, handling student requests, processing file uploads, and communicating with the React frontend.

---

## 🌐 Live Application

**Frontend:** https://papertrail-frontend.vercel.app

**Backend API:** *https://papertrail-backend-quej.onrender.com*

---

# 📖 About the Project

PaperTrail was built to solve a common problem faced by students—academic resources such as previous-year papers, notes, and lab manuals are often scattered across seniors, WhatsApp groups, Google Drive folders, and library archives.

The backend powers the platform by exposing REST APIs that allow administrators to manage academic resources while enabling students to browse, search, download, and request materials through the frontend.

---

# ✨ Features

## 👨‍💼 Administrator

Administrators can:

- Upload previous-year question papers
- Upload study notes
- Upload lab manuals
- Add new academic resources
- Delete outdated resources
- View all uploaded resources
- View and manage student resource requests

---

## 👩‍🎓 Student

Students can:

- Browse available academic resources
- Search and filter resources
- Download available materials
- Request unavailable papers or notes
- Calculate their CGPA using the integrated calculator

---

# 🏗 Architecture

```text
React Frontend (Vercel)
            │
            ▼
Spring Boot REST API (Render)
            │
            ▼
MySQL Database (Aiven Cloud)
```

---

# 🛠 Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate

### Database

- MySQL
- Aiven Cloud (Managed MySQL Database)

### Build Tool

- Maven

### Utilities

- Lombok
- Spring Validation

### Deployment

- Render

---

# 📂 Project Structure

```text
src
│
├── controller
│   ├── ResourceController
│   └── RequestController
│
├── entity
│   ├── AcademicResource
│   └── ResourceRequest
│
├── repository
│   ├── ResourceRepository
│   └── RequestRepository
│
├── service
│   └── ResourceService
│
└── resources
```

---

# 📡 REST API Endpoints

## Resource APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/resources` | Retrieve all academic resources |
| GET | `/resources/{id}` | Retrieve a resource by ID |
| POST | `/resources/upload` | Upload a new academic resource |
| DELETE | `/resources/{id}` | Delete a resource |

---

## Resource Request APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/requests` | Retrieve all student requests |
| POST | `/requests` | Create a new resource request |
| DELETE | `/requests/{id}` | Remove a request |

---

# 🗄 Database Design

## AcademicResource

Stores metadata for uploaded academic resources.

- Title
- Subject
- Branch
- Semester
- Academic Year
- Resource Type
- File Name
- File Path
- Created At

---

## ResourceRequest

Stores student requests for unavailable academic resources.

- Requested Details
- Branch
- Semester
- Status
- Created At

---

# 🚀 Running Locally

## Clone the repository

```bash
git clone https://github.com/Ur-ka-shi/papertrail-backend.git
```

```bash
cd papertrail-backend
```

---

## Configure the Database

Create a MySQL database named:

```text
papertrail
```

Update the database configuration inside:

```text
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/papertrail
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

---

## Install Dependencies

```bash
./mvnw clean install
```

---

## Run the Application

```bash
./mvnw spring-boot:run
```

The backend will start at:

```text
http://localhost:8080
```

---

# 🔗 Frontend Repository

https://github.com/Ur-ka-shi/papertrail-frontend

---

# 🚀 Future Improvements

- User Authentication
- Role-Based Access Control
- Download Analytics
- Bookmarks
- Comments and Discussions
- Resource Ratings
- Cloud File Storage
- Multi-College Support

---

## 👩‍💻 Author

**Urvashi Kamble**

Computer Engineering Student

SNDT Women's University

GitHub: https://github.com/Ur-ka-shi

---

## 📄 License

This project is intended for educational and portfolio purposes.