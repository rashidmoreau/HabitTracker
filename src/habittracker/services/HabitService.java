package habittracker.services;

import habittracker.models.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class HabitService {

    public Habit createDailyHabit(User user, String name, String description, LocalTime reminderTime) {
        HabitSchedule schedule = new HabitSchedule(
                EnumSet.allOf(DayOfWeek.class),
                reminderTime
        );
        Habit habit = new Habit(name, description, schedule);
        habit.getReminders().add(new Reminder(reminderTime));
        user.addHabit(habit);
        return habit;
    }

    public void deleteHabit(User user, Habit habit) {
        user.removeHabit(habit);
    }

    public void markTodayCompleted(Habit habit) {
        habit.addCompletion(LocalDate.now());
    }

    public SummaryReport generateWeeklySummary(User user) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(6);

        int total = 0;
        Map<String, Integer> byHabit = new HashMap<>();

        for (Habit h : user.getHabits()) {
            int count = (int) h.getCompletions().stream()
                    .filter(c -> !c.getDate().isBefore(start) && !c.getDate().isAfter(end))
                    .count();
            if (count > 0) {
                byHabit.put(h.getName(), count);
                total += count;
            }
        }
        return new SummaryReport(start, end, total, byHabit);
    }
}
