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
###  로그아웃

- Request

  - URL - /users/logout
  - HTTP Method - PUT

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
                "capacity" : Integer,
                "major" : String,
                "applicant" : Integer
            }
            ...
        ]
    }
    ```
---
### 강의 목록 필터링
- Request
  - URL : /users/filtering
  - HTTP Method : GET
- Response
  - Response Body {Status = 200}
  ````
  {
        "courses" : [
            {
                "targetGrade" : Integer,
                "credit" : Integer,
                "name" : String,
                "courseNumber" : Integer,
                "classNumber" : Integer,
                "professor" : String,
                "capacity" : Integer,
                "major" : String,
                "applicant" : Integer
            }
            …
        ]
    }
  ````

---
###  수강 신청

- Request
    - URL - /users/registration-course
    - HTTP Method - POST
        
    - Request Body
      ```
      {
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
    - URL : /users/courses
    - HTTP Method : GET

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
