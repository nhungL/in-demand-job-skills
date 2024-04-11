## Top Data Skills
Web application on recent data-related job postings, hightlights on high-demand skills.

## Project Description and Purpose
* 6 job titles are queried: Business Analyst, Data Scientist, Data Engineer, Software Engineer, Data Analyst, Machine Learning
* Extract and ranking demanding skills: ex: Excel is top skill being asked for Business Analyst job
* Keep track of degrees percentage needs to successful landing the jobs
* Retrieve jobs' salaries, compare and ranking them among job titles
  
## Technologies and Implementations
The project is created with:
* Frontend: Node.js (v16.15.1), React, SPA: React DOM and React Router, Apollo Client: A GraphQL client for React applications to connect with backend
* Backend: Java, Spring Boot, Lombok(https://projectlombok.org/setup/gradle)
* Database: GraphQL, Spring Boot Data JPA, PostgreSQL, H2 Database: testing purposes
  - Hosted on Neon(https://neon.tech/)
* Others: Gradle
* Testing: Mockito, JUnit

## Data Preprocessing
* Preprocess data using NLTK

## Run application on local
* Run ```build.gradle``` - install and build dependencies
* Run application - ```./gradlew bootRun```
