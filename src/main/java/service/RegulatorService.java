package service;

import model.ValueRisk;
import sun.awt.SunHints;
import util.ParameterChecker;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RegulatorService {

    private List<ValueRisk> processInputFile(String inputFilePath) {
        List<ValueRisk> inputList = new ArrayList<>();
        try {
            /*File inputF = new File(
                    getClass().getClassLoader().getResource("HISTORICAL_PNL_2018.csv").getFile());*/
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            inputList = br.lines().map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
        }
        return inputList;
    }

    private Function<String, ValueRisk> mapToItem = (line) -> {
        String[] p = line.split(",");// a CSV has comma separated lines
        ValueRisk item = new ValueRisk();
        item.setPeriod(ParameterChecker.parseDate(p[0]));
        item.setProfitOrLose(Double.parseDouble(p[1]));
        return item;
    };

    private List<ValueRisk> sortValueRiskByValue(List<ValueRisk> valueRisks) {
        return valueRisks.stream().sorted(Comparator.comparingDouble(ValueRisk::getProfitOrLose)).collect(Collectors.toList());
    }

    public Double calculateVarPercentile(String inputDate, Integer historicalDepth, Integer percentile, String filePath) {
        LocalDate currentDate = ParameterChecker.parseDate(inputDate);
        if (currentDate != null) {
            if (ParameterChecker.checkPercentile(percentile)) {
                List<ValueRisk> valueRisks = processInputFile(filePath);
                if (!valueRisks.isEmpty()) {
                    List<ValueRisk> filteredValueRisks = valueRisks.stream().filter(valueRisk -> (currentDate.isAfter(valueRisk.getPeriod()) &&
                            currentDate.minusDays(historicalDepth).isBefore(valueRisk.getPeriod()))).
                            collect(Collectors.toList());
                    List<ValueRisk> sortedValueRisk = sortValueRiskByValue(filteredValueRisks);
                    double x = sortedValueRisk.size() * percentile * 1.0 / 100;
                    double index = Math.round(x);
                    return sortedValueRisk.get((int) index).getProfitOrLose();
                }
            }
        }
        return null;
    }


}
