package refactoring_testing_debagging;

import com.sun.org.apache.bcel.internal.generic.DREM;
import lambda_expressions.collecting_data.CaloricLevel;
import streams.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static streams.WorkingWithStreams.printLine;

/**
 * Created by lukasz on 07.09.17.
 */
public class Refactoring {

    public static void main(String[] args) {

        // 8.1.2. From anonymous classes to lambda expressions
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };
        r1.run();
        printLine();

        Runnable r2 = () -> System.out.println("Hello_Lambda");
        r2.run();
        printLine();

        // Task
        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Danger Danger!! Task");
            }
        });
        printLine();

        // Runnable or Task ?
        //doSomething(() -> System.out.println("Danger Danger!! Runnable"));

        // Cast to Task
        doSomething((Task)() -> System.out.println("Danger Danger !! Cast to task"));
        printLine();

        //8.1.3. From lambda expressions to method references
        // without lambda
        Map<CaloricLevel,List<Dish>> dishesByCaloricLevel_normal =
                Dish.generateMenu().stream()
                .collect(
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400 ) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }                        )
                );
        for (Map.Entry<CaloricLevel, List<Dish>> caloricLevelListEntry : dishesByCaloricLevel_normal.entrySet()) {
            System.out.println("caloricLevelListEntry normal = " + caloricLevelListEntry);
        }
        printLine();

        // Extract method and use lambda
        Map<CaloricLevel,List<Dish>> dishesByCaloricLevel_lambda =
                Dish.generateMenu().stream()
                .collect(
                        groupingBy(Dish::getCaloricLevel)
                );
        for (Map.Entry<CaloricLevel, List<Dish>> caloricLevelListEntry : dishesByCaloricLevel_lambda.entrySet()) {
            System.out.println("caloricLevelListEntry lambda = " + caloricLevelListEntry);
        }
        printLine();

        //8.1.4. From imperative data processing to Streams

        //imperative code
        List<String> dishNames = new ArrayList<>();
        for (Dish dish:Dish.generateMenu()){
            if (dish.getCalories() > 300){
                dishNames.add(dish.getName());
            }
        }
        System.out.println("dishNames = " + dishNames);
        printLine();

        //lambda code
        List<String> dishNamesLambda = Dish.generateMenu().parallelStream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .collect(toList());
        System.out.println("dishNamesLambda = " + dishNamesLambda);
        printLine();

        //8.1.5. Improving code flexibility


        // 8.2. Refactoring object-oriented design patterns with lambdas
        // STRATEGY PATTERN
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        Validator loverCaseValidator = new Validator(new IsAllLoveCase());
        boolean b2 = loverCaseValidator.validate("bbbbb");
        System.out.println("numericValidator = " + b1);
        System.out.println("loverCaseValidator = " + b2);
        printLine();

        // Using Lambda expression
        Validator numericValidatorLambda = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b2Lambda = numericValidatorLambda.validate("bbbbb");
        Validator loverCaseValidatorLambda = new Validator((String s) -> s.matches("\\d+"));
        boolean b1Lambda = loverCaseValidatorLambda.validate("aaaa");
        System.out.println("numericValidatorLambda = " + b1Lambda);
        System.out.println("loverCaseValidatorLambda = " + b2Lambda);
        printLine();

        // TEMPLATE METHOD PATTERN
        new OnlineBankingLambda()
                .processCustomer(1, (Customer c) -> System.out.println("Hello " + c.getName()));
        printLine();

        //OBSERVER
        Feed f = new Feed();
        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("money")){
                System.out.println("Breaking news in NY! " + tweet);
            }
        });
        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet);
            }
        });
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");

        printLine();

        // CHAIN OF RESPONSIBILITY
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckProcessing();
        p1.setSuccessor(p2);
        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);
        printLine();

        //Using Lambda
        UnaryOperator<String> headerParssing =
                (String text) -> "From Raoul, MArio and Alan: " + text;
        UnaryOperator<String> spellCeckerPArsing =
                (String text) -> text.replaceAll("labda","lambda");
        Function<String, String> pipeline =
                headerParssing.andThen(spellCeckerPArsing);
        String result1 = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println("result1 = " + result1);
        printLine();

        //8.3. Testing lambdas
    }

    public static void doSomething(Runnable r){r.run();}
    public static void doSomething(Task r){r.execute();}


}

interface Task{
    public void execute();
}

interface ValidationStrategy{
    boolean execute(String s);
}

class IsAllLoveCase implements ValidationStrategy{

    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}

class IsNumeric implements ValidationStrategy{

    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}

class Validator {
    private final ValidationStrategy strategy;

    Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }
    public boolean validate(String s){
        return strategy.execute(s);
    }
}



abstract class OnlineBanking{
    public void processCustomer(int id){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    abstract void makeCustomerHappy(Customer c);
}

class OnlineBankingLambda{
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }
}

class Database {

    public static Customer getCustomerWithId(int id) {
        return new Customer("Adam", "Kowalski", id);
    }
}

class Customer {
    String name;
    String surname;
    int id;

    public Customer(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

interface Observer {
    void notify(String tweet);
}

interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}

class NYTimes implements Observer {

    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("money")){
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}

class Guardian implements Observer{

    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")){
            System.out.println("Yet another news in London... " + tweet);
        }
    }
}

class LeMonde implements Observer{

    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("wine")){
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}

class Feed implements Subject{
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        this.observers.forEach(o -> o.notify(tweet));
    }
}

abstract class ProcessingObject<T>{
    protected ProcessingObject<T> sucessor;

    public void setSuccessor(ProcessingObject<T> suvessor){
        this.sucessor = suvessor;
    }

    public T handle(T input){
        T r = handleWork(input);
        if(this.sucessor != null){
            return sucessor.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);
}

class HeaderTextProcessing extends ProcessingObject<String>{

    @Override
    protected String handleWork(String text) {
        return "From Raul, Mario and Alan: " + text;
    }
}

class SpellCheckProcessing extends ProcessingObject<String>{

    @Override
    protected String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }
}