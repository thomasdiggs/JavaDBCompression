
public class DataItem {

    private final String rowID;
    private final double floatValue;
    private final long timestamp;
    private final int quality;

    public DataItem(String rowID, double floatValue, int quality, long timestamp) {
        this.rowID = rowID;
        this.floatValue = floatValue;
        this.quality =  quality;
        this.timestamp = timestamp;
    }

    public String getRowID() { return rowID; }

    public double getValue() { return floatValue; }

    public int getQuality() { return quality; }

    public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "DataItem{" +
                "rowID=" + this.rowID +
                ", floatValue=" + this.floatValue +
                ", quality=" + this.quality +
                ", timestamp=" + this.timestamp +
                '}';
    }

}
