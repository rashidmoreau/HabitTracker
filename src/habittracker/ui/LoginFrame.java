package habittracker.ui;

import habittracker.models.User;
import habittracker.services.AuthService;
import habittracker.services.HabitService;

import javax.swing.*;
import java.awt.*;

// login and registration window
public class LoginFrame extends JFrame {

    private final AuthService authService;
    private final HabitService habitService;

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    // creates login frame
    public LoginFrame(AuthService authService, HabitService habitService) {
        super("Habit Tracker - Login");
        this.authService = authService;
        this.habitService = habitService;
        initComponents();
    }

    // initialize UI components
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Email (for register):"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Log In");
        JButton registerButton = new JButton("Sign Up");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> register());
    }

    // handle login action
    private void login() {
        try {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            User user = authService.login(username, password);
            openDashboard(user);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // handle registration action
    private void register() {
        try {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());
            User user = authService.register(username, email, password);
            JOptionPane.showMessageDialog(this, "Account created. You are now logged in.");
            openDashboard(user);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // open dashboard for logged in user
    private void openDashboard(User user) {
        SwingUtilities.invokeLater(() -> {
            new DashboardFrame(user, habitService).setVisible(true);
        });
        dispose();
    }
}
