package eight;

import java.util.*;

public abstract class Filter {

    public abstract boolean accept(String x);

    public static String[] filter(String[] a, Filter f) {
        List<String> list = new ArrayList<>();
        for (String x : a) {
            if (f.accept(x)) {
                list.add(x);
            }
        }
        return list.toArray(new String[0]);
    }

}
