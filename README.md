# 🏙️ LedWall Management System

Welcome to the **LedWall Management System**! 
This is a web application designed to manage, monitor, and display digital advertisements (*cartelloni*) on multiple LedWalls (*impianti*) distributed across the city. It allows administrators to easily configure schedules (*palinsesti*) and track the impressions and active status of each screen.

## 🛠️ Tech Stack
This project is built using modern and reliable technologies:
- **Backend:** Java 17, Spring Boot, Spring Security, Spring Data JPA
- **Frontend:** HTML5, CSS3, Vanilla JavaScript, Thymeleaf, Leaflet.js (for interactive maps)
- **Database:** MySQL 8 
- **Deployment:** Docker & Docker Compose (Multi-stage build)

## ✨ Main Features
- **LedWall Simulator (`/ledWall`):** A frontend view that displays rotating advertisements based on XML schedules, decorated with a real-time weather and time bar.
- **Admin Dashboard (`/gestione`):** A secure control panel to manage every LedWall, view them on a map, activate/deactivate screens, and change their daily schedules.
- **Reporting System (`/report`):** Track analytics, total impressions, and play durations for each advertisement using custom date filters.
- **Automated Tracking:** The screens send periodic signals to the backend to guarantee they are alive and to update impression metrics.

## 📸 Screenshots
*(Replace these placeholder links with your actual screenshots)*

![Dashboard GUI](images/placeholder1.png)
*Admin Dashboard for LedWall configuration.*

![LedWall View](images/placeholder2.png)
*Frontend view of an active LedWall.*

## 🚀 How to Install and Run
Thanks to Docker, running this project is incredibly easy! You don't need to manually install Java, Maven, or MySQL on your computer.

### Prerequisites
- [Docker](https://www.docker.com/products/docker-desktop/) installed and running on your machine.

### Setup Instructions
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name
   ```

2. **Start the application:**
   Open your terminal in the project folder and run:
   ```bash
   docker compose up --build
   ```
   *Docker will automatically download Java, compile the Spring Boot application, and set up the MySQL database with some initial dummy data.*

3. **Access the Application:**
   - **LedWall View:** http://localhost:8080/ledWall?id=2
   - **Admin Login:** http://localhost:8080/login *(Use the default credentials located in the `init.sql` file)*

### Stop the Application
To stop the execution, press `Ctrl+C` in your terminal or run:
```bash
docker compose down
```
