import java.util.ArrayList;
import java.util.List;

public class Filtering implements Filter {
    private final String[] vips = {"Haron", "Hieu","Nep"};

    public boolean accept(String x) {
        for (String s : vips) {
            if (s.equalsIgnoreCase(x)) return true;
        }
        return false;
    }

    public String[] filter(String[] a, Filter f) {
        List<String> list = new ArrayList<>();
        for (String x : a) {
            if (f.accept(x)) {
                list.add(x);
            }
        }
        return list.toArray(new String[0]);
    }


}
