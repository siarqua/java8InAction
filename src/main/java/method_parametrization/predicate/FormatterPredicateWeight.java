package method_parametrization.predicate;

import method_parametrization.Fruit;

/**
 * Created by lukasz on 17.06.17.
 */
public class FormatterPredicateWeight implements FormatterPredicate {
    @Override
    public String print(Fruit fruit) {
        return String.valueOf(fruit.getIntWeight());
    }
}
