package habittracker.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.Set;

public class HabitSchedule {
    private Set<DayOfWeek> days;
    private LocalTime reminderTime;

    public HabitSchedule(Set<DayOfWeek> days, LocalTime reminderTime) {
        this.days = EnumSet.copyOf(days);
        this.reminderTime = reminderTime;
    }

    public Set<DayOfWeek> getDays() {
        return days;
    }

    public void setDays(Set<DayOfWeek> days) {
        this.days = EnumSet.copyOf(days);
    }

    public LocalTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public boolean isScheduledOn(LocalDate date) {
        return days.contains(date.getDayOfWeek());
    }

    @Override
    public String toString() {
        return days.toString();
    }
}
