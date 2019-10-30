package com.amundi.historicalvar.exception;

import org.apache.log4j.Logger;

public class HistoricalVarException extends Exception {
	
	final static Logger LOGGER= Logger.getLogger(HistoricalVarException.class);

	public HistoricalVarException() {
		super();
	}

	public HistoricalVarException(String message) {
		super(message);
		LOGGER.error("[HistoricalVarException] : " + message);		
	}

}
