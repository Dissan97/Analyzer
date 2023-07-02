package dissan.ahmed.bce.neural;

import dissan.ahmed.bce.Operations;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;

public class HiddenNode extends NeuralNode{
    private int layer;

    public HiddenNode(int layer, long seed) {
        super(seed);
        this.layer = layer;
    }

    @Override
    public void operation(NeuralNode[] input, Operations operation, double wo) {
        RealVector values;
        RealVector weights;
        int length = input.length;
        double[] val = new double[length];
        double[] wi = new double[length];

        for (int i = 0; i < length; i++) {
            val[i] = input[i].getValue();
            wi[i] = input[i].getWeight();
        }

        values = new ArrayRealVector(val);
        weights = new ArrayRealVector(wi);

        double wPerX =weights.dotProduct(values) + wo;
        System.out.println(wPerX);
    }

}
