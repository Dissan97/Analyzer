package dissan.ahmed.bce.neural;

import dissan.ahmed.bce.BadMapException;
import dissan.ahmed.bce.DataBean;
import dissan.ahmed.bce.DataDao;
import dissan.ahmed.bce.DataRow;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

public class NeuralNetworkController implements dissan.ahmed.api.Analyzer {

    private final DataDao dataDao;
    private List<DataRow> dataList;
    private final List<DataBean> beanList;
    private NeuralNetwork neuralNetwork;

    public NeuralNetworkController(String dataset) throws IOException {
        this.dataDao = new DataDao();
        this.dataDao.setDataset(dataset);
        this.beanList = new ArrayList<>();
        setupData();
    }

    private void setupData() throws IOException {
        this.dataList = this.dataDao.getAllData();
        for (DataRow dta:
             this.dataList) {
            beanList.add(new DataBean(dta));
        }
    }


    //todo adjust this give it a sense
    @Override
    public void analyze() {


    }

    public void exitCalculator() throws BadMapException {
        double[][] z = new double[this.dataList.size()][];
    }

    /**
     * lossFunction :  R^p x R^p -> [0, infinite]
     * @param actualY the value actual value
     * @param realY the expected value
     * @return l(actualY, realY) = || actualY - realY || ^2
     */
    public double lossFunction(RealMatrix actualY, RealMatrix realY){
        return 0;
    }

    /**
     *
     * @return double error committed
     */
    public double errorCommitted(){
        return 0;
    }

    /**
     * prediction function that will be used
     * @param x feature € |R^n
     * @return actualY € |R
     */
    public double hX(@NotNull RealMatrix x, int index){
        RealMatrix xi = new Array2DRowRealMatrix();

        Random random = new Random();
        int i = random.nextInt(2);
        return i*2 - 1;

    }

    @Override
    public List<dissan.ahmed.api.DataBean> getDataBean() {
        return new ArrayList<>(this.beanList);
    }

    public static void main(String[] args) throws IOException {
    }
}
