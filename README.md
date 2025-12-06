# HabitTracker
Overview

The Habit Tracker is a Java desktop application designed to help users build and maintain positive daily habits. The project emphasizes clean software design, following principles of low coupling and high cohesion, with clear separation between Models, Services, and the User Interface.

Users can register, log in, create habits, track their daily completions, and view weekly summaries of their progress with streak tracking. The application is built using Java 17 and Swing, implementing an MVC-style architecture to ensure maintainability and scalability.

🧱 Layer Descriptions
Models (models/)

The Models layer contains classes that represent core data structures in the application, such as:

User

Habit

HabitSchedule

Completion

Reminder

SummaryReport

Notification classes

StreakCalculator

These classes hold data and simple logic relevant to their own state but do not manage workflow or application control.

Services (services/)

The Services layer provides business logic for the application. It includes:

AuthService / InMemoryAuthService for user registration and login

HabitService for creating habits, updating completions, generating summaries, and orchestrating streak calculations

Services allow the UI to remain lightweight and eliminate tight coupling between system components.

User Interface (ui/)

The UI layer contains the Swing-based graphical interface, including:

LoginFrame

DashboardFrame

HabitTableModel

These classes manage user interaction, display information, and delegate work to the service layer. The UI never contains business logic itself, preserving separation of concerns.

✨ Features
✔ User Management

Register new accounts

Log in with existing credentials

✔ Habit Management

Add new habits with custom descriptions

Set reminder times

Edit or delete habits

View habits in a table-based dashboard

✔ Daily Tracking

Mark habits as completed for the current day

Automatic streak updates:

Current streak

Longest streak

✔ Weekly Summary Report

View total completions for the current week

Breakdown of completions per habit
