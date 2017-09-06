package lambda_expressions.collecting_data;

import method_parametrization.comperator.Comperator;
import streams.Dish;
import streams.DishType;
import streams.practice.Transaction;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

import static streams.WorkingWithStreams.printLine;

/**
 * Created by lukasz on 14.07.17.
 */
public class CollectingData {

    public static void main(String[] args) {

        // you have a List of Transactions, and you want to group them based on their nominal currency
        // old java approach
        List<Transaction> transactions = new ArrayList<>();

        Map<Currency, List<Transaction>> transactionByCurrency = new HashMap<>();
        for (Transaction transaction : transactions){
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionForCurrency = transactionByCurrency.get(currency);
            if (transactionForCurrency == null) {
                transactionForCurrency = new ArrayList<>();
                transactionByCurrency.put(currency, transactionForCurrency);
            }
            transactionForCurrency.add(transaction);
        }

        // Lambda way
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
                .collect(groupingBy(Transaction::getCurrency));

        // 6.2. Reducing an summarizing
        Long howManyDishes = Dish.generateMenu().stream()
                .collect(counting());

        System.out.println("howManyDishes = " + howManyDishes);
        printLine();

        howManyDishes = Dish.generateMenu().stream()
                .count();
        System.out.println("howManyDishes = " + howManyDishes);
        printLine();

        //6.2.1 finding minimum and maximum
        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = Dish.generateMenu().stream()
                .collect(maxBy(dishCaloriesComparator));
        System.out.println("mostCalorieDish = " + mostCalorieDish.get().getCalories());
        printLine();

        // 6.2.2 Summarization opperations
        int totalCalories = Dish.generateMenu().stream()
                .collect(summingInt(Dish::getCalories));
        System.out.println("totalCalories = " + totalCalories);
        printLine();

        Double avgCalories = Dish.generateMenu().stream()
                .collect(averagingInt(Dish::getCalories));
        System.out.println("avgCalories = " + avgCalories);
        printLine();

        IntSummaryStatistics intSummaryStatistics = Dish.generateMenu().stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);
        printLine();

        //6.2.3. Joining Strings
        String shortMenu = Dish.generateMenu().stream()
                .map(Dish::getName).collect(joining());
        System.out.println("shortMenu = " + shortMenu);
        printLine();

        // there is not toString in dish
//        Dish.generateMenu().stream()
//                .collect(joining());

        String shortMenu1 = Dish.generateMenu().stream()
                .map(Dish::getName).collect(joining(", "));
        System.out.println("shortMenu = " + shortMenu1);
        printLine();

        //6.2.4 Generalized summarization with reduction
        Integer totalCaloriesq1 = Dish.generateMenu().stream()
                .collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println("totalCaloriesq1 = " + totalCaloriesq1);
        printLine();

        Optional<Dish> mostCaloriesDish1 = Dish.generateMenu().stream()
                .collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println("mostCaloriesDish1 = " + mostCaloriesDish1.get().getName());
        printLine();

        //Collect vs reduce
        Stream<Integer> integerStream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        integerStream.reduce(new ArrayList<Integer>(),
                (List<Integer> l, Integer e)->
        {
            l.add(e);
            return l;
        },(List<Integer> l1, List<Integer> l2)-> {
            l1.addAll(l2);
            return l1;
        } );

//        This solution has two problems: a semantic one and a practical one. The semantic problem lies
//        in the fact that the reduce method is meant to combine two values and produce a new one; it’s
//        an immutable reduction. In contrast, the collect method is designed to mutate a container to
//        accumulate the result it’s supposed to produce. This means that the previous snippet of code is
//        misusing the reduce method because it’s mutating in place the List used as accumulator.

        Integer totalCalories1 = Dish.generateMenu().stream()
                .collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println("totalCalories1 = " + totalCalories1);
        printLine();

        Integer totalCalories2 = Dish.generateMenu().stream()
                .map(Dish::getCalories).reduce(Integer::sum).get();
        System.out.println("totalCalories2 = " + totalCalories2);
        printLine();

