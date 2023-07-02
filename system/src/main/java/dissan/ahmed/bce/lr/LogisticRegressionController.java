package dissan.ahmed.bce.lr;

import dissan.ahmed.api.Analyzer;
import dissan.ahmed.api.DataBean;
import dissan.ahmed.bce.DataRow;
import java.util.List;

public class LogisticRegressionController implements Analyzer {

    private double probability;
    private List<DataRow> dataRows;
    private List<Double> binaryLabel;

    @Override
    public void analyze() {

    }

    @Override
    public List<DataBean> getDataBean() {
        return null;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public List<DataRow> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<DataRow> dataRows) {
        this.dataRows = dataRows;

        double m = 0;
        double pCounter = 0;

        for (DataRow row:
             dataRows) {

            double rowVal = row.getLabelValue();

            m++;
        }

        assert m != 0.0;
        this.probability = pCounter / m;

    }
}
