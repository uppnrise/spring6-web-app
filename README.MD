# Spring6 Web App

## Overview
This project is a Spring Boot application that manages beers and customers. It includes RESTful APIs for CRUD operations on beers and customers, database migrations using Flyway, and containerization using Docker.

## Running Tests
To run unit and integration tests, use the following command:
./gradlew test

## Testing the Application
You can test the application by accessing the following URL:
[https://spring6-web-app.onrender.com]  
This URL provides access to the deployed version of the Spring Boot application, where you can perform CRUD operations on beers and customers through the provided RESTful APIs.

## API Endpoints
- `GET /api/v1/beer` - List all beers
- `GET /api/v1/beer/{beerId}` - Get beer by ID
- `POST /api/v1/beer` - Create a new beer
- `PUT /api/v1/beer/{beerId}` - Update beer by ID
- `DELETE /api/v1/beer/{beerId}` - Delete beer by ID
- `PATCH /api/v1/beer/{beerId}` - Patch beer by ID
- `GET /api/v1/customer` - List all customers
- `GET /api/v1/customer/{customerId}` - Get customer by ID
- `POST /api/v1/customer` - Create a new customer
- `PUT /api/v1/customer/{customerId}` - Update customer by ID
- `DELETE /api/v1/customer/{customerId}` - Delete customer by ID
- `PATCH /api/v1/customer/{customerId}` - Patch customer by ID

## Database
The application uses MySQL as the database. The database schema is managed using Flyway migrations.

## Building Docker Image
To build the Docker image, run:
docker build -t spring6-web-app .

## Running Docker Container
To run the Docker container, use:
docker run -p 8080:8080 spring6-web-app

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact
For any inquiries or support, please contact [uppnrise](https://github.com/uppnrise).