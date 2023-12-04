import java.util.ArrayList;

public class Results {

    public ArrayList<DataItem> compressedData = new ArrayList<>();
    public ArrayList<DataItem> deletedData = new ArrayList<>();

    @Override
    public String toString() {
        return "\nKept: " + compressedData.size() + "\nDeleted: " + deletedData.size() + "\n";
    }

    public void showContents() {

        System.out.println("Compressed Data: ");
        for (DataItem item : compressedData) {
            System.out.println(item);
        }

        System.out.println("\nDeleted Data: ");
        for (DataItem item : deletedData) {
            System.out.println(item);
        }

    }

}
