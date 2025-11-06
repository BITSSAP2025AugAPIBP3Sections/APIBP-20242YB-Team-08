# Leave Scheduler

## Table of Contents
1. [Project Description](#project-description)
2. [Technologies Used](#technologies-used)
3. [Project Structure](#project-structure)
4. [Setup and Installation](#setup-and-installation)
5. [Running the Application](#running-the-application)
6. [API Overview](#api-overview)
7. [Swagger UI](#swagger-ui)
8. [Key Actors](#key-actors)
9. [System Architecture](#system-architecture)

## Project Description
The Leave Scheduler is a comprehensive application designed to manage employee leave requests efficiently. It provides features for applying for leave, tracking leave balances, managing approvals, and generating reports. The application supports both REST and GraphQL APIs, offering flexibility in how clients can interact with the system.

## Technologies Used
- Java 17
- Spring Boot 3.5.0
- Spring Security
- Spring Data JPA
- GraphQL
- REST API
- H2 Database (for development)
- MySQL (configured but commented out)
- Swagger UI for API documentation
- JWT for authentication
- Maven for dependency management and build
- Thymeleaf for server-side templating
- Apache POI for Excel file handling
- iText for PDF generation

## Project Structure
The project follows a standard Spring Boot application structure:

- `src/main/java/com/sap/bits/api/LeaveScheduler/`
  - `config/`: Configuration classes
  - `controller/`: REST and GraphQL controllers
  - `dto/`: Data Transfer Objects
  - `model/`: Entity classes
  - `repository/`: Data access layer
  - `security/`: Security configuration and JWT handling
  - `service/`: Business logic layer

- `src/main/resources/`
  - `graphql/`: GraphQL schema files
  - `application.properties`: Application configuration

## Setup and Installation
1. Prerequisites:
   - Java 17 JDK
   - Maven

2. Clone the repository:
   ```
   git clone https://github.com/BITSSAP2025AugAPIBP3Sections/APIBP-20242YB-Team-08.git
   cd LeaveScheduler
   ```

3. Configure environment variables:
   Create a `.env` file in the project root with the following variables:
   ```
   APP_JWT_SECRET=your_jwt_secret
   SPRING_DATASOURCE_USERNAME=your_db_username
   SPRING_DATASOURCE_PASSWORD=your_db_password
   MYSQL_URL=your_mysql_url
   SPRING_MAIL_USERNAME=your_email_username
   SPRING_MAIL_PASSWORD=your_email_password
   ```
   Replace the placeholder values with your actual configuration details.

## Running the Application
1. Build the project:
   ```
   mvn clean install
   ```

2. Run the application:
   ```
   mvn spring-boot:run
   ```

3. The application will start on `http://localhost:8080`

4. To access the H2 console (for development):
   - Go to `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:leave_scheduler`
   - User Name: [as set in your environment variables]
   - Password: [as set in your environment variables]

## API Overview

The Leave Scheduler application provides both REST and GraphQL APIs. Here's a summary of some key endpoints:

| Category | Method | Endpoint | Description |
|----------|--------|----------|-------------|
| Leave Management | POST | `/api/leave-applications` | Apply for leave |
| Leave Management | GET | `/api/leave-applications` | Get current user's leave applications |
| Leave Management | GET | `/api/leave-applications/eligibility` | Get leave eligibility details |
| Leave Management | PUT | `/api/leave-applications/{id}/withdraw` | Withdraw a leave application |
| User Management | GET | `/api/users/profile` | Get current user profile |
| User Management | GET | `/api/users/leave-balance` | Get current user leave balances |
| Authentication | POST | `/api/auth/login` | Authenticate user and return JWT token |
| Authentication | POST | `/api/auth/register` | Register a new user |
| Holiday Management | GET | `/api/holidays/` | Get all holidays |
| Holiday Management | POST | `/api/holidays` | Create a new holiday (ADMIN only) |
| Leave Approval | PUT | `/api/leave-approvals/{id}/{action}` | Approve/Reject a leave application |
| Leave Approval | GET | `/api/leave-approvals/pending` | Get pending leave applications for approval |
| Audit Logs | GET | `/api/audit-logs` | Get all audit logs (ADMIN only) |
| Admin Management | GET | `/api/admin/stats` | Get admin dashboard statistics |

GraphQL API also provides similar functionality. Some key queries and mutations include:

| API Type | Query/Mutation | Description |
|----------|----------------|-------------|
| GraphQL  | `currentUserLeaves` | Get current user's leave applications |
| GraphQL  | `userLeaveBalances` | Get leave balances for a specific user |
| GraphQL  | `applyLeave` | Apply for leave |
| GraphQL  | `withdrawLeave` | Withdraw a leave application |

For a complete list of endpoints and detailed API documentation, please refer to the Swagger UI for REST API and GraphiQL interface for GraphQL API.

### REST API
The REST API can be explored and tested using Swagger UI.

### GraphQL API
The GraphQL API can be explored and tested using the GraphiQL interface:
- Access GraphiQL: `http://localhost:8080/graphiql` (when running locally)
- In the GraphiQL interface, you can write and execute GraphQL queries and mutations
- The schema explorer in GraphiQL provides details about available types and operations

## Swagger UI
Swagger UI is available for exploring and testing the REST API:
- Access Swagger UI: `http://localhost:8080/swagger-ui.html`
- API documentation: `http://localhost:8080/v3/api-docs`

## Key Actors
1. Employees: Can apply for leave, view their leave history, and check leave balances.
2. Managers: Can approve or reject leave applications for their team members.
3. Administrators: Can manage user accounts, leave policies, and generate reports.

## System Architecture
The Leave Scheduler follows a layered architecture:

1. Presentation Layer: REST and GraphQL APIs
2. Business Logic Layer: Service classes implementing core functionality
3. Data Access Layer: JPA repositories for database interactions
4. Database: H2 (development) / MySQL (production)

Security is implemented using Spring Security and JWT for authentication and authorization. The application uses a combination of REST and GraphQL APIs to provide flexibility in data querying and manipulation.

The dual API approach allows for:
- Simple, resource-based operations via REST
- Complex, tailored data fetching via GraphQL
- Flexibility for different client needs and use cases
