/*
Summary of modified "Sliding Window" algorithm:

The first and last values are never evaluated for deletion. Those are saved into ArrayList<DataItem> compressedData for the
purposes of exporting to a CSV and compare trending against ArrayList<DataItem> uncompressedData. All values from each DataItem that
meet the criteria for retention are kept in compressedData (or more accurately, they are never added to a list for deletion).
ArrayList<DataItem> deletedData contains all the sets of data (equivalent to a row in a database) that did not meet the criteria
for retention and can safely be deleted.

The algorithm is always comparing the slope from last value to current value and current value to snapshot value (or the next value).
The current value is always being evaluated for deletion.

Upper Slope: (((NewValue + Deadband) - PreviousValue) / (NewTimestamp - PreviousTimestamp))
Lower Slope: (((NewValue - Deadband) - PreviousValue) / (NewTimestamp - PreviousTimestamp))

The algorithm will only store new values under the following conditions:
- The system always stores the first value on the tag when using the method, since the subsequent values will need an initial value to calculate slope from.
- If the newly calculated upper slope is lower than the previously calculated lower slope value, the system will store the new value.
- If the newly calculated lower slope is larger than the previously calculated upper slope value, the system will store the new value.
- The system always stores a value when the quality on the tag changes.

In cases where a new value isn't stored, the system will compare the newly calculated slope values to the previously calculated values:
- If the new upper slope is less than the previous upper slope, then the new upper slope is used for future comparisons.
- If the new lower slope is greater than the previous lower slope, then the new lower slope is used for future comparisons.
*/

import java.util.ArrayList;

public class CompressionUtility {

    // This function essentially evaluates a set of data for deletion. Must be sorted by timestamp.
    public static void compressData(ArrayList<DataItem> uncompressedData, double deadband) {

        Results results = new Results();

        if (!(uncompressedData.size() <= 2)) {

            // First value is never evaluated for deletion. Kept in data set for trending purposes.
            results.compressedData.add(uncompressedData.get(0));

            double currentUpperSlope = ((uncompressedData.get(1).getValue() + deadband) - uncompressedData.get(0).getValue()) / (uncompressedData.get(1).getTimestamp() - uncompressedData.get(0).getTimestamp());
            double currentLowerSlope = ((uncompressedData.get(1).getValue() - deadband) - uncompressedData.get(0).getValue()) / (uncompressedData.get(1).getTimestamp() - uncompressedData.get(0).getTimestamp());
            DataItem current = uncompressedData.get(1);

            // Do not evaluate the first and last data for deletion.
            for (int i = 1; i < uncompressedData.size() - 1; i++) {

                DataItem snapshot = uncompressedData.get(i + 1);
                double snapshotUpperSlope = ((uncompressedData.get(i + 1).getValue() + deadband) - uncompressedData.get(i).getValue()) / (uncompressedData.get(i + 1).getTimestamp() - uncompressedData.get(i).getTimestamp());
                double snapshotLowerSlope = ((uncompressedData.get(i + 1).getValue() - deadband) - uncompressedData.get(i).getValue()) / (uncompressedData.get(i + 1).getTimestamp() - uncompressedData.get(i).getTimestamp());
                // If the newly calculated upper slope (snapshotUpperSlope) is lower than the previously calculated lower slope value (currentLowerSlope),
                // the system will store the new value.
                boolean conditionSnapshotUpperCompareToCurrentLower = (snapshotUpperSlope < currentLowerSlope);
                // If the newly calculated lower slope (snapshotLowerSlope) is larger than the previously calculated upper slope value (currentUpperSlope),
                // the system will store the new value.
                boolean conditionSnapshotLowerCompareToCurrentUpper = (snapshotLowerSlope > currentUpperSlope);
                int snapshotQuality = snapshot.getQuality();
                int currentQuality = current.getQuality();
                // The system always stores a value when the quality on the tag changes.
                boolean didQualityChange = (snapshotQuality != currentQuality);

                if (didQualityChange) {
                    results.compressedData.add(current);
                } else {

                    if (conditionSnapshotUpperCompareToCurrentLower || conditionSnapshotLowerCompareToCurrentUpper) {
                        results.compressedData.add(current);
                        currentLowerSlope = snapshotLowerSlope;
                        currentUpperSlope = snapshotUpperSlope;
                        current = snapshot;
                    } else {
                        results.deletedData.add(current);
                        // In cases where a new value isn't stored, the system will compare the newly calculated slope values to the previously calculated values:
                        // If the new upper slope is less than the previous upper slope, then the new upper slope is used for future comparisons.
                        // If the new lower slope is greater than the previous lower slope, then the new lower slope is used for future comparisons.
                        if (snapshotUpperSlope < currentUpperSlope) {
                            currentUpperSlope = snapshotUpperSlope;
                        }
                        if (snapshotLowerSlope > currentLowerSlope) {
                            currentLowerSlope = snapshotLowerSlope;
                        }
                    }

                }

            }
        }

        // Last value is never evaluated for deletion. Kept in data set for trending purposes.
        results.compressedData.add(uncompressedData.get(uncompressedData.size() - 1));

        System.out.println(results);

//        deleteData(results.deletedData);

    }

    public static void deleteData(ArrayList<DataItem> deletedData) {

        // Function to iterate through a list of rows to be deleted from the database.
        // Called if evaluateForDeletion returns true.

    }

}