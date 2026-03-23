package temporal;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DemoTemporal {

    // NB
    // - Java 1.0 : class java.util.Date
    // - Java 1.1 : class java.util.Calendar, GregorianCalendar
    // - Java 8 (ISO 8061)
    //      LocalDate
    //      LocalDateTime
    //      LocalTime
    //      ZonedDateTime
    //      Duration/Period


    @Test
    void demoLocalDate(){
        // keyword var (Java 11)
        var today = LocalDate.now();
        System.out.println(today);
    }
}
