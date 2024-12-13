package gui;

public class StringManager {

    public static String getNumString(int n) {
        String str = Integer.toString(n);
        if (str.length() < 2) {
            str = "0" + str;
        }
        return str;
    }
}
