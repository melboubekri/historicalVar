package com.amundi.historicalvar.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ValueRisk {

    private LocalDate period;
    private BigDecimal profitOrLose;

    public LocalDate getPeriod() {
        return period;
    }

    public void setPeriod(LocalDate period) {
        this.period = period;
    }

	public BigDecimal getProfitOrLose() {
		return profitOrLose;
	}

	public void setProfitOrLose(BigDecimal profitOrLose) {
		this.profitOrLose = profitOrLose;
	}

   
}
