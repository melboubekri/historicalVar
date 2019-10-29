package model;

import java.time.LocalDate;

public class ValueRisk {

    private LocalDate period;
    private Double profitOrLose;

    public LocalDate getPeriod() {
        return period;
    }

    public void setPeriod(LocalDate period) {
        this.period = period;
    }

    public Double getProfitOrLose() {
        return profitOrLose;
    }

    public void setProfitOrLose(Double profitOrLose) {
        this.profitOrLose = profitOrLose;
    }
}
