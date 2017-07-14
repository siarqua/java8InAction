package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lukasz on 05.07.17.
 */
public class Dish {
    static List<Dish> menu = new ArrayList<>();
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final DishType type;

    public Dish(String name, boolean vegetarian, int calories, DishType type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public DishType getType() {
        return type;
    }

    public static List<Dish> generateMenu(){
        menu = Arrays.asList(
                new Dish("Pork", false, 800, DishType.MEAT),
                new Dish("Beef", false, 700, DishType.MEAT),
                new Dish("Chicken", false, 400, DishType.MEAT),
                new Dish("French fries", true, 530, DishType.OTHER),
                new Dish("Rice", true, 350, DishType.OTHER),
                new Dish("Season fruit", true, 120, DishType.OTHER),
                new Dish("Pizza", true, 550, DishType.OTHER),
                new Dish("Prawns", true, 500, DishType.FISH),
                new Dish("Salmon", true, 450, DishType.FISH)
        );
        return menu;
    };

//    @Override
//    public String toString() {
//        return "Dish = (" + name + ", "+vegetarian + ", " + calories + ", "+ type + ")";
//    }
}
