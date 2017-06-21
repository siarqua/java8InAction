package method_parametrization.predicate;

import method_parametrization.Fruit;

/**
 * Created by lukasz on 17.06.17.
 */
public class AppleGreenColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Fruit fruit) {
        return "green".equals(fruit.getColor());
    }
}
