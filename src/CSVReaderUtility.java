import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReaderUtility {

    public ArrayList<DataItem> CSVReader(String filePath) {

        String line;

        ArrayList<DataItem> dataItems = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");

                // Extract values from the row
                String rowID = row[0];
                float floatValue = Float.parseFloat(row[1]);
                int quality = Integer.parseInt(row[2]);
                long timeStamp = Long.parseLong(row[3]);

                // Create a DataItem object and add it to the list
                DataItem item = new DataItem(rowID, floatValue, quality, timeStamp);
                dataItems.add(item);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataItems;

    }

}
