package carbontracker.thread;

import carbontracker.service.CarbonTrackerService;

public class ReportGeneratorThread extends Thread {                 // Multithreading is used in ReportGeneratorThread where emission calculation runs separately from main thread 

    private CarbonTrackerService service;

    public ReportGeneratorThread(CarbonTrackerService service) {                        // service is passed for thread to track activities all over to generate the report
        this.service = service;
    }

    @Override
    public void run() {
        System.out.println("\nGenerating emission report in background...");

        double total = service.calculateTotalEmission();

        System.out.println("Total Carbon Emission = " + total);

        System.out.println("Report generation completed.");
    }

}
