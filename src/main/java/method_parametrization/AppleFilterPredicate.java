package method_parametrization;

import method_parametrization.predicate.AppleGreenColorPredicate;
import method_parametrization.predicate.AppleHeavyWeightPredicate;
import method_parametrization.predicate.ApplePredicate;
import method_parametrization.predicate.AppleReedAndHeavyPredicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz on 17.06.17.
 */
public class AppleFilterPredicate extends Apples {

    public static void main(String[] args) {
        List<Fruit> inventory = orchard.gatherApples();
        List<Fruit> applesGrean = filterApples(inventory, new AppleGreenColorPredicate());

        for (Fruit fruit : applesGrean) {
            System.out.println(fruit);
        }

        System.out.println("\n");

        List<Fruit> applesOver150 = filterApples(inventory, new AppleHeavyWeightPredicate());

        for (Fruit fruit : applesOver150) {
            System.out.println(fruit);
        }

        System.out.println("\n");

        List<Fruit> applesReedAndHeavy = filterApples(inventory, new AppleReedAndHeavyPredicate());

        for (Fruit fruit : applesReedAndHeavy) {
            System.out.println(fruit);
        }

    }

    private static List<Fruit> filterApples(List<Fruit> inventory, ApplePredicate predicate) {
        List<Fruit> result = new ArrayList<>();
        for (Fruit fruit : inventory) {
            if (predicate.test(fruit)) {
                result.add(fruit);
            }
        }
        return result;
    }

}
