import calendar.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Exercise1 {

    public static void main(String[] args) {
        Calendar calendar = new Calendar();

        calendar.addEvent(
            new Event(
                "Revise the Open Closed principle",
                LocalDateTime.of(2020, 9, 19, 11, 0),
                Duration.ofMinutes(150),
                Arrays.asList("George Eeliot")
            )
        );

        calendar.addEvent(
            new Event(
                "Skiller Whale Session",
                LocalDateTime.of(2020, 9, 20, 15, 0),
                Duration.ofMinutes(60),
                Arrays.asList("Salmon Rushdie")
            )
        );

        calendar.addEvent(
            new Event(
                "Learn about Orca Culture",
                LocalDateTime.of(2020, 9, 20, 16, 30),
                Duration.ofMinutes(120)
            )
        );

        // TODO: Uncomment this code after defining Meeting
        // calendar.addEvent(
        //     new Meeting(
        //         "Important Discussions",
        //         LocalDateTime.of(2020, 9, 21, 17, 0),
        //         Duration.ofMinutes(45),
        //         "Surf Boardroom",
        //         Arrays.asList("Sealion Dion")
        //     )
        // );

        new CalendarPrinter().print(calendar);
    }
}
