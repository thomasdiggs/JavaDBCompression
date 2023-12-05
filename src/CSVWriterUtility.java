import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVWriterUtility {

public static void csvWriter(ArrayList<DataItem> data, String filepath) {
    try (FileWriter writer = new FileWriter(filepath)) {
        for (DataItem row : data) {
            writer.append(row.getRowID()).append(",");
            writer.append(Double.toString(row.getValue())).append(",");
            writer.append(Integer.toString(row.getQuality())).append(",");
            writer.append(Long.toString(row.getEpochTime()));
            writer.append("\n");
        }
    } catch (IOException e) {
        System.err.println("Error writing CSV file: " + e.getMessage());
    }
}

}
