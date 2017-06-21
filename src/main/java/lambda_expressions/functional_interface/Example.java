package lambda_expressions.functional_interface;

/**
 * Created by lukasz on 18.06.17.
 */
public class Example {
    //lambda
    static Runnable r1 = () -> System.out.println("Hello World 1");

    //anonymous class
    static Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Hello World 2");
        }
    };


    public static void main(String[] args) {
        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));
    }

    // lambda passed directly throe runnable functional interface
    public static void process(Runnable r) {
        r.run();
    }
}
