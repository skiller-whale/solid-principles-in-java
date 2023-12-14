package calendar;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AllDayEvent {

    private String title;
    private LocalDate date;
    private List<String> attendees;

    public AllDayEvent(String title, LocalDate date) {
        this.title = title;
        this.date = date;
        this.attendees = new ArrayList<>();
        this.attendees.add("Me");
    }

    public AllDayEvent(String title, LocalDate date, List<String> attendees) {
        this(title, date);
        this.attendees.addAll(attendees);
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<String> getAttendees() {
        return new ArrayList<>(attendees); // Return a copy of the attendee list
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
        if (!attendees.remove(attendee)) {
            throw new AttendeeNotFoundException(
                "Could not remove " + attendee + " from attendees"
            );
        }
    }

    // AllDayEvents should always start at midnight
    public void move(Duration delta) {}

    public String getType() {
        return "AllDayEvent";
    }
}
