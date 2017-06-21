package method_parametrization;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz on 17.06.17.
 */
public class AppleFilterBad {
    public static void main(String[] args) {
        Orchard orchard = new Orchard();
        List<Fruit> inventory = orchard.gatherApples();

        List<Fruit> applesGrean = filterGreenApples(inventory);

        for (Fruit fruit : applesGrean) {
            System.out.println(fruit);
        }

        System.out.println("\n");

        List<Fruit> applesReed = filterGreenApples(inventory,"reed");

        for (Fruit fruit : applesReed) {
            System.out.println(fruit);
        }

        System.out.println("\n");

        List<Fruit> applesOver150 = filterApplesByWeight(inventory,150);

        for (Fruit fruit : applesOver150) {
            System.out.println(fruit);
        }

        System.out.println("\n");

        List<Fruit> fruits = filterApples(inventory,"reed",140,true);

        for (Fruit fruit : fruits) {
            System.out.println(fruit);
        }

    }

    public static List<Fruit> filterGreenApples(List<Fruit> inventory) {
        List<Fruit> result = new ArrayList<>();
        for (Fruit fruit : inventory) {
            if ("green".equals(fruit.getColor())) {
                result.add(fruit);
            }
        }
        return result;
    }

    public static List<Fruit> filterGreenApples(List<Fruit> inventory, String color) {
        List<Fruit> result = new ArrayList<>();
        for (Fruit fruit : inventory) {
            if (fruit.getColor().equals(color)) {
                result.add(fruit);
            }
        }
        return result;
    }

    public static List<Fruit> filterApplesByWeight(List<Fruit> inventory, int weight) {
        List<Fruit> result = new ArrayList<>();
        for (Fruit fruit : inventory) {
            if (fruit.getIntWeight() > weight) {
                result.add(fruit);
            }
        }
        return result;
    }

    public static List<Fruit> filterApples(List<Fruit> inventory, String color, int weight, boolean flag) {
        List<Fruit> result = new ArrayList<>();
        for (Fruit fruit : inventory) {
            if ((flag && fruit.getColor().equals(color)) || (!flag && fruit.getIntWeight() > weight)) {
                result.add(fruit);
            }
        }
        return result;
    }
}
