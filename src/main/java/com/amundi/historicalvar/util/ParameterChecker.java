package com.amundi.historicalvar.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParameterChecker {

    public static LocalDate parseDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    public static boolean checkPercentile(Integer percentile) {
        return 0 <= percentile && percentile <= 100;
    }
}
