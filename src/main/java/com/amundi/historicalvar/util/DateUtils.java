package com.amundi.historicalvar.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DateUtils {

	public static String formatNumber(BigDecimal number) {
		String pattern = "##,###.###";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		String format = decimalFormat.format(number);
		return format;
	}

}
