# Bus Station Management - Microservices Architecture

## Description
The **Bus Station Management System** is a microservices-based application designed to efficiently manage bus circuits, stations, and related operations. The system provides features for retrieving circuit details, calculating travel costs, managing stations, and more.

The application evolved through multiple stages of development:
1. **SOAP & XML** → Initial implementation.
2. **REST (Jersey)** → Migration to RESTful architecture.
3. **GraphQL** → Enhanced query flexibility.
4. **Microservices** → Final architecture with two independent services.

## Features
### **1. Bus Information Service** (`Bus-Information-Service`)
- Retrieve circuits between two stations.
- Get closest stations based on coordinates.
- Check if a circuit is active on a specific date.

### **2. Bus Management Service** (`Bus-Management-Service`)
- Create new circuits and manage routes.
- Retrieve all circuits and specific circuit details.
- Calculate total cost of circuits per day.
- Manage stations (add, retrieve, and modify routes).

## Microservices Architecture
The system follows a **microservices architecture**, where each service operates independently and communicates via REST APIs.

![Microservices Architecture](https://github.com/user-attachments/assets/8741dd4e-b492-457a-8bb2-ce9b7e5f6193)

## Database Representation
Neo4j is used for storing and managing the relationships between **buses, circuits, and stations** in a graph-based model.
![graph](https://github.com/user-attachments/assets/93a1a276-20ef-4655-9c17-d233d631ae7a)


## Technology Stack
- **Backend:** Spring Boot (Microservices)
- **Database:** Neo4j (Graph Database)
- **Containerization:** Docker
- **API Communication:** REST, GraphQL

## Installation
### **Prerequisites**
- **Java JDK 17+**
- **Maven** (for building the application)
- **Neo4j Database** (for data storage)
- **Docker** (for containerization)

### **Steps to Run**
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/bus-station-management.git
   cd bus-station-management
   ```
2. Build and run each microservice:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
3. Run the database in Docker:
   ```bash
   docker-compose up -d
   ```
4. Access the services:
   - **Bus Information Service:** `http://localhost:8081/api/tunise`
   - **Bus Management Service:** `http://localhost:8082/api/management`

## Usage
- Use **Postman** or **cURL** to test endpoints.
- Create, manage, and retrieve bus circuits and stations.
- Query Neo4j for optimized route suggestions.

## Contributing
Contributions are welcome! Fork the repo and submit a pull request.

## License
MIT License. See `LICENSE` for details.
