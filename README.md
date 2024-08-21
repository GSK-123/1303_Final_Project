# Millionaire Game Project

## Project Description

### 1.1 Project
Millionaire Game - A web-based trivia game inspired by the popular TV show "Who Wants to Be a Millionaire?"

### 1.2 Description
The Millionaire Game project is a full-stack web application designed to emulate the famous quiz game show. Users can answer multiple-choice questions to win virtual money, with the assistance of lifelines. The project involves frontend development using React, backend development with Java and Spring Boot, and data storage using MongoDB.

### 1.3 Revision History
| Date       | Comment                                       | Author               |
|------------|-----------------------------------------------|----------------------|
| 2023-08-19 | Initial project setup and basic functionality | Gursaroop Singh Kang |

## Overview

### 2.1 Purpose
The purpose of this project is to create an engaging and interactive trivia game that mimics the experience of "Who Wants to Be a Millionaire?".

### 2.2 Scope
The scope of this project includes the development of the following modules:
- **User Interface**: Web-based interface for users to interact with the game.
- **Game Logic**: Backend logic to manage game flow, lifelines, and scoring.
- **Data Management**: Integration with MongoDB for storing questions and user data.
- **Testing and Monitoring**: Ensuring reliability and performance of the game.

### 2.3 Requirements
#### 2.3.1 Functional Requirements
- **R1**: The system shall allow users to start a new game and answer questions.
- **R2**: The system shall implement lifelines including 50-50, Double Dip, Skip Question, and Flip the Question.

#### 2.3.2 Technical Requirements
- **Hardware**: The backend server should have at least 4GB of RAM and 2 CPUs.
- **Software**: The backend shall be developed in Java using Spring Boot, and the frontend shall be developed using React.

#### 2.3.3 Estimates
| #  | Description                       | Hrs. Est. |
|----|-----------------------------------|-----------|
| 1  | Frontend development (UI)         | 12        |
| 2  | Backend development (API)         | 40        |
| 3  | Database design and integration   | 5        |
| 4  | Testing and QA                    | 10        |
| **Total** |                           | **150**   |

#### 2.3.4 Traceability Matrix
| SRS Requirement | SDD Module                                |
|-----------------|-------------------------------------------|
| Req 1           | 5.1.1 (link to module), 5.1.2 (link)      |
| Req 2           | 5.2.1 (link to module), 5.2.2 (link)      |

## System Architecture

### 3.1 Overview
The system is built on a microservices architecture with a React frontend and a Spring Boot backend. MongoDB is used for data storage. The backend manages game logic, while the frontend handles user interaction.

### 3.2 Architectural Diagrams
(Include diagrams here if possible or a link to where they can be found.)

## Data Dictionary
### 4.1 Overview
The data dictionary includes a detailed description of the data entities used in the system, including collections in MongoDB.

| Table/Collection | Field      | Notes                       | Type    |
|------------------|------------|-----------------------------|---------|
| question         | id         | Unique Identifier           | ObjectId|
|                  | text       | question                    | String  |
|                  | answers    | answers                     | Array   |
|                  | correctAnswer| correct answer            | String  |
|                  | difficulty | difficulty level            | Integer |
|                  | timeLimit  | time limit                  | Integer |

## User Interface Design
### 5.1 User Interface Design Overview
Mockups and high-level design requirements for the UI.

### 5.2 User Interface Navigation Flow
Just one Screen.

### 6.2 Outstanding Issues
none 

## References
### 7.1 References
Takes heavy inspiration from the original "Who Wants To Be A Millionaire" T.V. show.


