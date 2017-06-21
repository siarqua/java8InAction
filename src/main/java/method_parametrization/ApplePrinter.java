package method_parametrization;

import method_parametrization.predicate.FormatterPredicate;
import method_parametrization.predicate.FormatterPredicateWeight;

import java.util.List;

/**
 * Created by lukasz on 17.06.17.
 */
public class ApplePrinter extends Apples{

    public static void main(String[] args) {
        prettyPrintApple(orchard.gatherApples(),new FormatterPredicateWeight());
    }

    public static void prettyPrintApple (List<Fruit> inventory, FormatterPredicate predicate) {
        for (Fruit fruit : inventory) {
            String output = predicate.print(fruit);
            System.out.println(output);
        }

    }
}
