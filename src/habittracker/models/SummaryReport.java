package habittracker.model;

import java.time.LocalDate;
import java.util.Map;

public class SummaryReport {
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private int totalCompletions;
    private Map<String, Integer> completionsByHabitName;

    public SummaryReport(LocalDate periodStart, LocalDate periodEnd,
                         int totalCompletions, Map<String, Integer> byHabit) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.totalCompletions = totalCompletions;
        this.completionsByHabitName = byHabit;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public int getTotalCompletions() {
        return totalCompletions;
    }

    public Map<String, Integer> getCompletionsByHabitName() {
        return completionsByHabitName;
    }
}
