package dissan.ahmed.bce.models.neural;


import dissan.ahmed.bce.DataRow;
import dissan.ahmed.bce.FeatureData;
import dissan.ahmed.bce.models.AiModel;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class NeuralNetwork{
    private double bias;
    private final NeuralNode[] inputNode;
    private final NeuralNode[] outerNode;
    private NeuralNode[][] hiddenNodes;
    private int hiddenLayers = 0;

    /**
     * Constructor of NeuralNetwork
     * Example new NeuralNetwork(seed, 14,{3, 4, 5}, 4 will generate a network that has 14 input neuron,
     * 3 hidden layer the first will have 3 neuron the second 4, and the last 5 neuron, and 4 output neuron
     * The seed will be random by using pc clock value
     * @param numOfInputNodes how many input nodes want to pass
     * @param numOfHiddenNodes the length of the array indicates how many hidden layer and the x[i]
     *                         indicates how many neuron per layer
     * @param numOfOutputNodes how many output nodes want to pass
     */

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
        this.inputNode = new NeuralNode[numOfInputNodes];
        this.outerNode = new NeuralNode[numOfOutputNodes];
        Random random = new Random();
        random.setSeed(seed);
        //Wo
        this.setBias(random.nextGaussian());
        int i;


        for (i = 0; i < numOfInputNodes; i++){
            this.inputNode[i] = new InputNode(random);
        }

        for (i = 0; i < numOfOutputNodes; i++) {
            this.outerNode[i] = new OutputNode(random);
        }
        if (numOfHiddenNodes != null) { // if null there will be not generated any node
            this.hiddenLayers = numOfHiddenNodes.length;
            this.hiddenNodes = new NeuralNode[numOfHiddenNodes.length][];
            for (i = 0; i < this.hiddenLayers; i++) {
                int numOfNodes = numOfHiddenNodes[i];
                hiddenNodes[i] = new NeuralNode[numOfNodes];
                for (int j = 0; j < numOfNodes; j++) {
                    hiddenNodes[i][j] = new HiddenNode(i, random);
                }
            }
        }

        setupArc();
        setupPrevious();
    }

    private void setupArc() {

        NeuralNode[] nextNodes = outerNode;
        int i;
        if (hiddenNodes != null) {
            nextNodes = hiddenNodes[0];
        }

        setNextNodes(inputNode, nextNodes);

        for (i = 0; i < hiddenNodes.length - 1; i++) {
            setNextNodes(hiddenNodes[i], hiddenNodes[i+1]);
        }
        setNextNodes(hiddenNodes[i], outerNode);
        int j = 0;
    }

    private void setupPrevious() {
        NeuralNode[] previousNodes = inputNode;
        int i;
        if (hiddenNodes != null){
            previousNodes = hiddenNodes[hiddenNodes.length - 1];

            for (i = hiddenNodes.length - 1; i > 0 ; i--) {
                for (NeuralNode hn:
                     hiddenNodes[i]) {
                    hn.setInputNodes(hiddenNodes[i - 1]);
                }
            }

            for (NeuralNode hn:
                 hiddenNodes[i]) {
                hn.setInputNodes(inputNode);
            }

        }

        for (NeuralNode on:
                this.outerNode) {
            on.setInputNodes(previousNodes);
        }
    }

    private void setNextNodes(NeuralNode @NotNull [] prevNodes, NeuralNode[] nextNodes) {
        for (NeuralNode node:
            prevNodes) {
            node.setupNextNodes(nextNodes);
        }
    }


    //todo setup propagation flow...
    public void setAttributes(double @NotNull [] x) throws ArrayIndexOutOfBoundsException{
        if (x.length != inputNode.length){
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    public NeuralNode[] getInputNode() {
        return inputNode;
    }

    public NeuralNode[] getOuterNode() {
        return outerNode;
    }

    public NeuralNode[][] getHiddenNodes() {
        return hiddenNodes;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }




    protected void minimize() {

    }


    public void train(DataRow dr) {
        List<FeatureData>featureData = dr.getDataColumns();


        for (int i = 0; i <featureData.size() - 1 ; i++) {
            inputNode[i].setExit(featureData.get(i).getValue());
        }

        for (int i = 0; i < hiddenNodes.length; i++) {
            for (int j = 0; j < hiddenNodes[i].length; j++) {
                hiddenNodes[i][j].setExit(hiddenNodes[i][j].getExit());
            }
        }

        int i = 0;

        for (NeuralNode on:
             this.outerNode) {
            on.setExit(on.getExit());
        }

    }
    /**
     * this function is the loss function l: R^p x R^p -> [0, inf) difference measure between y~ and y
     * l(y~,y) = || y~ - y || ^2

     * @return cross-Entropy function
     */


    public double lossFunction(double[] yExpected, double yActual){
        return 0;
    }

    public double activationFunction(double x){
        if (x <= 0){
            return 0;
        }
        return x;
    }

    public double dActivationFunction(double x){
        double xPow = Math.pow(activationFunction(x), 2);
        double dXPow = x > 0 ? 2*x : 0;
        return Math.sqrt(dXPow);
    }

    public double lossFunction(){
        return 0;
    }







}
