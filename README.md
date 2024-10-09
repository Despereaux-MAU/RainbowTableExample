# Rainbow Table API

이 프로젝트는 Spring Boot를 사용하여 Rainbow Table 공격을 구현한 REST API입니다. 비밀번호 해시를 생성하고, Rainbow Table을 만들며, 해시 값을 검색하는 기능을 포함합니다.
## EXAMPLE
| 비밀번호 | MD5 해시 값                            |
|----------|--------------------------------------|
| 12345    | 827ccb0eea8a706c4c34a16891f84e7b     |
| password | 5f4dcc3b5aa765d61d8327deb882cf99     |
| qwerty   | d8578edf8458ce06fbc5bb76a58c5ca4     |
| abc123   | e99a18c428cb38d5f260853678922e03     |
| letmein  | 0d107d09f5bbe40cade3de5c71e9e9b7     |

## 1. 어플리케이션 실행
```bash
./mvnw spring-boot:run
```
### 기본적으로 애플리케이션은 http://localhost:8080에서 실행됩니다.

## 2. API 명세서
### 1. 레인보우 테이블 생성 (Rainbow Table Create)

- **HTTP Method**: `POST`
- **URL**: `/api/rainbow/create`
- **본문**: JSON 배열 형태로 비밀번호를 포함합니다.
- **요청예시**:
    ```json
    ["12345", "password", "qwerty", "abc123", "letmein"]
    ```
- **Successful Response**:
  - **Status Code**: `200 OK`
  - **Body**:
    ```json
    {
        "message": "Rainbow Table created successfully!"
    }
    ```
- **Failed Response**:
  - **Status Code**: `400 Bad Request`
  - **Body**:
    ```json
    {
        "error": "Invalid input format."
    }
    ```

### 2. 비밀번호 찾기 (Find Passwords)

- **HTTP Method**: `GET`
- **URL**: `/api/rainbow/find/{hashedValue}`
- **Path Parameter**:
  - `hashedValue`: 찾고자 하는 비밀번호의 해시 값
- **요청예시**:
  - 해시 값: 827ccb0eea8a706c4c34a16891f84e7b
- **Successful Response**:
  - **Status Code**: `200 OK`
  - **Body**:
    ```json
    {
        "password": "12345"
    }
    ```
- **Failed Response**:
  - **Status Code**: `404 Not Found`
  - **Body**:
    ```json
    {
        "error": "Password not found."
    }
    ```
