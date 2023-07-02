package dissan.ahmed.client;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        //Analyzer analyzer = ControllerCreator.getAnalyzer();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Hello", "world");
        System.out.println("Hello World: Analyzer created[] " + jsonObject.toString());
    }
}