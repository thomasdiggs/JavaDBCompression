import java.util.ArrayList;

public class Results {

    public ArrayList<DataItem> compressedData = new ArrayList<>();
    public ArrayList<DataItem> deletedData = new ArrayList<>();

    @Override
    public String toString() {
        return "\nKept: " + compressedData.size() + "\nDeleted: " + deletedData.size() + "\n";
    }

}
