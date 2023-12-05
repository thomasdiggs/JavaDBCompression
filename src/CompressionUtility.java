/*
Summary of modified "Sliding Window" algorithm:

The following algorithm essentially evaluates a set of data for deletion. Must be sorted by timestamp.
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

    public Results compressData(ArrayList<DataItem> uncompressedData, double deadband) {

        Results results = new Results();

        if (!(uncompressedData.size() <= 2)) {

            results.compressedData.add(uncompressedData.get(0));
            DataItem lastKeptItem = uncompressedData.get(0);

            for (int i = 1; i < uncompressedData.size() - 1; i++) {

                DataItem current = uncompressedData.get(i);
                DataItem snapshot = uncompressedData.get(i + 1);
                double snapshotUpperSlope = ((snapshot.getValue() + deadband) - current.getValue()) / (snapshot.getEpochTime() - current.getEpochTime());
                double snapshotLowerSlope = ((snapshot.getValue() - deadband) - current.getValue()) / (snapshot.getEpochTime() - current.getEpochTime());
                double currentUpperSlope = ((current.getValue() + deadband) - lastKeptItem.getValue()) / (current.getEpochTime() - lastKeptItem.getEpochTime());
                double currentLowerSlope = ((current.getValue() - deadband) - lastKeptItem.getValue()) / (current.getEpochTime() - lastKeptItem.getEpochTime());

                if (snapshot.getQuality() != current.getQuality()) {

                        results.compressedData.add(current);

                } else {

                    if ((snapshotUpperSlope < currentLowerSlope) || (snapshotLowerSlope > currentUpperSlope)) {

                        results.compressedData.add(current);
                        currentLowerSlope = snapshotLowerSlope;
                        currentUpperSlope = snapshotUpperSlope;
                        lastKeptItem = current;

                    } else {

                        results.deletedData.add(current);

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

        results.compressedData.add(uncompressedData.get(uncompressedData.size() - 1));

//        deleteData(results.deletedData);

        return results;
    }

    public static void deleteData(ArrayList<DataItem> deletedData) {

        // Function to iterate through a list of rows to be deleted from the database.
        // Called if evaluateForDeletion returns true.

    }

}