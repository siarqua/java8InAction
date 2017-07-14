package streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by lukasz on 12.07.17.
 */
public class WorkingWithStreams {
    public static final String FILE_NAME = "data.txt";

    public static void main(String[] args) {

        // Filtering and slicing
        // 5.1.1 Filtering with a predicate
        List<Dish> dishes = Dish.generateMenu();
        List<Dish> vegetarianDishes = dishes.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        printDishStream(vegetarianDishes);

        // 5.1.2 Filtering unique elements
        List<Dish> uniqueDishes = dishes.stream()
                .filter(d -> d.getType().equals(DishType.MEAT))
                .distinct()
                .collect(Collectors.toList());

        printDishStream(uniqueDishes);

        // 5.1.3 Truncate
        List<Dish> skippedDishes = dishes.stream()
                .filter(d -> d.getCalories() > 500)
                .skip(3)
                .collect(Collectors.toList());

        printDishStream(skippedDishes);

        // 5.2.1 Mapping
        List<String> mappedDish = dishes.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        printStringStream(mappedDish);

        // 5.2.1 Mapping int
        List<Integer> mappedIntDish = dishes.stream()
                .map(Dish::getName)
                .map(String::length)
                .distinct()
                .collect(Collectors.toList());

        printIntStream(mappedIntDish);

        // 5.2.2 Flattening streams
        List<String[]> flattening_1 = dishes.stream()
                .map(d -> d.getName())
                .map(w -> w.split(""))
                .distinct()
                .collect(Collectors.toList());

        printStringStreamArray(flattening_1);

        // 5.2.2 Flattening streams uding map and arrays.stream
        List<Stream<String>> flattening_2 = dishes.stream()
                .map(d -> d.getName())
                .map(w -> w.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        printStreamString(flattening_2);

        // 5.2.2 Flattening streams using flatmap
        List<String> flattening_3 = dishes.stream()
                .map(d -> d.getName())
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        printStringStream(flattening_3);

        // 5.2.2 example
        List<Integer> numbers_1 = Arrays.asList(1,2,3,4,5);
        List<Integer> squearList = numbers_1.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());
        printIntStream(squearList);

        List<Integer> numbers_2 = Arrays.asList(10,21,23,43,45);

        List<int[]> pairs = numbers_1.stream()
                .flatMap(x -> numbers_2.stream().map(y -> new int[] {x,y}))
                .collect(Collectors.toList());

        printIntStreamArray(pairs);


        // 5.3 Finding and matching
        // Match at least one element
        if (dishes.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("This menu is Vegetarian friendly!!");
        }
        printLine();

        // Match all elements - return boolean

        boolean isHealthy = dishes.stream()
                .allMatch(d -> d.getCalories() < 1000);
        if (isHealthy){
            System.out.println("isHealthy = " + isHealthy);
        }
        printLine();

        // nonMatch - no matches in stream - return boolean

        isHealthy = dishes.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        if (isHealthy){
            System.out.println("isHealthy = " + isHealthy);
        }
        printLine();


        // 5.3.3 Finding an element

        Optional<Dish> dishOptional = dishes.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println("dishOptional = " + dishOptional.get().getName());
        printLine();

        //Print any found element
        dishes.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));
        printLine();

        // Find the first element

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst(); // 9

        System.out.println("firstSquareDivisibleByThree = " + firstSquareDivisibleByThree.get());
        printLine();

        Boolean vegeterianDishes = dishes.stream()
                .map(Dish::isVegetarian)
                .filter(b -> b == true)
                .findFirst().get();
        System.out.println("vegeterianDishes = " + vegeterianDishes);
        printLine();

        // 5.4 Reducing - with initial value
        List<Integer> nums = Arrays.asList(1,1,2,3,5,8,13,21);
        Integer reduceValue = nums.stream()
                .reduce(0, Integer::sum);
        System.out.println("reduceValue = " + reduceValue);
        printLine();

        // Reducing - without initial value
        Optional<Integer> reduceWithoutInitialValue = nums.stream()
                .reduce(Integer::sum);
        System.out.println("Optional reduceWithoutInitialValue.get() = " + reduceWithoutInitialValue.get());
        printLine();

        Optional<Integer> reduceWithoutInitialValueLambda = nums.stream()
                .reduce((a, b) -> a + b);
        System.out.println("Optional reduceWithoutInitialValueLambda.get() = " + reduceWithoutInitialValueLambda.get());
        printLine();

        //Min max value
        dishes.stream()
                .map(Dish::getCalories)
                .reduce(Integer::max)
                .ifPresent(i -> System.out.println("Max calories: " + i));
        printLine();

        dishes.stream()
                .map(Dish::getCalories)
                .reduce((a,b) -> a > b ? a : b)
                .ifPresent(i -> System.out.println("Max calories: " + i));
        printLine();

        dishes.stream()
                .map(Dish::getCalories)
                .reduce(Integer::min)
                .ifPresent(i -> System.out.println("Min calories: " + i));
        printLine();

        // Reduce quiz
        Integer countHowManyDishes = dishes.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        System.out.println("countHowManyDishes = " + countHowManyDishes);
        printLine();

        // 5.6.1 Primitive stream specializations
        int allCaloriesInMenu = dishes.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("allCaloriesInMenu = " + allCaloriesInMenu);
        printLine();

        //Converting
        IntStream intStream = dishes.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();

        //OptionalInt
        OptionalInt optionalInt = dishes.stream()
                .mapToInt(Dish::getCalories)
                .max();
        System.out.println("optionalInt = " + optionalInt);
        printLine();

        // Optional or else
        System.out.println("optionalInt Or else = " + optionalInt.orElse(-1));
        printLine();

        // 5.6.3. Putting numerical streams into practice: Pythagorean triples
        IntStream.rangeClosed(1,100)
               .boxed()
               .flatMap(a -> IntStream.rangeClosed(a,100)
                       .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                       .mapToObj(b -> new int[] {a,b,(int)Math.sqrt(a*a + b*b)})
               )
               .limit(100)
               .forEach(t -> System.out.println(t[0] + ", "+ t[1] + ", " + t[2]));
        printLine();

        IntStream.rangeClosed(1,100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a,100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                        .filter(t -> t[2] % 1 == 0))
                .limit(100)
                .forEach(t -> System.out.println(t[0] + ", "+ t[1] + ", " + t[2]));
        printLine();

        //Building streams
        // 5.7.1 Streams from value

        Stream.of("Java 8", "Lambdas", "In", "Action")
                .map(String::toUpperCase)
                .forEach(System.out::println);
        Stream.empty();
        printLine();

        // 5.7.2 Stream from array
        int[] numbers = {1,1,2,3,5,8,13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println("sum = " + sum);
        printLine();

        // 5.7.3 Streams from file

        try {
            Stream<String> lines = Files.lines(Paths.get("/home/lukasz/dev/Projects/inAction/java8InAction/src/main/resources/data.txt"), Charset.defaultCharset());
            lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        printLine();

        //5.7.4 infinite streams
        //Iterate
        Stream.iterate(0, n-> n+2)
                .limit(10)
                .forEach(System.out::println);

        printLine();

        //Fibonacci
        Stream.iterate(new int[]{0,1},t -> new int[]{t[1], t[0] + t[1]})
                .limit(5)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1]+ ")"));

        printLine();

        Stream.iterate(new int[]{0,1},t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);

        printLine();

        //Generate
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        printLine();

        IntStream.generate(() -> 1)
                .limit(5)
                .forEach(System.out::println);

        printLine();
        IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        }).limit(5)
                .forEach(System.out::println);

        printLine();

        //Fibonacci
        IntSupplier fibSupplier = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        IntStream.generate(fibSupplier)
                .limit(10)
                .forEach(System.out::println);

        printLine();


    }

    private static void printIntStreamArray(List<int[]> pairs) {
        for (int[] pair : pairs) {
            System.out.println("pair = [" + String.valueOf(pair[0]) +", " + String.valueOf(pair[1])+ "]");
        }
        printLine();
    }

    public static void printLine() {
        System.out.println("---------------------------------------------------------------------------------------");
    }

    private static void printStreamString(List<Stream<String>> streamList) {
        for (Stream<String> stream : streamList) {
            stream.forEach(System.out::println);
        }
        printLine();
    }

    private static void printStringStreamArray(List<String[]> strings) {
        for (String[] string : strings) {
            for (String s : string) {
                System.out.println("s = " + s);
            }
        }
        printLine();
    }

    private static void printIntStream(List<Integer> mappedIntDish) {
        for (Integer integer : mappedIntDish) {
            System.out.println("Count = " + integer);
        }
        printLine();
    }


    private static void printDishStream(List<Dish> dishes) {
        for (Dish dish : dishes) {
            System.out.println("Dish = " + dish.getName());
        }
        printLine();
    }

    private static void printStringStream(List<String> dishes) {
        for (String dish : dishes) {
            System.out.println("Dish = " + dish);
        }
        printLine();
    }
}
