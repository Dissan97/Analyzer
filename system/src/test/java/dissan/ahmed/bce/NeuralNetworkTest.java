package dissan.ahmed.bce;

import dissan.ahmed.bce.neural.NeuralNetwork;
import dissan.ahmed.bce.neural.NeuralNode;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class NeuralNetworkTest {


    @Test
    public void testConstructor(){

        int seed = 1234;
        NeuralNetwork neuralNetwork;
        NeuralNode[] input;
        NeuralNode[][] hidden;
        NeuralNode[] output;
        boolean assertion;

        Random random = new Random();
        random.setSeed(seed);
        double bias = random.nextGaussian();

        int iNodes = 2;
        int[] hNodes = {2, 3};
        int oNodes = 1;
        neuralNetwork = new NeuralNetwork(seed, iNodes, hNodes, oNodes);
        input = neuralNetwork.getInnerNode();
        hidden = neuralNetwork.getHiddenNode();
        output = neuralNetwork.getOuterNode();

        boolean hiddenControl = true;

        for (int i = 0; i < hNodes.length; i++) {
            hiddenControl &= hidden[i].length == hNodes[i];
        }



        assertion = input.length == iNodes
                && hidden.length == hNodes.length
                && hiddenControl
                && output.length == oNodes
                && neuralNetwork.getBias() == bias;

        assertTrue(assertion);
    }
}
