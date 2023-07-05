package dissan.ahmed.bce.models.neural;

import dissan.ahmed.bce.Operations;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public abstract class NeuralNode {

    protected Random random;
    private double exit;
    protected double bias;
    private Map<NeuralNode, Double> doubleNextMap;
    private NeuralNode[] inputNodes;

    public NeuralNode(@NotNull Random rnd) {
        this.random = rnd;
        this.bias = random.nextGaussian();
    }

    protected double rectifiedLinearUnit(double x){
        return x > 0 ? x : 0;
    }

    public double getExit(){
        return exit;
    }

    public void setExit(double exit) {
        this.exit = exit;
    }

    public void operation(NeuralNode[] input, Operations operation, double wo){}

    public double getWeight(@NotNull NeuralNode node){
        if (this.doubleNextMap != null) {
            return this.doubleNextMap.get(node);
        }
        return Double.NaN;
    }

    public NeuralNode[] getInputNodes() {
        return inputNodes;
    }

    public void setInputNodes(NeuralNode[] inputNodes) {
        this.inputNodes = inputNodes;
    }

    protected double alj() {
        double zl = 0;

        for (NeuralNode in:
             this.inputNodes) {
            zl += in.getWeight(this)*in.getExit();
        }

        /*
        for (Map.Entry<NeuralNode, Double> entry:
                doubleNextMap.entrySet()) {
            zl += (entry.getValue() * entry.getKey().getExit());
        }
         */
        return zl - bias;
    }

    public double dAlj(int index){
        if (index == 0){
            return -1;
        }
        //z{l -1}
        return inputNodes[index].getExit();
    }

    public void setupNextNodes(NeuralNode @NotNull [] nodes) {
        this.doubleNextMap = new LinkedHashMap<>();
        for (NeuralNode node:
                nodes) {
            doubleNextMap.put(node, random.nextGaussian());
        }
    }
}
