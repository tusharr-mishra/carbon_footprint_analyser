package carbontracker.util;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {                 //Generates report and permanently saves in the file 

    public static void saveReport(double totalEmission) {
        FileWriter writer = null;                       // since file isn't opened yet and is in a reference for try block

        try {
            writer = new FileWriter("carbon_report.txt");                       // writes character data into a file
            writer.write("Carbon Footprint Report\n");
            writer.write("----------------------\n");
            writer.write("Total Emission: " + totalEmission + " kg CO2\n");
            writer.close();
            System.out.println("Report saved successfully");
        } catch (IOException e) {
            System.out.println("File error occurred");
        }
    }
}
