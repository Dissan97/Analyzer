package dissan.ahmed.bce.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataUtil {
    private final Map<String, Integer> integerMap;
    private boolean binary;
    private boolean label = false;

    public DataUtil() {
        this.integerMap = new LinkedHashMap<>();
    }

    public DataUtil(boolean label, boolean b) {
        this();
        this.label = label;
        this.binary = b;
    }

    public Map<String, Integer> getIntegerMap() {
        return integerMap;
    }

    public boolean isLabel() {
        return label;
    }
}
