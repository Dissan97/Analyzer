package dissan.ahmed.bce.util;

public class ArffUtil {

    public static String[] getAttributes(String line){
        int start;
        int end;
        for (start = 0; start < line.length(); start++) {
            if (line.charAt(start) == '{'){
                break;
            }
        }
        start ++;

        for (end = start; end < line.length() ; end++) {
            if (line.charAt(end) == '}'){
                break;
            }
        }
        return line.substring(start, end).split(",");
    }
}
