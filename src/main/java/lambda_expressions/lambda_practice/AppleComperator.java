package lambda_expressions.lambda_practice;

import method_parametrization.Apple;
import method_parametrization.Fruit;
import method_parametrization.comperator.Comperator;

/**
 * Created by lukasz on 03.07.17.
 */
public class AppleComperator implements Comperator<Apple> {
    @Override
    public int compare(Fruit f1, Fruit f2) {
        return f1.getStringWeight().compareTo(f2.getStringWeight());
    }
}
