package habittracker.ui;

import habittracker.models.Habit;
import habittracker.models.HabitSchedule;
import habittracker.models.SummaryReport;
import habittracker.models.User;
import habittracker.services.HabitService;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.Set;

public class DashboardFrame extends JFrame {

    private final User user;
    private final HabitService habitService;
    private HabitTableModel tableModel;
    private JTable table;

    public DashboardFrame(User user, HabitService habitService) {
        super("Habit Tracker - " + user.getUsername());
        this.user = user;
        this.habitService = habitService;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        JLabel header = new JLabel("Today's Habits & Streaks", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 16f));
        add(header, BorderLayout.NORTH);

        tableModel = new HabitTableModel(user.getHabits());
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton addButton = new JButton("Add Habit");
        JButton editButton = new JButton("Edit Habit");
        JButton deleteButton = new JButton("Delete Habit");
        JButton completeButton = new JButton("Mark Today Completed");
        JButton summaryButton = new JButton("Weekly Overview");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addButton);
        bottomPanel.add(editButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(completeButton);
        bottomPanel.add(summaryButton);

        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addHabit());
        editButton.addActionListener(e -> editHabit());
        deleteButton.addActionListener(e -> deleteHabit());
        completeButton.addActionListener(e -> markCompleted());
        summaryButton.addActionListener(e -> showWeeklySummary());
    }

    private Habit getSelectedHabit() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a habit first.");
            return null;
        }
        return tableModel.getHabitAt(row);
    }

private void addHabit() {
    String name = JOptionPane.showInputDialog(this, "Habit name:");
    if (name == null || name.isBlank()) return;

    String desc = JOptionPane.showInputDialog(this, "Description (optional):");
    if (desc == null) desc = "";

    // ask user for a reminder time
    String timeStr = JOptionPane.showInputDialog(this,
            "Reminder time (HH:MM, 24h, default 09:00):");
    LocalTime time = LocalTime.of(9, 0);
    try {
        if (timeStr != null && !timeStr.isBlank()) {
            time = LocalTime.parse(timeStr.trim());
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Invalid time, using 09:00.");
    }

    ScheduleDialog sd = new ScheduleDialog(this, EnumSet.allOf(DayOfWeek.class));
    sd.setVisible(true);

    if (!sd.isConfirmed()) {
        return;
    }

    Set<DayOfWeek> selectedDays = sd.getSelectedDays();

    // make schedule with selected days
    HabitSchedule schedule = new HabitSchedule(selectedDays, time);

    Habit habit = new Habit(name, desc, schedule);
    user.addHabit(habit);

    tableModel.refresh();
}


    private void editHabit() {
        Habit h = getSelectedHabit();
        if (h == null) return;

        String newName = JOptionPane.showInputDialog(this, "New name:", h.getName());
        if (newName != null && !newName.isBlank()) {
            h.setName(newName.trim());
        }

        String newDesc = JOptionPane.showInputDialog(this, "New description:", h.getDescription());
        if (newDesc != null) {
            h.setDescription(newDesc);
        }

        ScheduleDialog sd = new ScheduleDialog(this, h.getSchedule().getDays());
        sd.setVisible(true);

        if (sd.isConfirmed()) {
            h.getSchedule().setDays(sd.getSelectedDays());
        }

        tableModel.refresh();
    }


    private void deleteHabit() {
        Habit h = getSelectedHabit();
        if (h == null) return;

        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete habit '" + h.getName() + "'?", "Confirm",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            habitService.deleteHabit(user, h);
            tableModel.refresh();
        }
    }

    private void markCompleted() {
        Habit h = getSelectedHabit();
        if (h == null) return;
        habitService.markTodayCompleted(h);
        tableModel.refresh();
    }

    private void showWeeklySummary() {
        SummaryReport r = habitService.generateWeeklySummary(user);
        StringBuilder sb = new StringBuilder();
        sb.append("Weekly summary (")
          .append(r.getPeriodStart()).append(" to ").append(r.getPeriodEnd()).append(")\n\n");
        sb.append("Total completions: ").append(r.getTotalCompletions()).append("\n\n");
        r.getCompletionsByHabitName().forEach((name, count) ->
                sb.append(name).append(": ").append(count).append(" days\n"));
        if (r.getCompletionsByHabitName().isEmpty()) {
            sb.append("No completions this week yet.");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Weekly Overview",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
