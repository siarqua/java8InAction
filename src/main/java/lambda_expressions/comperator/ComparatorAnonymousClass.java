package lambda_expressions.comperator;

import method_parametrization.Apples;
import method_parametrization.Fruit;
import method_parametrization.comperator.Comperator;


import java.util.List;

/**
 * Created by lukasz on 18.06.17.
 */
public class ComparatorAnonymousClass extends Apples{
    static List<Fruit> inventory = orchard.gatherApples();

    static Comperator<Fruit> byWeight = new Comperator<Fruit>() {
        @Override
        public int compare(Fruit a1, Fruit a2) {
            return a1.getStringWeight().compareTo(a2.getStringWeight());
        }
    };

    static Comperator<Fruit> byWeightLambda = (Fruit a1, Fruit a2)
            -> a2.getStringWeight().compareTo(a1.getStringWeight());


    public static void main(String[] args) {
        inventory.sort(byWeightLambda);

        for (Fruit fruit : inventory) {
            System.out.println(fruit);
        }

        System.out.println("\n");

        inventory.sort(byWeight);

        for (Fruit fruit : inventory) {
            System.out.println(fruit);
        }

        System.out.println("\n");

        inventory.sort((Fruit a1, Fruit a2) -> a1.getColor().compareTo(a2.getColor()));

        for (Fruit fruit : inventory) {
            System.out.println(fruit);
        }

    }
}
