package method_parametrization;

/**
 * Created by lukasz on 17.06.17.
 */
public class ThreadExample {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I`m in thread");
            }
        });

        Thread tl = new Thread(() -> System.out.println("I`m lambda thread"));
    }
}
