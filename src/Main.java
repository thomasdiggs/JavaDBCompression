import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<DataItem> example = new ArrayList<>();
        example.add(new DataItem("A",100.0,192,1636409614396L));
        example.add(new DataItem("B",150.0,192,1636409655838L));
        example.add(new DataItem("C",50.0,192,1636409701167L));
        example.add(new DataItem("D",50.001,192,1636409726809L));
        example.add(new DataItem("E",50.002,192,1636409760145L));
        example.add(new DataItem("F",100.0,192,1636409786810L));

//        String filePath = "C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/TestData.csv";
//        String filePath = "C:/Users/tdiggs/OneDrive - eogresources.com/Desktop/tagid-4028--table-sqlt_data4-2022_08.csv";
//
//        ArrayList<DataItem> data = CSVReaderUtility.CSVReader(filePath);

        CompressionUtility compression = new CompressionUtility();

        var results = compression.compressData(example, 0.01);
        System.out.println(results);

    }

}