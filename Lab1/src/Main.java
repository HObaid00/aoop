import java.util.*;

public class Main implements Filter{

    @Override
    public boolean accept(String x) {
        return x.length() <= 3;
    }

    public static void main(String[] args) {
      /* Matrix a = new Matrix(3,3);
       Matrix b = new Matrix(3,3);

        a.createRandomMatrix(6);
        b.createRandomMatrix(7);

        System.out.println("this is matrix a:"+a);
        System.out.println("this is matrix b:"+ b);

        System.out.println("The sum:"+a.addMatrix(b));
        System.out.println("The product:"+a.multiplyMatrix(b));*/

       List<String> listInput = new ArrayList<>();
        System.out.println("Enter the names of the people who are coming, type finish after typing the last person");
        Scanner input = new Scanner(System.in);

        while(input.hasNext()){
            String name = input.nextLine();
            if(name.equalsIgnoreCase("finish"))
                break;
            listInput.add(name);
        }

        String [] guests = new String[listInput.size()];
        guests = listInput.toArray(guests);

        String [] invitedGuests = StringFilter.filter(guests,new Main());
        System.out.println(Arrays.toString(invitedGuests));

    }



}
