package method_parametrization;

import method_parametrization.predicate.ApplePredicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz on 17.06.17.
 */
public class ApplwFilterAnonymouse extends Apples {

    static List<Fruit> redFruits = filterApples(orchard.gatherApples(),new ApplePredicate(){

        @Override
        public boolean test(Fruit fruit) {
            return "reed".equals(fruit.getColor());
        }
    });

    private static List<Fruit> filterApples(List<Fruit> fruits, ApplePredicate applePredicate) {
        List<Fruit> result = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (applePredicate.test(fruit)){
                result.add(fruit);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        for (Fruit fruit : redFruits) {
            System.out.println(fruit);
        }
    }


}
