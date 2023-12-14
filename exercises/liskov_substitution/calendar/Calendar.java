package calendar;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Calendar {

    private List<Event> events;

    public Calendar(List<Event> events) {
        this.events = events;
    }

    public Calendar() {
        this(new ArrayList<>());
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void inviteToAllEvents(String attendee) {
        for (Event event : events) {
            try {
                event.addAttendee(attendee);
            } catch (InvalidAttendeeException e) {
                System.out.println(
                    "Could not invite " +
                    attendee +
                    " to " +
                    event.getEventType() +
                    ": " +
                    event.getTitle()
                );
            }
        }
    }

    public void removeFromAllEvents(String attendee) {
        for (Event event : events) {
            try {
                event.removeAttendee(attendee);
            } catch (AttendeeNotFoundException ignored) {}
        }
    }

    public void moveAllEvents(Duration delta) {
        for (Event event : events) {
            event.move(delta);
        }
    }

    public Map<LocalDate, List<Event>> groupEventsByDay() {
        return events
            .stream()
            .sorted(Comparator.comparing(Event::getStart))
            .collect(
                Collectors.groupingBy(
                    event -> event.getStart().toLocalDate(),
                    TreeMap::new,
                    Collectors.toList()
                )
            );
    }

    // This method returns an initialized Calendar with some example events
    public static Calendar createExampleCalendar() {
        Calendar calendar = new Calendar();

        calendar.addEvent(
            new Event(
                "Revise the Open Closed principle",
                LocalDateTime.of(2020, 9, 19, 11, 0),
                Duration.ofMinutes(150),
                new ArrayList<>(List.of("George Eeliot"))
            )
        );

        calendar.addEvent(
            new Event(
                "Skiller Whale Session",
                LocalDateTime.of(2020, 9, 20, 15, 0),
                Duration.ofMinutes(60),
                new ArrayList<>(List.of("Salmon Rushdie"))
            )
        );

        calendar.addEvent(
            new Event(
                "Learn about Orca Culture",
                LocalDateTime.of(2020, 9, 20, 16, 30),
                Duration.ofMinutes(120)
            )
        );

        return calendar;
    }
}
