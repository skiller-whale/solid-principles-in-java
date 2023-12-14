package calendar;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Event {

    private String title;
    private LocalDateTime start;
    private Duration duration;
    private List<String> attendees;

    public Event(
        String title,
        LocalDateTime start,
        Duration duration,
        List<String> attendees
    ) {
        this.title = title;
        this.start = start;
        this.duration = duration;
        this.attendees = attendees;
    }

    public Event(String title, LocalDateTime start, Duration duration) {
        this(title, start, duration, new ArrayList<>(List.of("Me")));
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Duration getDuration() {
        return duration;
    }

    public List<String> getAttendees() {
        return new ArrayList<>(attendees); // Return a copy of the attendee list
    }

    public void move(Duration delta) {
        start = start.plus(delta);
    }

    // Add a new attendee to the event, throwing an exception if invalid.
    public void addAttendee(String attendee) throws InvalidAttendeeException {
        if (attendee.contains("/")) {
            throw new InvalidAttendeeException(
                "Name cannot contain the '/' character"
            );
        }
        attendees.add(attendee);
    }

    // Remove the attendee from the event, throwing an exception if missing.
    public void removeAttendee(String attendee)
        throws AttendeeNotFoundException {
        if (attendees.isEmpty() || !attendees.remove(attendee)) {
            throw new AttendeeNotFoundException(
                "Could not remove " + attendee + " from attendees"
            );
        }
    }

    // Returns a Map of field names and values that summarise the event.
    public Map<String, String> displayFields() {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("Attendees", String.join(", ", attendees));
        fields.putAll(additionalDisplayFields());
        return fields;
    }

    // Method to allow subclasses to add additional fields to the display
    protected Map<String, String> additionalDisplayFields() {
        // Default implementation, can be overridden by subclasses
        return new LinkedHashMap<>();
    }

    // Can be overidden by subclasses to display different
    public String getEventType() {
        return "Event";
    }
}
