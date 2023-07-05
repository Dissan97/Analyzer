package dissan.ahmed.bce;

import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;

import java.util.ArrayList;
import java.util.Arrays;
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

    public double[] getLabelsVal() {
        List<Double> doubles = new ArrayList<>();
        for (FeatureData fd:
             dataColumns) {
            if (fd.isLabel()){
                doubles.add((double) fd.getValue());
            }
        }
        return doubles.stream().mapToDouble(Double::doubleValue).toArray();
    }


}
