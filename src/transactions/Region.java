package transactions;

import java.util.Arrays;

public class Region {
    private String regionName;
    private String[] placeName;

    public Region(String regionName, String[] placeName) {
        this.regionName = regionName;
        this.placeName = placeName;
    }

    public Region(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public String[] getPlaceName() {
        return placeName;
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionName='" + regionName + '\'' +
                ", placeName=" + Arrays.toString(placeName) +
                '}';
    }
}
