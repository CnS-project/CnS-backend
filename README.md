# CnS-Project

## 프로젝트 요구사항
https://traveling-organ-e7f.notion.site/2022-Cloud-Server-2eccf1fc81b94584898aae38615f56eb


## 🚀 기능 명세사항

###  로그인

- Request
  - URL : /users/login
  - HTTP Method : POST
  - Request Body

      ```
      {
          "studentId" = Integer
          "password" = String
      }
      ```

- Response
  - Response Body (Status = 200)

    ```
    {
        "sessionId" = String
    }
    ```

---

###  로그아웃

- Request

  - URL - /users/logout
  - HTTP Method - PUT
  - Request Header

    ```
    {
        "sessionId" = String
    }
    ```

- Response
  - Response Body (Status = 200)
  - Response Body (Status = 401)

---

### 전체 강의 목록 조회

- Request
  - URL - /courses
  - HTTP Method - GET

- Response
  - Response Body (Status = 200)
    ```
    {
        "courses" : [
            {
                "targetGrade" : Integer,
                "credit" : Integer,
                "name" : String,
                "courseNumber" : Integer,
                "classNumber" : Integer,
                "professor" : String,
                "capacity" : Integer
            }
            ...
        ]
    }
    ```
---

###  수강 신청

- Request
    - URL - /registration-course
    - HTTP Method - POST
    - Request Header
      ```
      {
          "sessionId" = String
      }
      ```
    - Request Body
      ```
      {
          "courseName" = String
          "courseNumber" = Integer
          "classNumber" = Integer
      }
      ```
- Response
    - Response Body (Status = 200)

    - Response Body (Status = 400)
    - Response Body (Status = 401)

---

### 신청 내역 조회

- Request
    - URL : /users/{student-id}/courses
    - HTTP Method : GET
    - Request Parameter
      ```
      {
          "student-id" = Integer
      }
      ```

- Response

  - Response Body {Status = 200}
    ```
    {
        "coursesOfUser" : [
            {
                "targetGrade" : Integer,
                "credit" : Integer,
                "name" : String,
                "courseNumber" : Integer,
                "classNumber" : Integer,
                "professor" : String,
                "capacity" : Integer
            }
            …
        ]
    }
    ```

---

### 수강 취소 기능
- Request

    - URL : /users/courses/cancel
    - HTTP Method : PUT
    - Request Body
  ````
  {
      courseNumber : Integer,
      classNumber : Integer
  }
  ````
