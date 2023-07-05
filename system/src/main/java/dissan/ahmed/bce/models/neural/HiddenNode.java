package dissan.ahmed.bce.models.neural;

import java.util.Random;

public class HiddenNode extends NeuralNode{
    private int layer;

    public HiddenNode(int layer, Random random) {
        super(random);
        this.layer = layer;
    }



    /**
     * this function get the z[l, j] = g(a[l, j]) where the g is the ReLu activation function
     * g(x) = max{0, x}
     * a[l, j] = sum of previous weight and exit - its bias
     * @return double
     */



    @Override
    public double getExit() {
        return rectifiedLinearUnit(alj());
    }


}
