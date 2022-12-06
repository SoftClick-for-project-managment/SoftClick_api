package softclick.server.webtier.utils.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {
    public static final String DATETIME_FORMAT = "dd/M/yyyy HH:mm";

    public static LocalDateTime valueOf(String dateTimeString){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
        return LocalDateTime.parse(dateTimeString, dtf);
    }
    public static String toString(LocalDateTime dateTime){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
        return dateTime.format(dtf);
    }
}
