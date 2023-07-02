package dissan.ahmed.bce;

import dissan.ahmed.bce.util.ArffUtil;
import dissan.ahmed.bce.util.DataUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.*;


public class DataDao {

    private String dataset;
    private static final String DIR = "datasets";
    private String regex;

    public DataDao() throws IOException {
        File file = new File(DIR);
        if (!file.isDirectory()){
            if (!file.mkdir()){
                throw new IOException("file does not created");
            }
        }
    }


    /**
     * Function used to retrieve from a dataset all the data if a csv is passed then it converts it to arff and analyze the data
     * @return Collection of DataRow each row contains its column that is FeatureData
     * @throws IOException if file are malformed
     */

    public List<DataRow> getAllData() throws IOException {

        List<DataRow> data = new ArrayList<>();
        checkFileFormat();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(this.dataset)
        )){


            Map<String, DataUtil> map = parseArff(reader);
            String line;
            String[] attributes = map.keySet().toArray(new String[0]);

            while ((line = reader.readLine()) != null){
                String[] values = line.split(this.regex);
                List<FeatureData> featureDataList = new ArrayList<>();
                boolean label = false;
                for (int i = 0; i < attributes.length; i++) {
                    DataUtil dataUtil = map.get(attributes[i]);

                    int value;
                    if (dataUtil == null){
                        value = Integer.parseInt(values[i]);
                    }else {
                        value = dataUtil.getIntegerMap().get(values[i].replace("\"", ""));
                        label = dataUtil.isLabel();
                    }

                    featureDataList.add(new FeatureData(attributes[i], value, values[i], label));
                }
                data.add(new DataRow(featureDataList));
            }
        }

        return data;
    }


    /**
     * This method is used to parse Arff file
     * @param reader the reader from which it reads the data
     * @return Map<String, DataUtil> it is also used to map values it is not correct here
     * @throws IOException if arf file are malformed
     */

    private @NotNull Map<String, DataUtil> parseArff(@NotNull BufferedReader reader) throws IOException {
        Map<String, DataUtil> map = new LinkedHashMap<>();

        String line;
        while ((line = reader.readLine()) != null) {

            if (line.contains("@data")){
                break;
            }

            if (line.contains("@attribute")) {
                int start;
                int end;

                String[] attributeField = line.split(" ");
                final String fieldName = attributeField[1];
                if (line.contains("real")){
                    map.put(fieldName, null);
                    continue;
                }

                String[] fieldValues = ArffUtil.getAttributes(line);
                DataUtil dataUtil = getDataUtil(line);

                int index = 1;

                for (String fieldValue : fieldValues) {
                    int mapNum = index;

                    if (fieldValue.equals("?")) {
                        mapNum = -1;
                        index -= 1;
                    }
                    dataUtil.getIntegerMap().put(fieldValue, mapNum);
                    index++;
                }

                map.put(fieldName, dataUtil);
            }
        }

        return map;
    }

    @Contract("_ -> new")
    private @NotNull DataUtil getDataUtil(@NotNull String line) {
        String[] words = line.split(" ");
        final String cl = "class";

        for (String w:
             words) {
            if (w.equals(cl)){
                    return new DataUtil(true, ArffUtil.getAttributes(line).length == 2);
                }
        }
        return new DataUtil();
    }

    /**
     * This method control if the file passed is already an arff file or not
     * @throws IOException it is thrown by csvToArff method
     */
    private void checkFileFormat() throws IOException {
        String[] filename = this.dataset.split("\\.");
        if (filename[1].equals("csv")){
            csvToArff();
        }
    }


    /**
     * This method convert a csv file in an arff file but with just a label
     * @throws IOException it files are malformed
     */

    private void csvToArff() throws IOException {

        this.dataset  = DIR + '/' + dataset;
        this.regex = ",";
        try (BufferedReader reader = new BufferedReader(
                new FileReader(this.dataset)
        )){

            String line = reader.readLine();
            Map<String, String[]> map = new HashMap<>();
            getAttributes(line, map);
            String[] featureNames = map.get(regex);
            assert regex != null;

            for (int i = 0; i < featureNames.length; i++) {
                featureNames[i] = featureNames[i].toLowerCase(Locale.ROOT);
            }

            List<String[]> values = new ArrayList<>();
            while ((line = reader.readLine()) != null)  {
                values.add(line.split(regex));
            }

            Map<String, List<String>> listMap = mapValues(featureNames, values);
            createArff(featureNames, listMap, values);
        }
    }

    /**
     * This method map the values for the arff format
     * @param featureNames all the features that need to be prepared to @attribute
     * @param values the values that will be mapped in {v1, v2, .. , vn}
     * @return return the relative map
     */
    private @NotNull Map<String, List<String>> mapValues(String @NotNull [] featureNames,@NotNull List<String[]> values) {
        Map<String, List<String>> listMap = new HashMap<>();

        for (int i = 0; i < featureNames.length; i++) {
            for (String[] v : values) {
                List<String> stringList = listMap.get(featureNames[i]);
                final String value = v[i];
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    if (stringList == null) {
                        stringList = new ArrayList<>();
                        stringList.add(value);
                        listMap.put(featureNames[i], stringList);
                    } else {
                        if (!stringList.contains(value)) {
                            stringList.add(value);
                        }
                    }
                }
            }
        }
        return listMap;
    }


    /**
     * This method create the arff file
     * @param featureNames the features of the arff file @attribute featureName {v1, v2, .., vn}
     * @param map the map that will be used to create the attribute structure
     * @param values values for the field @data
     * @throws IOException if some IO error is thrown
     */

    private void createArff(String @NotNull [] featureNames, @NotNull Map<String,@NotNull List<String>> map,@NotNull List<String[]> values) throws IOException {
        this.dataset = this.dataset.split("\\.")[0];
        StringBuilder metadata = new StringBuilder();
        metadata.append("@relation ").append(this.dataset).append("\n\n");

        final String real = " real\n";
        final String openBracket = " {";
        final String closeBracket = "}\n";
        final String comma = ",";
        final String attribute = "@attribute ";
        int i;
        int j;

        for (i = 0; i < featureNames.length; i++) {
            final String feature = featureNames [i];
            List<String> stringList = map.get(feature);
            metadata.append(attribute);
            if (i == featureNames.length - 1){
                metadata.append("class ");
            }
            if (stringList == null){
                metadata.append(feature).append(real);
            }else {
                metadata.append(feature).append(openBracket);
                for (j = 0; j < stringList.size() - 1; j++) {
                    metadata.append(stringList.get(j)).append(comma);
                }
                metadata.append(stringList.get(j)).append(closeBracket);
            }
        }

        metadata.append("\n@data\n");
        this.dataset = this.dataset + ".arff";
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(this.dataset)
        )) {
            writer.write(metadata.toString());
            int k;
            for (String[] ls :
                    values) {
                StringBuilder writerBuffer = new StringBuilder();

                for (k = 0; k < ls.length - 1; k++) {
                    try {
                        writerBuffer.append(Integer.parseInt(ls[k]));
                    } catch (NumberFormatException e) {
                        writerBuffer.append('\"').append(ls[k]).append('\"');
                    }
                    writerBuffer.append(comma);
                }
                try {
                    writerBuffer.append(Integer.parseInt(ls[k]));
                } catch (NumberFormatException e) {
                    writerBuffer.append('\"').append(ls[k]).append('\"');
              }
                writerBuffer.append('\n');
                writer.write(writerBuffer.toString());
            }
        }
    }


    /**
     * This method check if regex for split is correct
     * @param line the line analyzed
     * @param map the reference to the map that will be used
     * @return regex value
     */

    private @Nullable String getAttributes(@NotNull String line, Map<String, String[]> map) {
        String[] attributes = line.split(regex);
            if (attributes.length > 1) {
                map.put(regex, attributes);
                return regex;
            }
        switch (regex){
            case ","->
                regex = ";";
            case ";" ->
                regex = ":";
            case ":" ->
                regex = "badFile";
            default -> {
                return null;
            }
        }
            return getAttributes(line, map);
    }


    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public static void main(String[] args) throws IOException {

        DataDao dataDao = new DataDao();
        dataDao.setDataset("adult.csv");
        List<DataRow> dataRowList = dataDao.getAllData();

        for (DataRow dr:
             dataRowList) {
            for (FeatureData fd:
                 dr.getDataColumns()) {
                if (fd.isLabel())
                    System.out.println(fd);
            }
        }


    }
}
