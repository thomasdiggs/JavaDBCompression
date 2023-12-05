import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String inputFilePath = "C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/input.csv";
        String outputFilePath = "C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/output.csv";
        double deadband = 0.05;

        CSVUtilities csvUtility = new CSVUtilities();
        ArrayList<DataItem> data = csvUtility.reader(inputFilePath);
        CompressionUtility compression = new CompressionUtility();
        Results results = compression.compressData(data, deadband);
        csvUtility.writer(results.compressedData, outputFilePath);

        System.out.println(results);

    }

}