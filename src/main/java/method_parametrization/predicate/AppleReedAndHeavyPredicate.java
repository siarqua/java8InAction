package method_parametrization.predicate;

import method_parametrization.Fruit;

/**
 * Created by lukasz on 17.06.17.
 */
public class AppleReedAndHeavyPredicate implements ApplePredicate {
    @Override
    public boolean test(Fruit fruit) {
        return "reed".equals(fruit.getColor()) && fruit.getIntWeight() > 150;
    }
}
