import service.RegulatorService;

import java.util.Scanner;

public class HistoricalVar {

    public static void main(String argv[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date");
        String currentDate = scanner.next();
        System.out.println("Enter depth");
        boolean depthKO = false;
        while (!depthKO) {
            try {
                Integer depth = scanner.nextInt();
                depthKO = true;
            } catch (Exception e) {
                System.out.println("put valid depth");
            }
        }
        System.out.println("Enter percent");
        Integer percent = scanner.nextInt();
        System.out.println("Enter path file");
        String filePath = scanner.next();
        RegulatorService regulatorService = new RegulatorService();


    }
}
