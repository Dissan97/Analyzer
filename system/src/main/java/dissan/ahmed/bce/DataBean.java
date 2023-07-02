package dissan.ahmed.bce;

public class DataBean implements dissan.ahmed.api.DataBean {
    private final DataRow data;
    public DataBean(DataRow dataToAnalyze) {
        this.data = dataToAnalyze;
    }

    /**
     * Function used to get all the feature of the data
     * @return entire line of the entity from his map
     */
    @Override
    public String getLine(){
        return null;
    }

    @Override
    public String getValue(String key){
        return null;
    }

}
