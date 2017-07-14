package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by lukasz on 05.07.17.
 */
public class IntroducingStreams {
    public static void main(String[] args) {

        // dish stream
        List<Dish> menu = Dish.generateMenu();

        List<String> newMenu =  menu.stream()
                // each dish that have calories > 300
                .filter(dish -> dish.getCalories() > 300)
                // get only dish name
                .map(Dish::getName)
                // limit result to top 3
                .limit(3).collect(toList());

        System.out.println("newMenu = " + newMenu);
        System.out.println("---------------------------------------");

        // String stream
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> stream = title.stream();
        stream.forEach(System.out::println);
        // below is illegal state exception because stream was operated or closed
        //stream.forEach(System.out::println);
        System.out.println("---------------------------------------");

        // Collections
        List<String> names = new ArrayList<>();
        for(Dish dish: Dish.generateMenu()){
            names.add(dish.getName());
        }
        for (String name : names) {
            System.out.println("name = " + name);
        }
        System.out.println("---------------------------------------");

        //External iterator
        Iterator<String> iterator = names.iterator();
        while(iterator.hasNext()){
            String s = iterator.next();
            System.out.println("dish = " + s.toString());
        }
        System.out.println("---------------------------------------");

        // Stream get names
        List<String> namesStream = menu.stream()
                .map(Dish::getName)
                .collect(toList());

        for (String s : namesStream) {
            System.out.println("Dish = " + s);
        }
        System.out.println("---------------------------------------");

        // Intermediate operaions
        List<String> newMenu_1 =  menu.stream()
                .filter(dish -> {
                    System.out.println("dish = " + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish ->{
                    System.out.println("dish = " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());

        System.out.println("newMenu_1 = " + newMenu_1);
        System.out.println("---------------------------------------");

        // Count menu items
        long count = menu.parallelStream()
                .filter(dish -> dish.getCalories() > 300)
                .distinct()
                .limit(10)
                .count();

        System.out.println("count = " + count);
        System.out.println("---------------------------------------");






    }



}
