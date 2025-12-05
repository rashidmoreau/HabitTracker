package habittracker.model;

import java.time.LocalTime;

public class UserSettings {
    private LocalTime defaultReminderTime = LocalTime.of(9, 0);
    private boolean weeklySummaryEnabled = true;
    private boolean monthlySummaryEnabled = true;

    public LocalTime getDefaultReminderTime() {
        return defaultReminderTime;
    }

    public void setDefaultReminderTime(LocalTime defaultReminderTime) {
        this.defaultReminderTime = defaultReminderTime;
    }

    public boolean isWeeklySummaryEnabled() {
        return weeklySummaryEnabled;
    }

    public void setWeeklySummaryEnabled(boolean weeklySummaryEnabled) {
        this.weeklySummaryEnabled = weeklySummaryEnabled;
    }

    public boolean isMonthlySummaryEnabled() {
        return monthlySummaryEnabled;
    }

    public void setMonthlySummaryEnabled(boolean monthlySummaryEnabled) {
        this.monthlySummaryEnabled = monthlySummaryEnabled;
    }
}
