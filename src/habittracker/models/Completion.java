package habittracker.model;

import java.time.LocalDate;
import java.util.UUID;

public class Completion {
    private final UUID id;
    private final LocalDate date;
    private boolean enabled;

    public Completion(LocalDate date) {
        this.id = UUID.randomUUID();
        this.date = date;
        this.enabled = true;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
