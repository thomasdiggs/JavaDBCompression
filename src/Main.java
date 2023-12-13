import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        double deadband = 0.05;
        String inputFilePath = "C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/input.csv";
        String outputFilePath = "C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/output0.05DDMMYYYHHMMSS.csv";

        CSVUtilities csvUtility = new CSVUtilities();
        ArrayList<DataItem> data = csvUtility.reader(inputFilePath);
        CompressionUtility compression = new CompressionUtility();
        Results results = compression.compressData(data, deadband);
        csvUtility.writer(results.compressedData, outputFilePath);

//        csvUtility.writer(data,"C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/inputDDMMYYYHHMMSS.csv");

        System.out.println(results);

    }

}