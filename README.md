```
Project description:

Small framework designed for demonstrating REST services testing.
REST spacification is given here: https://restful-booker.herokuapp.com/apidoc/index.html
```
```
Technologies:

Java 17
JUnit 5 – base test framework
RestAssured – REST API functionalities testing
Maven – for building and dependency management
Lombok – to avoid boilerplate code
Jackson – JSON serialization/deserialization
```
```
Clone project:
git clone https://github.com/mstamatovic/restful-testing-tool.git
```
``` bash
Project structure:

src/
├── main/
│   └── java/
│       └── com.automation/
│           ├── config/          # Base configuration(base URL, credentials)
│           ├── model/           # POJO models for request/response
│           ├── constants/       # Separate classes for constants used across a project
|           ├── requests/        # Service classes for API calls
│           └── response/        # Methods for handling response parameters
│
└── test/
    ├── java/
    │   └── com.automation/
    │       ├── base/            # Simple base tests superclass
    │       ├── response/        # Logic for validation test results
    |       └── tests/           # Test classes, keep just simple test scenario (handling stays behind)
    │
    └── resources/
        └── application.properties     # URL and credential data 

```
        
