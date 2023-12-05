import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String filePath = "C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/input.csv";
        String outputFilePath = "C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/output.csv";
//        String filePath = "C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/tagid-4028--table-sqlt_data4-2022_08.csv";

        CSVReaderUtility csvReader = new CSVReaderUtility();
        ArrayList<DataItem> data = csvReader.CSVReader(filePath);

        CompressionUtility compression = new CompressionUtility();
        Results results = compression.compressData(data, 0.05);
        CSVWriterUtility.csvWriter(results.compressedData, outputFilePath);
        System.out.println(results);

    }

}