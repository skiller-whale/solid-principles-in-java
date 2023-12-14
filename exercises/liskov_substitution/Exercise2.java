import calendar.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

public class Exercise2 {

    public static void main(String[] args) {
        Calendar calendar = Calendar.createExampleCalendar();

        calendar.addEvent(
            new AllDayEvent(
                "Very Long Event",
                LocalDate.of(2020, 9, 21),
                Arrays.asList("Sealion Dion")
            )
        );

        new CalendarPrinter().print(calendar);
    }
}
