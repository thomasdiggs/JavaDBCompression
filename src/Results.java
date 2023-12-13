import java.util.ArrayList;

public class Results {

    public ArrayList<DataItem> compressedData = new ArrayList<>();
    public ArrayList<DataItem> deletedData = new ArrayList<>();

    @Override
    public String toString() {
        return "\nPreserved: " + compressedData.size() + "\nRemoved: " + deletedData.size();
    }

    public void showResults() {
        System.out.println("\nCompressed Data\n----------");
        for (DataItem datum : compressedData) {
            System.out.println(datum);
        }
        System.out.println("\nDeleted Data\n----------");
        for (DataItem datum : deletedData) {
            System.out.println(datum);
        }
    }

}
