import calendar.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Exercise4 {

    public static void main(String[] args) {
        Calendar calendar = Calendar.createExampleCalendar();

        calendar.addEvent(
            new AllDayEvent("Busy All Day", LocalDate.of(2020, 9, 21))
        );

        // TODO: Uncomment this line moving all events forward by 23 hours
        // calendar.moveAllEvents(Duration.ofHours(23));

        new CalendarPrinter().print(calendar);
    }
}
