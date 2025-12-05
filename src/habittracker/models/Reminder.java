package habittracker.model;

import java.time.LocalTime;
import java.util.UUID;

public class Reminder {
    private final UUID id;
    private LocalTime time;

    public Reminder(LocalTime time) {
        this.id = UUID.randomUUID();
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
