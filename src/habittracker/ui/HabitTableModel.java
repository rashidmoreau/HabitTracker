package habittracker.ui;

import habittracker.models.Habit;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class HabitTableModel extends AbstractTableModel {

    private final List<Habit> habits;
    private final String[] columns = {"Name", "Description", "Current Streak", "Longest Streak", "Schedule"};

    public HabitTableModel(List<Habit> habits) {
        this.habits = habits;
    }

    @Override
    public int getRowCount() {
        return habits.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Habit h = habits.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> h.getName();
            case 1 -> h.getDescription();
            case 2 -> h.getCurrentStreak();
            case 3 -> h.getLongestStreak();
            case 4 -> h.getSchedule().toString();
            default -> "";
        };
    }

    public Habit getHabitAt(int row) {
        return habits.get(row);
    }

    public void refresh() {
        fireTableDataChanged();
    }
}
