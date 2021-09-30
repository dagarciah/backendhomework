# Backend Homework
Is an application to create, modify, delete and read a collection of movies. A movie will be represented by the following model: <br/>
- id: The unique identificator for the movie 
- name: The name of the movie
- resume: Some text about the movie.
- duration: How many minutes.
- releaseDate: When was released.
- language: The original language of the movie.
- accessType: 'PUBLIC' is visible for all users, 'PRIVATE' is visible by the owner only.
- creator: Who is the user that create it.

Only registered users will be able to use the movie management services.

# Architecture
The application is build using clean architecture, based on "Hexagonal Architecture" or "Ports and Adapters". The main project contains tree submodules:
- **Domain**: Contains all business entities like model, concrete services, exceptions and ports.
- **Application**: Contains all handlers and commands, this components make the orchestration between business components to resolve any requirement.
- **Infrastructure**: Contains all tree party frameworks and libraries like spring framework, jdbc connectors, etc.

# Services
## User Command
**User Registration Service:** Allow to register a new user in the system.<br/>
`curl -X POST "http://localhost:8080/user" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"email\": \"correo@correo.com\", \"password\": \"Hello@#?!]\"}"`
## User Query
**User Authentication Service:** Allow the authentication for registered users.<br/>
`curl -X GET "http://localhost:8080/user/authentication" -H "accept: */*" -H "Authorization: Basic Y29ycmVvQGNvcnJlby5jb206SGVsbG9AIz8hXQ=="`
## Movie Command
**Create Movie Service:** The authenticated users can create a new movie entry.<br/>
`curl -X POST "http://localhost:8080/movie" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzI5NjYxNjcsInN1YiI6ImNvcnJlb0Bjb3JyZW8uY29tIiwiaXNzIjoiZGFnYXJjaWFoQGhvdG1haWwuY29tIiwiZXhwIjoxNjMyOTY3MzY3fQ.Tu_SWvrI7HF7omqzXMuyz8YjoUOpzwaMYhvKfe82B84" -H "Content-Type: application/json" -d "{ \"accessType\": \"PUBLIC\", \"durationInMinutes\": 104, \"language\": \"English\", \"name\": \"Predator\", \"releaseDate\": \"2010-10-05\", \"resume\": \"string\"}"`

**Update Movie Service:** The authenticated users can modify their private items.<br/>
`curl -X PUT "http://localhost:8080/movie/2" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzI5NjYxNjcsInN1YiI6ImNvcnJlb0Bjb3JyZW8uY29tIiwiaXNzIjoiZGFnYXJjaWFoQGhvdG1haWwuY29tIiwiZXhwIjoxNjMyOTY3MzY3fQ.Tu_SWvrI7HF7omqzXMuyz8YjoUOpzwaMYhvKfe82B84" -H "Content-Type: application/json" -d "{ \"accessType\": \"PUBLIC\", \"durationInMinutes\": 0, \"id\": 0, \"language\": \"string\", \"name\": \"string\", \"releaseDate\": \"string\", \"resume\": \"string\", \"user\": { \"email\": { \"value\": \"string\" }, \"id\": 0, \"password\": { \"value\": \"string\" } }}"`

**Delete Movie Service:** The authenticated users can delete their private items.<br/>
`curl -X DELETE "http://localhost:8080/movie/1" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzI5NjYxNjcsInN1YiI6ImNvcnJlb0Bjb3JyZW8uY29tIiwiaXNzIjoiZGFnYXJjaWFoQGhvdG1haWwuY29tIiwiZXhwIjoxNjMyOTY3MzY3fQ.Tu_SWvrI7HF7omqzXMuyz8YjoUOpzwaMYhvKfe82B84"`

## Movie Query
**List Movies:** The authenticated users can list all their public and private items.<br/>
`curl -X GET "http://localhost:8080/movie?accessType=PRIVATE&page=0&size=2" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzI5NjYxNjcsInN1YiI6ImNvcnJlb0Bjb3JyZW8uY29tIiwiaXNzIjoiZGFnYXJjaWFoQGhvdG1haWwuY29tIiwiZXhwIjoxNjMyOTY3MzY3fQ.Tu_SWvrI7HF7omqzXMuyz8YjoUOpzwaMYhvKfe82B84"`

**Find Movie:** The authenticated users can retrieve their public and private items by id.<br/>
`curl -X GET "http://localhost:8080/movie/1" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzI5NjYxNjcsInN1YiI6ImNvcnJlb0Bjb3JyZW8uY29tIiwiaXNzIjoiZGFnYXJjaWFoQGhvdG1haWwuY29tIiwiZXhwIjoxNjMyOTY3MzY3fQ.Tu_SWvrI7HF7omqzXMuyz8YjoUOpzwaMYhvKfe82B84"`

# API Documentation
The API documentation is allocated in ***http://localhost:8080/swagger-ui.html***, this documentation is provide by Swagger.

# Build And Run
### Compile and install 
- `mvn clean install`

### Run
- `mvn spring-boot:run -f infrastructure`
