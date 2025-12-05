package habittracker.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Habit {
    private final UUID id;
    private String name;
    private String description;
    private int currentStreak;
    private int longestStreak;
    private int targetStreak;
    private HabitSchedule schedule;
    private final List<Completion> completions;
    private final List<Reminder> reminders;

    public Habit(String name, String description, HabitSchedule schedule) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.schedule = schedule;
        this.completions = new ArrayList<>();
        this.reminders = new ArrayList<>();
        this.targetStreak = 7; // default
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public int getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(int longestStreak) {
        this.longestStreak = longestStreak;
    }

    public int getTargetStreak() {
        return targetStreak;
    }

    public void setTargetStreak(int targetStreak) {
        this.targetStreak = targetStreak;
    }

    public HabitSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(HabitSchedule schedule) {
        this.schedule = schedule;
    }

    public List<Completion> getCompletions() {
        return completions;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void addCompletion(LocalDate date) {
        completions.add(new Completion(date));
        StreakCalculator.updateStreaks(this);
    }

    public boolean hasCompletionOn(LocalDate date) {
        return completions.stream().anyMatch(c -> c.getDate().equals(date));
    }
}
