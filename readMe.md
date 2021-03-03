# Question Paper Generator - V1

### Background
The system is designed to generate Question Paper dynamically against 
the set of inputs. API's has been exposed and eventually gets consumed by 
UI Clients (Web/Android/IOS APP/Mobile Browser)

### Tech Stack
- Java 8
- Spring Boot
- InMemory Perstitant Store
- Slf4j
- JSR 303 Bean Validation

### Design Principles Followed
- SRP: Single Responsibility Principle
- OCP: Open Closed Principle
- ISP: Interface Segregation Principle
- DIP: Dependency Inversion Principle
- LSP: Liskov Substitution Principle

### Limitation of current version

- Question Store expects questions with marks multiple of 5 for EASY & Medium level
    & 10 for Difficulty level only
- Total Marks should be in Range from 5 TO 200. 200 Being highest (Range is configurable)

### Enhancement which can be taken up

- API Security to make sure Question Store is not hampered by Unauthorised User
- Migrating to Document Store perstitant storage for better search functionality
- System is designed in modular way to make future enhancement to handle Question
  preference on Topic. In case of such enhancements we just have to touch service layer 
  without having other layers of the application Impacted
- Externalising & Dynamic configuration Management

### How to run
- Clone project from git
- Build project with Test cases : **mvn clean install**
- On command line run : **mvn spring-boot:run** (Or Import project to Intellij and run)

### API's To Run

- To Load Questions **[POST]**
  <http://localhost:8080/api/v1/questionpaper/store/load/ALL>
- To Generate Question Paper **[GET]**
  <http://localhost:8080/api/v1/questionpaper/generate?distributions=30,20,50&stratergy=difficulty&totalMarks=100>
### API Documentation
Documented with Swagger.
Please access URL for swagger
[API Documentation](<http://localhost:8080/api/v1/questionpaper/swagger-ui.html#>)




