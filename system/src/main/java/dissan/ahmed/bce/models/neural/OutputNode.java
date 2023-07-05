package dissan.ahmed.bce.models.neural;

import java.util.Random;

public class OutputNode extends NeuralNode {
    public OutputNode(Random random) {
        super(random);
    }
    @Override
    public double getExit() {
       return rectifiedLinearUnit(alj());
    }

}
