package method_parametrization;

import method_parametrization.predicate.PredicateLambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz on 17.06.17.
 */
public class AppleFilterLambda extends Apples{
    private static List<Fruit> inventory = orchard.gatherApples();

    public static void main(String[] args) {
        List<Fruit> result = filter(inventory,(Fruit apple) -> "red".equals(apple.getColor()));

        for (Fruit fruit : result) {
            System.out.println(fruit);
        }
    }

    private static <T> List<T> filter(List<T> inventory, PredicateLambda<T> predicateLambda) {
        List<T> result = new ArrayList<>();
        for (T element : inventory) {
            if(predicateLambda.test(element)){
                result.add(element);
            }
        }
        return result;
    }


}
