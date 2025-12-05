package habittracker;

import habittracker.services.AuthService;
import habittracker.services.HabitService;
import habittracker.services.InMemoryAuthService;
import habittracker.ui.LoginFrame;

import javax.swing.*;

public class HabitTrackerApp {

    public static void main(String[] args) {
        System.out.println("Starting Habit Tracker..."); 

        SwingUtilities.invokeLater(() -> {
            AuthService authService = new InMemoryAuthService();
            HabitService habitService = new HabitService();
            LoginFrame frame = new LoginFrame(authService, habitService);
            frame.setVisible(true);
        });
    }
}

