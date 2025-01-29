### For easy startup execute next commands in the project root

1) ./gradlew clean build
2) docker-compose up -d
3) run the app from console or IDE

# 📌 Blog API Documentation

This is the API documentation for the **Blog Application**, containing endpoints for managing **articles** and **comments**.

## 🚀 Base URL
```
http://localhost:8080/api
```

---

## 📌 Article Endpoints (`/api/articles`)

### **📍 Get All Articles**
🔹 **GET** `/api/articles`
```http
http://localhost:8080/api/articles
```
📌 **Response:** List of articles (paginated)

---

### **📍 Get Article by ID**
🔹 **GET** `/api/articles/{id}`
```http
http://localhost:8080/api/articles/1
```
📌 **Response:** Returns article details or `404 Not Found`

---

### **📍 Create a New Article**
🔹 **POST** `/api/articles`
```http
http://localhost:8080/api/articles
Content-Type: application/json

{
  "title": "New Article",
  "content": "This is a new article content.",
  "authorId": 1,
  "publishedDate": "2025-01-30T10:00:00Z"
}
```
📌 **Response:** `201 Created` with article details.

---

### **📍 Update an Existing Article**
🔹 **PUT** `/api/articles/{id}`
```http
http://localhost:8080/api/articles/1
Content-Type: application/json

{
  "title": "Updated Title",
  "content": "Updated content of the article.",
  "publishedDate": "2025-01-30T10:00:00Z"
}
```
📌 **Response:** `200 OK` if successful, or `404 Not Found`.

---

### **📍 Delete an Article**
🔹 **DELETE** `/api/articles/{id}`
```http
http://localhost:8080/api/articles/1
```
📌 **Response:** `204 No Content` if deleted, `404 Not Found` if article doesn’t exist.

---

## 📌 Comment Endpoints (`/api/{articleId}/comments`)

### **📍 Get All Comments for an Article**
🔹 **GET** `/api/{articleId}/comments`
```http
http://localhost:8080/api/1/comments
```
📌 **Response:** List of comments (paginated)

---

### **📍 Add a Comment to an Article**
🔹 **POST** `/api/{articleId}/comments`
```http
http://localhost:8080/api/1/comments
Content-Type: application/json

{
  "content": "This is a great article!"
}
```
📌 **Response:** `201 Created` with comment details.

---

### **📍 Update a Comment**
🔹 **PUT** `/api/{articleId}/comments/{commentId}`
```http
http://localhost:8080/api/1/comments/3
Content-Type: application/json

{
  "content": "Updated comment content."
}
```
📌 **Response:** `200 OK` if updated, or `404 Not Found`.

---

### **📍 Delete a Comment**
🔹 **DELETE** `/api/{articleId}/comments/{commentId}`
```http
http://localhost:8080/api/1/comments/3
```
📌 **Response:** `204 No Content` if deleted, `404 Not Found` if not found.

---

