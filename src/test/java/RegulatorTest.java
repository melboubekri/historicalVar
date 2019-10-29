import org.junit.Test;
import service.RegulatorService;

public class RegulatorTest {


    @Test
    public void test_calcul() {
        RegulatorService regulatorService = new RegulatorService();
        System.out.println(regulatorService.calculateVarPercentile("2018-09-12", 180, 70, "C://Users/elboubekri/eclipse-workspace/historicalvar/src/main/resources/HISTORICAL_PNL_2018.csv"));
    }


}
