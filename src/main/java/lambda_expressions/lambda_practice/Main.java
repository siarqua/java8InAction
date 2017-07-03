package lambda_expressions.lambda_practice;

import method_parametrization.Apples;
import method_parametrization.Fruit;
import method_parametrization.predicate.AppleGreenColorPredicate;

import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by lukasz on 03.07.17.
 */
public class Main extends Apples{
        static List<Fruit> fruits = orchard.gatherApples();

    public static void main(String[] args) {
        // Pass code
        fruits.sort(new AppleComperator());
        printFruits(fruits);

        // Anonymous class
        fruits.sort(new Comparator<Fruit>() {
            @Override
            public int compare(Fruit f1, Fruit f2) {
                return f1.getStringWeight().compareTo(f2.getStringWeight());
            }
        });
        printFruits(fruits);

        //lambda expression
        fruits.sort((Fruit f1, Fruit f2) -> f1.getStringWeight().compareTo(f2.getStringWeight()));
        printFruits(fruits);

        //lambda expression short
        fruits.sort((f1, f2) -> f1.getStringWeight().compareTo(f2.getStringWeight()));
        printFruits(fruits);

        //lambda expression one argument
        Comparator<Fruit> comperator = Comparator.comparing((Fruit a) -> a.getStringWeight());
        fruits.sort(comperator);
        printFruits(fruits);

        // lambda compact form
        fruits.sort(Comparator.comparing((a) -> a.getStringWeight()));
        printFruits(fruits);

        // Method reference
        fruits.sort(Comparator.comparing(Fruit::getStringWeight));
        printFruits(fruits);

        // Method reference reversed
        fruits.sort(Comparator.comparing(Fruit::getStringWeight).reversed());
        printFruits(fruits);

        // Method reference reversed 2 comperators
        fruits.sort(
                Comparator.comparing(Fruit::getColor).reversed()
                        .thenComparing(Fruit::getStringWeight));
        printFruits(fruits);

        // Composing predicates
        AppleGreenColorPredicate appleGreenColorPredicate = new AppleGreenColorPredicate();
        Predicate<Fruit> notGreenFruit = appleGreenColorPredicate.negate();

        print(notGreenFruit);


        Predicate<Fruit> redAndHeavyFruit = appleGreenColorPredicate.and(a -> a.getIntWeight() > 150);

        print(redAndHeavyFruit);

        Predicate<Fruit> redAndHeavyOrGrean = appleGreenColorPredicate
                .negate()
                .and(f -> f.getIntWeight() > 150)
                .or(f -> "green".equals(f.getColor()));

        print(redAndHeavyOrGrean);

        //Composing functions andThen = plusFunction(multipluFunction(x))
        Function<Integer, Integer> plusFunction = x -> x + 1;
        Function<Integer, Integer> multipluFunction = x -> x * 2;
        Function<Integer, Integer> plusThenMultipluFunctionAndthen = plusFunction.andThen(multipluFunction);

        System.out.println("plusThenMultipluFunctionAndthen = " + plusThenMultipluFunctionAndthen.apply(2));

        //Composing functions compose multipluFunction(plusFunction(x))
        Function<Integer, Integer> plusThenMultipluFunctionCompose = plusFunction.compose(multipluFunction);

        System.out.println("plusThenMultipluFunctionCompose = " + plusThenMultipluFunctionCompose.apply(2));
        System.out.println("---------------------------");

        // composing function in practice
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println("transformationPipeline = " + transformationPipeline.apply("XXX labda YYY"));
        Function<String, String> transformationPipeline_2 = addHeader
                .andThen(Letter::addFooter);
        System.out.println("transformationPipeline_2 = " + transformationPipeline_2.apply("XXX"));
        System.out.println("---------------------------");

        // MAthematical lambda example Area under the function f(x) = x + 10 for x from 3 to 7
        System.out.println(" mathematical= " + integrate((double x) -> x + 10,3,7));




    }

    private static double integrate(DoubleFunction<Double>f, double a, double b) {
        // (f(a) + f(b)) * (b-a) / 2.0
        return (f.apply(a) + f.apply(b)) * (b-a) / 2.0;

    }


    private static void print(Predicate<Fruit> predicate) {
        for (Fruit fruit : fruits) {
            if (predicate.test(fruit)){
                System.out.println("fruit = " + fruit);
            }
        }
        System.out.println("--------------------------------------");
    }

    private static void printFruits(List<Fruit> fruits) {
        for (Fruit fruit : fruits) {
            System.out.println("fruit = " + fruit);
        }
        System.out.println("--------------------------------------");
    }
}
