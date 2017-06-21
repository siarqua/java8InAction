package method_parametrization;

import java.util.Comparator;
import java.util.List;

/**
 * Created by lukasz on 17.06.17.
 */
public class AppleComparator extends Apples{
    static List<Fruit> inventory = orchard.gatherApples();

    public static void main(String[] args) {
        for (Fruit fruit : inventory) {
            System.out.println(fruit);
        }

        inventory.sort((Fruit a1, Fruit a2) -> a1.getStringWeight().compareTo(a2.getStringWeight()));

        for (Fruit fruit : inventory) {
            System.out.println("sorted lambda:  " + fruit);
        }

        inventory.sort(new Comparator<Fruit>() {
           @Override
           public int compare(Fruit o1, Fruit o2) {
               return String.valueOf(o1.getIntWeight()).compareTo(String.valueOf(o2.getIntWeight()));
           }
       });

        for (Fruit fruit : inventory) {
            System.out.println("sorted:  " + fruit);
        }


    }
}
