package lambda_expressions.method_references;

import method_parametrization.Apple;
import method_parametrization.Apples;
import method_parametrization.Fruit;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by lukasz on 18.06.17.
 */
public class ExampleMethodReference extends Apples{
    static List<Fruit> inventory = orchard.gatherApples();
    static List<Integer> weights = Arrays.asList(7, 3, 4, 10);

    private static List<Fruit> mapOneParameter(List<Integer> weights, Function<Integer, Fruit> f) {
        List<Fruit> result = new ArrayList<>();
        for (Integer weight : weights) {
            result.add(f.apply(weight));
        }
        return result;
    }

    private static List<Fruit> mapTwoParameters(List<Integer> weights, BiFunction<String, Integer, Fruit> f) {
        List<Fruit> result = new ArrayList<>();
        for (Integer weight : weights) {
            result.add(f.apply("green",weight));
        }
        return result;
    }

    private static List<Fruit> mapThreeParameters(List<Integer> weights, ThreeFunction<Integer, Integer, String, Fruit> f) {
        List<Fruit> result = new ArrayList<>();
        for (Integer weight : weights) {
            result.add(f.apply(weight,weight, "green"));
        }
        return result;
    }

    // declaration of types of fruits
    static Map<String, Function<Integer,Fruit>> map = new HashMap<>();
    static {
        map.put("apple", Apple::new);
        map.put("apple", Apple::new);
        map.put("apple", Apple::new);
    }

    public static List<Fruit> sortLambda(List<Fruit> inventory){
        inventory.sort((a1, a2) -> a2.getStringWeight().compareTo(a1.getStringWeight()));
        return inventory;
    }

    public static List<Fruit> sortMethodReference(List<Fruit> inventory){
        inventory.sort(Comparator.comparing(Fruit::getStringWeight));
        return inventory;
    }

    public static Fruit giveMeFruit(String name, Integer weight){
        return map.get(name.toLowerCase()).apply(weight);
    }

    static List<String> str = Arrays.asList("a","b","A","B");

    public static void main(String[] args) {
        for (Fruit fruit : sortLambda(inventory)) {
            System.out.println(fruit);
        }

        System.out.println("\n");
        for (Fruit fruit : sortMethodReference(inventory)) {
            System.out.println(fruit);
        }

        System.out.println("\n");
        str.sort((s1, s2) -> s2.compareToIgnoreCase(s1));

        for (String s : str) {
            System.out.println("s = " + s);
        }

        System.out.println("\n");
        str.sort(String::compareToIgnoreCase);
        for (String s : str) {
            System.out.println("s = " + s);
        }

        System.out.println("\n");
        List<Fruit> fruits = mapOneParameter(weights, Apple::new);
        for (Fruit fruit : fruits) {
            System.out.println("apple = " + fruit);
        }

        System.out.println("\n");
        List<Fruit> applesTwoParameters = mapTwoParameters(weights, Apple::new);
        for (Fruit fruit : applesTwoParameters) {
            System.out.println("apple = " + fruit);
        }

        System.out.println("\n");
        List<Fruit> applesThreeParameters = mapThreeParameters(weights, Apple::new);
        for (Fruit fruit : applesThreeParameters) {
            System.out.println("apple = " + fruit);
        }

        // couple of fruit types can be generated
        System.out.println("\n");
        Apple apple = (Apple) giveMeFruit("apple",10);
        System.out.println("fruit = " + apple);
    }
}
