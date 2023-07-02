package dissan.ahmed.bce.neural;

import dissan.ahmed.bce.DataRow;
import dissan.ahmed.bce.FeatureData;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureMapping {

    private final Map<String, Map<String, Integer>> inputMapping; //Map<Feature, Map<attribute, int value>>

    @Contract(pure = true)
    public FeatureMapping() {
        inputMapping = new HashMap<>();
    }

    public void mapData(@NotNull List<DataRow> dataRowList){
        for (DataRow dr:
                dataRowList) {
            setupMapping(dr);
        }
    }

    /**
     * Mapping the values for FeatureData checking if value is already an integer otherwise it must be mapping
     * @param dr data row that contains our features
     */
    private void setupMapping(@NotNull DataRow dr) {

        for (FeatureData fd:
             dr.getDataColumns()) {
            try{
                fd.setValue(Integer.parseInt(fd.getGlobalValue()));
            }catch (NumberFormatException e){

                final String fn = fd.getFeatureName();
                final String gv = fd.getGlobalValue();

                if (!inputMapping.containsKey(fn)){
                    this.insertNewVal(fn, gv);
                }else {
                    this.handleExistingMapping(fn, gv);
                }
                fd.setValue(this.inputMapping.get(fn).get(gv));
            }
        }
    }

    public Map<String, Map<String, Integer>> getInputMapping() {
        return inputMapping;
    }

    private void insertNewVal(String key, String value) {
        Map<String, Integer> integerMap = new HashMap<>();
        integerMap.put(value, 1);
        this.inputMapping.put(key, integerMap);
    }

    private void handleExistingMapping(String key, String value) {
        Map<String, Integer> integerMap = this.inputMapping.get(key);
        integerMap.putIfAbsent(value, integerMap.size() + 1);
    }


}
