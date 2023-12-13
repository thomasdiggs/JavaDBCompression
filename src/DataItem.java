import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DataItem {

    private final String rowID;
    private final double floatValue;
    private final int quality;
    private final LocalDateTime timestamp;
    private final ZoneId zoneId = ZoneId.systemDefault();

    public DataItem(String rowID, double floatValue, int quality, String timestamp) {
        this.rowID = rowID;
        this.floatValue = floatValue;
        this.quality =  quality;
        this.timestamp = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:SSS"));
    }

    public String getRowID() { return rowID; }

    public double getValue() { return floatValue; }

    public int getQuality() { return quality; }

    public String getTimestamp() { return timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")); }

    public long getEpochTime() { return timestamp.atZone(zoneId).toEpochSecond(); }

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
