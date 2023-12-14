package calendar;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalendarPrinter {

    private static final int WIDTH = 64;
    private EventFormatter eventFormatter;

    public CalendarPrinter(EventFormatter eventFormatter) {
        this.eventFormatter = eventFormatter;
    }

    public CalendarPrinter() {
        this(new EventFormatter());
    }

    public void print(Calendar calendar) {
        System.out.println();
        printBar();
        printLine(
            String.format(
                "%" + ((WIDTH + "CALENDAR".length()) / 2) + "s",
                "CALENDAR"
            )
        );
        printBar();
        for (Map.Entry<LocalDate, List<Event>> entry : calendar
            .groupEventsByDay()
            .entrySet()) {
            printDay(entry.getKey(), entry.getValue());
        }
        System.out.println();
    }

    private void printDay(LocalDate date, List<Event> events) {
        String dateString = formatDate(date);
        printLine("");
        printLine(dateString);
        printLine(
            String.format("%" + dateString.length() + "s", "").replace(' ', '-')
        );
        printLine("");
        for (Event event : events) {
            for (String line : eventFormatter.format(event)) {
                printLine(line);
            }
            printLine("");
        }
        printBar();
    }

    private static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "EEEE dd MMM, yyyy"
        );
        return date.format(formatter);
    }

    private static void printLine(String text) {
        if (text.length() > WIDTH) {
            text = text.substring(0, WIDTH - 3) + "...";
        }
        System.out.println(
            "| " + String.format("%-" + WIDTH + "s", text) + " |"
        );
    }

    private static void printBar() {
        System.out.println("+" + "-".repeat(WIDTH + 2) + "+");
    }
}

// For the purposes of this session, this class can be ignored. In a real
// system, this would be a separate class, or interface.
class EventFormatter {

    // Returns a list of strings, each of which is a line to be printed for the
    // given event.
    static List<String> format(Event event) {
        String time = formatTimeRange(event);
        String eventType = event.getEventType();
        String leftText = String.format("%s (%s) ", time, eventType);
        Integer leftPad = leftText.length();

        List<String> lines = new ArrayList<>();
        lines.add(leftText + event.getTitle());

        for (Map.Entry<String, String> entry : event
            .displayFields()
            .entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();

            if (value == null || value.isEmpty()) {
                continue;
            }

            lines.add(String.format("%" + leftPad + "s%s: %s", "", key, value));
        }
        return lines;
    }

    private static String formatTimeRange(Event event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime start = event.getStart();
        LocalDateTime end = start.plus(event.getDuration());
        if (start == end) {
            return start.format(formatter);
        }
        return String.format(
            "%s - %s",
            start.format(formatter),
            end.format(formatter)
        );
    }
}
