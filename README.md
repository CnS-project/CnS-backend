# CnS-Project

## 프로젝트 요구사항
https://traveling-organ-e7f.notion.site/2022-Cloud-Server-2eccf1fc81b94584898aae38615f56eb

### 신청 내역 조회

- Request
    - URL : /users/{student-id}/courses
    - HTTP Method : GET
    - Request Parameter
  ````
  {
     studentId = Integer 
  }
  ````


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
