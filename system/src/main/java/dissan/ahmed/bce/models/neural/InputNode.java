package dissan.ahmed.bce.models.neural;


import java.util.Random;

public class InputNode extends NeuralNode{
    //each next layer will have an arc to the node with a weight
    public InputNode(Random random) {
        super(random);
    }
}
