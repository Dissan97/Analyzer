package dissan.ahmed.bce;

public class FeatureData {

    private final String featureName;
    private final boolean label;
    private String globalValue;
    private int value;

    public FeatureData(String featureName, int val, String gValue, boolean lbl) {
        this.featureName = featureName;
        this.value = val;
        this.globalValue = gValue;
        this.label = lbl;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getGlobalValue() {
        return globalValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String str = "";

        if (!String.valueOf(this.value).equals(this.globalValue)){
            str = " mapped value: " + this.value;
        }

        return "feature name: " + this.featureName + " -> value: " + this.globalValue + str;
    }

    public boolean isLabel() {
        return this.label;
    }
}
