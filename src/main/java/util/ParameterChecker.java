package util;

import java.time.LocalDate;

public class ParameterChecker {

    public static LocalDate parseDate(String date) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        return LocalDate.parse(date/*, formatter*/);
    }

    public static boolean checkPercentile(Integer percentile) {
        return 0 <= percentile && percentile <= 100;
    }
}
