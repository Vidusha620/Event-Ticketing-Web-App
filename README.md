# Real-Time Event Ticketing System  

This system is a simple full-stack application designed to handle event ticketing efficiently. Built using React for the frontend and Spring Boot with H2 for the backend, it ensures real-time updates and maintains data integrity during concurrent ticket bookings. It's perfect for event organizers who need a reliable platform for managing tickets.

## Setup Instructions  

### Prerequisites  
Before you start, make sure you have the following installed on your computer:  
- **Java**: Version 17 or later  
- **Node.js**: Version 18 or later  
- **MySQL**: Version 8.0 or later  
- **Maven**: Version 3.8.0 or later  

### Building and Running the Application  

#### Backend Setup  
1. Clone the backend repository to your local machine: git clone <backend-repo-url>  
2. Go to the project directory:  cd backend    
3. Open the `application.properties` file and update the database configurations (username, password, etc.) to match your H2 setup.  
4. Build the backend using Maven:  mvn clean install   
5. Start the backend server:   mvn spring-boot:run   

#### Frontend Setup  
1. Clone the frontend repository:  git clone <frontend-repo-url>    
2. Navigate to the frontend directory:  cd frontend   
3. Install the required dependencies:    npm install  
4. Start the development server:    npm start    

## Usage Instructions  

### Starting the System  
1. Run both the backend and frontend as described in the setup instructions.  
2. Open your browser and go to `http://localhost:3000` to access the application.  

### Using the Interface  
- **Configurations**  
  - Input all ticket configuration details before starting.

- **Logs**  
  - Displays all the logs related with the system. 

- **Control Panel**  
  - Holds start and stop buttons to start and stop the system for ticket releasing.

## CLI Usage
For users who prefer interacting with the system through the CLI, this too has the basic operations in the web application.

**Running the CLI**
Open a terminal in the backend project directory.
Run the application.
  
**Configurations**  
  - Input all ticket configuration details before starting.
 
 **Logs**  
  - Displays all the logs related with the system which are saved in a JSON file. 













  

