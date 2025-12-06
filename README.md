# Habit Tracker 

## Overview

The **Habit Tracker** is a Java desktop application designed to help users build and maintain positive daily habits. The project emphasizes clean software design, following principles of **low coupling** and **high cohesion**, with clear separation between Models, Services, and the User Interface.

Users can register, log in, create habits, track daily completions, and view weekly summaries of their progress with streak tracking. The application is built using **Java 17** and **Swing**, following an MVC-style architecture to ensure maintainability and scalability.

---

## 🧱 Layer Descriptions

### **Models (`models/`)**
Represents the core data of the system. These classes define the application's domain objects and their internal state. This layer includes:

- `User`
- `Habit`
- `HabitSchedule`
- `Completion`
- `Reminder`
- `SummaryReport`
- `Notification` classes
- `StreakCalculator`

Each model class has a single responsibility and does not manage application workflow or logic beyond its own state.

---

### **Services (`services/`)**
Contains the business logic of the application. These classes coordinate operations between models and ensure proper system behavior. This layer includes:

- `AuthService` and `InMemoryAuthService` for authentication
- `HabitService` for habit creation, updating completions, generating summaries, and orchestrating streak calculations

The service layer allows the UI to remain lightweight while preventing tight coupling between data and presentation.

---

### **User Interface (`ui/`)**
Implements the Swing-based graphical interface. These classes manage user interaction and display information but do not contain business logic. This layer includes:

- `LoginFrame` — handles user login and registration
- `DashboardFrame` — main application window for habit management
- `HabitTableModel` — table model used to display habits in the UI

This separation ensures that UI components remain focused on interaction and visualization only.

---

## ✨ Features

### ✔ User Management
- Create new user accounts  
- Log in with existing credentials  

### ✔ Habit Management
- Add new habits with custom descriptions  
- Edit or delete habits  
- Set reminder times  
- View all habits in a dashboard table  

### ✔ Daily Tracking
- Mark habits as completed for the current day  
- Automatic streak calculation:
  - Current streak
  - Longest streak

### ✔ Weekly Summary
- View total completions for the week  
- Breakdown of completions by habit  

### ✔ Clean Architecture Principles
- High cohesion within each class  
- Low coupling across layers  
- Clear separation of concerns (Models, Services, UI)  
- Easily extendable for future features such as persistence, enhanced reminders, or monthly reports

✔ Weekly Summary Report

View total completions for the current week

Breakdown of completions per habit
