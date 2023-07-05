package dissan.ahmed;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String foo = "ao";
        Map<String, String> map = new HashMap<>();
        ciao(map);
        System.out.println(map);
    }

    private static void ciao(Map<String, String> map){
        map.put("hello", "giao");
        System.out.println("Hello world");
    }
}