        int totalCalories3 = Dish.generateMenu().stream()
                .mapToInt(Dish::getCalories).sum();
        System.out.println("totalCalories3 = " + totalCalories3);
        printLine();

        //6.3 Grouping

        Map<DishType, List<Dish>> dishType = Dish.generateMenu().stream()
                .collect(groupingBy(Dish::getType));
        for (Map.Entry<DishType, List<Dish>> dishTypeListEntry : dishType.entrySet()) {
            System.out.println("dishTypeListEntry = " + dishTypeListEntry);
        }
        printLine();

        //6.4 Classification of an item in the stream during the grouping process
        Map<CaloricLevel, List<Dish>> collect = Dish.generateMenu().stream()
                .collect(
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;

                        })
                );
        for (Map.Entry<CaloricLevel, List<Dish>> caloricLevelListEntry : collect.entrySet()) {
            System.out.println("caloricLevelListEntry = " + caloricLevelListEntry);
        }
        printLine();

        // 6.3.1 Multilevel grouping
        Map<DishType, Map<CaloricLevel, List<Dish>>> dishTypeCalorieLevel = Dish.generateMenu().stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                ));
        for (Map.Entry<DishType, Map<CaloricLevel, List<Dish>>> dishTypeMapEntry : dishTypeCalorieLevel.entrySet()) {
            System.out.println("dishTypeMapEntry = " + dishTypeMapEntry);
        }
        printLine();

        // 6.3.2 Collecting data in subgroups
        Map<DishType, Long> dishTypeCounts = Dish.generateMenu().stream()
                .collect(
                        groupingBy(Dish::getType, counting())
                );
        for (Map.Entry<DishType, Long> dishTypeLongEntry : dishTypeCounts.entrySet()) {
            System.out.println("dishTypeLongEntry = " + dishTypeLongEntry);
        }
        printLine();

        Map<DishType, Optional<Dish>> mostCaloriesBytype = Dish.generateMenu().stream()
                .collect(
                        groupingBy(
                                Dish::getType, maxBy(
                                        comparingInt(Dish::getCalories))
                        ));
        for (Map.Entry<DishType, Optional<Dish>> dishTypeOptionalEntry : mostCaloriesBytype.entrySet()) {
            System.out.println("dishTypeOptionalEntry = " + dishTypeOptionalEntry);
        }
        printLine();

        Map<DishType, Dish> mostCaloriesByType = Dish.generateMenu().stream()
                .collect(
                        groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get))
                );
        for (Map.Entry<DishType, Dish> dishTypeDishEntry : mostCaloriesByType.entrySet()) {
            System.out.println("dishTypeDishEntry = " + dishTypeDishEntry);
        }
        printLine();

        Map<DishType, Set<CaloricLevel>> caloricLevelsByType =
                Dish.generateMenu().stream()
                        .collect(
                                groupingBy(Dish::getType,
                                        mapping(
                                            dish -> {
                                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                            else return CaloricLevel.FAT; },
                                                toCollection(HashSet::new)
                                        )
                                )
                        );

        for (Map.Entry<DishType, Set<CaloricLevel>> dishTypeSetEntry : caloricLevelsByType.entrySet()) {
            System.out.println("dishTypeSetEntry = " + dishTypeSetEntry);
        }
        printLine();

        // 6.4 Partitioning
        Map<Boolean, List<Dish>> partitionedMenu = Dish.generateMenu().stream()
                .collect(partitioningBy(Dish::isVegetarian));
        for (Map.Entry<Boolean, List<Dish>> booleanListEntry : partitionedMenu.entrySet()) {
            System.out.println("booleanListEntry = " + booleanListEntry);
        }
        printLine();

        // get only vegetarian dishes
        List<Dish> vegetarianDishes = partitionedMenu.get(true);

        vegetarianDishes =
                Dish.generateMenu().stream().filter(Dish::isVegetarian).collect(toList());


        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                Dish.generateMenu().stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get)));
        for (Map.Entry<Boolean, Dish> booleanDishEntry : mostCaloricPartitionedByVegetarian.entrySet()) {
            System.out.println("booleanDishEntry = " + booleanDishEntry);
        }
        printLine();









    }
}
