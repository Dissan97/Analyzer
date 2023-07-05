package dissan.ahmed.bce.models;

import dissan.ahmed.bce.DataRow;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public abstract class AiModel {


    protected abstract double lossFunction(double[] yBar,double[] yReal);

    /**
     * //1/m * sum[1,m]{Ei(w)} calculating average error committed
     * goal have a low empirical risk but not 0 to avoid generalization problem on new data
     * @param samples is a map were each row is associated with his relative label
     * @return R(w) double the empirical Risk
     */
    protected double empiricalRisk(@NotNull Map<double[], DataRow> samples){
        double m = samples.size();
        double E = 0.0;

        for (Map.Entry<double[], DataRow> entry:
                samples.entrySet()) {
            //foreach entry of the data set the labels are selected and are assigned the relative predicted value
            E += lossFunction(entry.getKey(), entry.getValue().getLabelsVal());
        }
        return E / m;
    }

    /**
     * Is the problem that we want to solve min 1/m sum[i=1..m]{Ei(m)} + lamda*||omega||^2
     * so each class must implement his own logic to make it happen
     */
    protected abstract void minimize();

    /**
     * This method can be called to train the data set
     */
    public abstract void train(DataRow dr);

}
