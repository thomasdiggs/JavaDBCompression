import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class CSVUtilities {

    public ArrayList<DataItem> reader (String filePath) {

        String line;

        ArrayList<DataItem> dataItems = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");

                // Extract values from the row
                String rowID = row[0];
                float floatValue = Float.parseFloat(row[1]);
                int quality = Integer.parseInt(row[2]);
                String timeStamp = row[3];

                // Create a DataItem object and add it to the list
                DataItem item = new DataItem(rowID, floatValue, quality, timeStamp);
                dataItems.add(item);
            }

        } catch (IOException e) {

            System.err.println("Error reading CSV file: " + e.getMessage());

        }

        return dataItems;

    }

    public void writer (ArrayList<DataItem> data, String filepath) {

        try (FileWriter writer = new FileWriter(filepath)) {

            for (DataItem row : data) {

                writer.append(row.getRowID()).append(",");
                writer.append(Double.toString(row.getValue())).append(",");
                writer.append(Integer.toString(row.getQuality())).append(",");
//                writer.append(Long.toString(row.getEpochTime()));
                writer.append(row.getTimestamp());
                writer.append("\n");

            }

        } catch (IOException e) {

            System.err.println("Error writing CSV file: " + e.getMessage());

        }

    }

}
