package calendar;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reminder extends Event {

    public Reminder(String title, LocalDateTime time) {
        // Create an event with a duration of 0 seconds and no attendees
        super(title, time, Duration.ofSeconds(0), List.of());
    }

    @Override
    public List<String> getAttendees() {
        return new ArrayList<>();
    }

    @Override
    public void removeAttendee(String name)
        throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
            "Cannot remove attendees from a Reminder"
        );
    }

    @Override
    public String getEventType() {
        return "Reminder";
    }
}
