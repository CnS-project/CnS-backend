# CnS-Project

## 프로젝트 요구사항
https://traveling-organ-e7f.notion.site/2022-Cloud-Server-2eccf1fc81b94584898aae38615f56eb

---
## 기능 요구사항

1. 로그인 기능
   - [x] 로그인 기능
     - 학번 + 비밀번호


2. 수강신청 기능
   - [ ] 수강신청 기능
     - 강의 직접 클릭하여 신청
     - 과목번호 + 분반으로 신청
     - 학생 정보가 강의 수강 대상에 해당되지 않을 시 신청 불가
   - [x] 강의목록 필터링
     - 학과별, 학년별, 교수별, 과목별, 과목번호 등으로 조합하여 필터링
   - [x] 최대 수강 신청 가능 학점
     - 9학점
   - [x] 수강페이지 접속 시 강의 목록 표시



3. 신청내역 조회 기능
   - [x] 신청내역 조회 기능
   - [x] 수강 취소 기능


4. 시스템 관리 기능
   - [ ] 수강 신청 가능 시간 설정
   - [x] 관리자 아이디로 접속 시 강의 목록 관리 가능
   - [x] 강의 데이터 수정
     - 강의명 변경, 정원 변경 등등
   - [x] 강의 삭제
     - 강의 삭제 시, 해당 강의 수강생들에게 이메일로 폐강 알림 전송
       - 작동과정 유투브 링크 첨부
       - 
---
       
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
  - URL : /users/filtering?courseNumber=&name=&professor=&grade=&magor=
  - HTTP Method : GET
  - Parameter : 
  ````
  {
      "courseNumber" : Integer,
      "name" : name,
      "professor" : String,
      "grade" : Integer,
      "major" : String
  }
  ````
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

### 관리자 기능
