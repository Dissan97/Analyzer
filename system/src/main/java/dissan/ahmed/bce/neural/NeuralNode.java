package dissan.ahmed.bce.neural;

import dissan.ahmed.bce.Operations;

import java.util.Random;

public abstract class NeuralNode {

    protected double value;
    protected double weight;

    public NeuralNode (){
        this(System.nanoTime());
    }

    public NeuralNode(long seed) {
        Random random = new Random();
        random.setSeed(seed);
        this.weight = random.nextGaussian();
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void operation(NeuralNode[] input, Operations operation, double wo){}
}
