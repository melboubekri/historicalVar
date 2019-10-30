import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.amundi.historicalvar.exception.HistoricalVarException;
import com.amundi.historicalvar.service.RegulatorService;

public class RegulatorTest {

	@Test
	public void test_calculate_Var_Percentile() throws HistoricalVarException {
		RegulatorService regulatorService = new RegulatorService();
		String result = "-656 565,21";
		String reelResult = regulatorService.calculateVarPercentile("2018-09-12", 180, 5, "Historical_PNL_2018.csv");
		assertEquals(result, reelResult);
	}

	@Test
	public void calculate_Var_Percentile_Without_Valid_Historic() {
		RegulatorService regulatorService = new RegulatorService();
		try {
			String reelResult = regulatorService.calculateVarPercentile("2018-01-01", 180, 5,
					"Historical_PNL_2018.csv");
		} catch (HistoricalVarException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void calculate_Var_Percentile_Without_Valid_Percentile() {
		RegulatorService regulatorService = new RegulatorService();
		try {
			String reelResult = regulatorService.calculateVarPercentile("2018-01-01", 180, -5,
					"Historical_PNL_2018.csv");
		} catch (HistoricalVarException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_calculate_Var_Percentile_Without_Valid_File() throws HistoricalVarException {
		RegulatorService regulatorService = new RegulatorService();
		try {
			String reelResult = regulatorService.calculateVarPercentile("2018-09-12", 180, 5, "Historical_PNL.csv");
		} catch (HistoricalVarException e) {
			e.printStackTrace();
		}
	}

}
