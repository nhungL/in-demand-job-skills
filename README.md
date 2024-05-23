## Top Data Skills Web Application
[Demo Video](https://youtu.be/Kf9UUe1z7q4)

[GO TO APP](https://top-data-879e43211a23.herokuapp.com/)

### Project Overview
The "Top Data Skills" web application analyzes recent data-related job postings to highlight in-demand skills across various roles. This project aims to provide valuable insights for professionals looking to enter or advance in the data field.

### Project Description and Purpose
* Job Titles Analyzed: The application queries 6 key job titles:
  - Business Analyst
  - Data Scientist
  - Data Engineer
  - Software Engineer
  - Data Analyst
  - Machine Learning
* Skill Extraction and Ranking: Identifies and ranks the most demanded skills for each job title, e.g., highlighting Excel as a top skill for Business Analysts.
* Degree Requirements: Analyzes and presents the percentage of job postings requiring specific degrees, aiding in understanding the educational qualifications needed.
* Salary Insights: Retrieves and compares salary data across job titles, offering a comprehensive view of the earning potential in the data field.
  
### Technologies and Implementations
The project is created with:
* Frontend:
  - Node.js (v16.15.1)
  - React
  - SPA: React DOM and React Router
  - Apollo Client: A GraphQL client for React applications to connect with backend
* Backend:
  - Language: Java
  - Spring Boot
  - [Lombok](https://projectlombok.org/setup/gradle)
* Database:
  - Host on [Neon](https://neon.tech/)
  - GraphQL
  - Spring Boot Data JPA
  - PostgreSQL
  - H2 Database: testing purposes
* Others: Gradle
* Testing: Mockito, JUnit

### Data Preprocessing
* NLTK (Natural Language Toolkit): Utilized for processing job posting texts to extract meaningful skill data.
Running the Application Locally

### Run Application
* Navigate to the project directory and run ```build.gradle``` to install and build project dependencies
* Start application by execute ```./gradlew bootRun```
* App runs on ```http://localhost:8080/```
