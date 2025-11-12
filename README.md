This back-end service entails a Spring Boot application that manages a collection of books. Each book has an ID, title, author, and publication year.

Stack used: 
- Java 17
- Spring Boot v3.5.7
- Database(In memory H2 db)

Set-up Instructions
- Clone the code from the repo link
- Set up on IntelliJ IDE ensuring the stack tools version is met as defined above
- The back-end service can be accessed via localhost:8080/api/books
- Swagger documentaion available when the service is up and running via : http://localhost:8080/swagger-ui/index.html#/
- H2 Console Access when the service is up and running via: http://localhost:8080/h2-console/

How to Run via PostMan:
- Eg Create a new book entry
- Sample payload below:

`{
  "title": "The Pragmatic Programmer",
  "author": "Nelson Kimaiga",
  "year": 1999
  }`

- URL: http://localhost:8080/api/books
- Method: GET 
- Authorization: Choose Basic Auth
- Username: nelson 
- Password: password
- Send the request
