package dissan.ahmed.bce.neural;


import org.jetbrains.annotations.NotNull;

import java.util.*;

public class NeuralNetwork {
    private double bias;
    private final NeuralNode[] innerNode;
    private final NeuralNode[] outerNode;
    private NeuralNode[][] hiddenNode;

    public NeuralNetwork(int numOfInputNodes, int[] numOfHiddenNodes, int numOfOutputNodes){
        this(System.nanoTime(), numOfInputNodes, numOfHiddenNodes, numOfOutputNodes);
    }

    /**
     * Constructor of NeuralNetwork
     * Example new NeuralNetwork(seed, 14,{3, 4, 5}, 4 will generate a network that has 14 input neuron,
     * 3 hidden layer the first will have 3 neuron the second 4, and the last 5 neuron, and 4 output neuron
     * @param seed for weights randomly generated
     * @param numOfInputNodes how many input nodes want to pass
     * @param numOfHiddenNodes the length of the array indicates how many hidden layer and the x[i]
     *                         indicates how many neuron per layer
     * @param numOfOutputNodes how many output nodes want to pass
     */


    public NeuralNetwork(long seed, int numOfInputNodes, int[] numOfHiddenNodes, int numOfOutputNodes) {
        this.innerNode = new NeuralNode[numOfInputNodes];
        this.outerNode = new NeuralNode[numOfOutputNodes];
        Random random = new Random();
        random.setSeed(seed);
        this.setBias(random.nextGaussian());
        int i;
        for (i = 0; i < numOfInputNodes; i++){
            this.innerNode[i] = new InputNode(seed);
        }

        for (i = 0; i < numOfOutputNodes; i++) {
            this.outerNode[i] = new OutputNode(seed);
        }

        if (numOfHiddenNodes != null) { // if null there will be not generated any node
            this.hiddenNode = new NeuralNode[numOfHiddenNodes.length][];
            for (i = 0; i < numOfHiddenNodes.length; i++) {
                int numOfNodes = numOfHiddenNodes[i];
                hiddenNode[i] = new NeuralNode[numOfNodes];
                for (int j = 0; j < numOfNodes; j++) {
                    hiddenNode[i][j] = new HiddenNode(i, seed);
                }
            }
        }
    }

    //todo setup propagation flow...
    public void setAttributes(double @NotNull [] x) throws ArrayIndexOutOfBoundsException{
        if (x.length != innerNode.length){
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    public NeuralNode[] getInnerNode() {
        return innerNode;
    }

    public NeuralNode[] getOuterNode() {
        return outerNode;
    }

    public NeuralNode[][] getHiddenNode() {
        return hiddenNode;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
