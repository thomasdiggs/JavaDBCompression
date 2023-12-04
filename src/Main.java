
public class Main {
    public static void main(String[] args) {

        String filePath = "C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/TestData.csv";
//        String filePath = "C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/tagid-4028--table-sqlt_data4-2022_08.csv";

        CompressionUtility.compressData(CSVReaderUtility.CSVReader(filePath), 0.123);

    }

}