package dissan.ahmed.bce;

import java.util.List;

public class DataRow {

    protected final List<FeatureData> dataColumns;

    protected DataRow(List<FeatureData> dc) {
        this.dataColumns = dc;

    }
    public List<FeatureData> getDataColumns(){
        return this.dataColumns;
    }

    public void getLabel() {

    }

    public int getLabelValue() {
        for (FeatureData fd:
             dataColumns) {
            if (fd.isLabel()){
                return fd.getValue();
            }
        }
        return 0;
    }
}
