package habittracker.models;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreakCalculator {

    public static void updateStreaks(Habit habit) {
        List<Completion> list = habit.getCompletions().stream()
                .filter(Completion::isEnabled)
                .sorted(Comparator.comparing(Completion::getDate))
                .collect(Collectors.toList());

        int longest = 0;
        int current = 0;

        LocalDate prevDate = null;
        for (Completion c : list) {
            LocalDate d = c.getDate();
            if (prevDate == null || d.equals(prevDate.plusDays(1))) {
                current++;
            } else if (d.isAfter(prevDate)) {
                current = 1;
            }
            prevDate = d;
            if (current > longest) longest = current;
        }

        // current streak is from today backwards
        current = 0;
        LocalDate day = LocalDate.now();
        while (habit.hasCompletionOn(day)) {
            current++;
            day = day.minusDays(1);
        }

        habit.setCurrentStreak(current);
        habit.setLongestStreak(longest);
    }
}
