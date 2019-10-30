package com.amundi.historicalvar.service;

import java.io.BufferedReader;
import org.apache.commons.collections4.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.amundi.historicalvar.exception.HistoricalVarException;
import com.amundi.historicalvar.model.ValueRisk;
import com.amundi.historicalvar.util.DateUtils;
import com.amundi.historicalvar.util.ParameterChecker;

public class RegulatorService {

	public String calculateVarPercentile(String inputDate, Integer historicalDepth, Integer percentile, String filePath)
			throws HistoricalVarException {
		LocalDate currentDate = ParameterChecker.parseDate(inputDate);
		if (currentDate != null) {
			if (ParameterChecker.checkPercentile(percentile)) {
				List<ValueRisk> valueRisks = processInputFile(filePath);
				if (!CollectionUtils.isEmpty(valueRisks)) {
					List<ValueRisk> filteredValueRisks = valueRisks.stream()
							.filter(valueRisk -> (currentDate.isAfter(valueRisk.getPeriod())
									&& currentDate.minusDays(historicalDepth).isBefore(valueRisk.getPeriod())))
							.collect(Collectors.toList());
					if (!CollectionUtils.isEmpty(filteredValueRisks)) {
						List<ValueRisk> sortedValueRisk = sortValueRiskByValue(filteredValueRisks);
						double x = sortedValueRisk.size() * percentile * 1.0 / 100;
						double index = Math.round(x);
						return DateUtils.formatNumber(sortedValueRisk.get((int) index).getProfitOrLose());

					} else {
						throw new HistoricalVarException("filtered historic is empty");

					}
				} else {
					throw new HistoricalVarException("there is no historic of profit or loss before this date");
				}
			} else {
				throw new HistoricalVarException("please enter a valid percentile");
			}
		}
		return null;
	}

	private List<ValueRisk> processInputFile(String inputFilePath) throws HistoricalVarException {
		List<ValueRisk> inputList = new ArrayList<>();
		try {
			File inputF = new File(getClass().getClassLoader().getResource(inputFilePath).getFile());
			InputStream inputFS = new FileInputStream(inputF);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
			inputList = br.lines().map(mapToItem).collect(Collectors.toList());
			br.close();
		} catch (Exception e) {
			throw new HistoricalVarException("file not found or wrong name");
		}
		return inputList;
	}

	private Function<String, ValueRisk> mapToItem = (line) -> {
		String[] p = line.split(";");
		ValueRisk item = new ValueRisk();
		item.setPeriod(ParameterChecker.parseDate(p[0]));
		item.setProfitOrLose(BigDecimal.valueOf(Double.parseDouble(p[1])));
		return item;
	};

	private List<ValueRisk> sortValueRiskByValue(List<ValueRisk> valueRisks) {
		return valueRisks.stream().sorted(Comparator.comparing(ValueRisk::getProfitOrLose))
				.collect(Collectors.toList());
	}

}
