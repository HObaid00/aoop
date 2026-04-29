package eight;

import java.util.*;

public class Main extends Filter {
    public static void main(String[] args) {
        System.out.println("Enter the name of the people who's coming ");
        System.out.println(Arrays.toString(filteredArray()));
    }
    public static String[] filteredArray() {
        List<String> guests = new ArrayList<String>();
        System.out.println("Type finish when done.");
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            String name = input.nextLine();
            if (name.equalsIgnoreCase("finish")) {
                break;
            }

            guests.add(name);
        }
        input.close();
        String[] list = new String[guests.size()];
        list = guests.toArray(list);
        return filter(list, new Main());
    }

    @Override
    public boolean accept(String x) {
        return x.length() <= 3;
    }
}
