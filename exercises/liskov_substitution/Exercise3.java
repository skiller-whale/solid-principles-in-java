import calendar.*;
import java.time.LocalDateTime;

public class Exercise3 {

    public static void main(String[] args) {
        Calendar calendar = Calendar.createExampleCalendar();

        calendar.addEvent(
            new Reminder(
                "Feed Sharkimedes",
                LocalDateTime.of(2020, 9, 20, 16, 15)
            )
        );

        // TODO 1 - Uncomment the line below
        // calendar.removeFromAllEvents("Me"); // Calls removeAttendee on each Event

        // TODO 2 - Uncomment the line below
        // calendar.inviteToAllEvents("Ernest Herringway"); // Calls addAttendee on each Event

        new CalendarPrinter().print(calendar);
    }
}
