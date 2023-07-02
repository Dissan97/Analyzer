package dissan.ahmed;


import dissan.ahmed.api.Analyzer;
import dissan.ahmed.api.DataBean;
import dissan.ahmed.bce.DataDao;
import dissan.ahmed.bce.DataRow;
import dissan.ahmed.bce.lr.LogisticRegressionController;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Creator -> LogisticRegressionController
 */

public class ControllerFacade {

    public static final Logger LOGGER = Logger.getLogger(ControllerFacade.class.getSimpleName());


    private Analyzer analyzer;
    private List<DataBean> dataBeans;
    private DataDao dataDao;
    public ControllerFacade(@NotNull MLModel model) {
        final String fooMessage = model.name() + "not implemented yey";
            switch (model){
                case NEURAL_NETWORK, LEAST_SQUARE_REGRESSION ->{
                    LOGGER.info(fooMessage);
                }
                case LOGISTIC_REGRESSION -> {
                    this.analyzer = new LogisticRegressionController();
                }
            }
    }

    public void chooseDataSet(String dataset) throws IOException {
        dataDao = new DataDao();
        this.dataDao.setDataset(dataset);
        List<DataRow> dataRows = dataDao.getAllData();

        if(this.analyzer instanceof LogisticRegressionController){
            ((LogisticRegressionController) this.analyzer).setDataRows(dataRows);
        }
        this.dataBeans = analyzer.getDataBean();
    }

    public void analyzeData(){
        this.analyzer.analyze();
        this.dataBeans = analyzer.getDataBean();
        preparePresentation();
    }

    private void preparePresentation() {
    }


}