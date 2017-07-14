package streams.practice;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static streams.WorkingWithStreams.printLine;

/**
 * Created by lukasz on 13.07.17.
 */
public class Example {

    public static void main(String[] args) {

        // Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> transactionList = Transaction.generate().stream()
                .filter(t -> t.getYer() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        for (Transaction transaction : transactionList) {
            System.out.println("transaction = " + transaction);
        }

        printLine();

        // What are all the unique cities where the traders work?
        List<String> citiesList = Trader.generate().stream()
                .map(Trader::getCity)
                .distinct()
                .collect(toList());

        for (String c : citiesList) {
            System.out.println("city = " + c);
        }
        printLine();

        //Find all traders from Cambridge and sort them by name.
        List<Trader> traders = Transaction.generate().stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(comparing(Trader::getName))
                .collect(toList());

        for (Trader trader : traders) {
            System.out.println("trader = " + trader);
        }
        printLine();

        //Return a string of all traders’ names sorted alphabetically.
        String stringNames = Transaction.generate().stream()
                .map(transaction -> transaction.getTrader())
                .map(trader -> trader.getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        System.out.println("stringNames = " + stringNames);
        printLine();

        //Are any traders based in Milan?
        boolean milan = Transaction.generate().stream()
                .map(transaction -> transaction.getTrader())
                .anyMatch(trader -> trader.getCity().equals("Milan"));

        System.out.println("milan = " + milan);

        printLine();

        //Print all transactions’ values from the traders living in Cambridge.
        Transaction.generate().stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        printLine();

        //What’s the highest value of all the transactions?
        Transaction.generate().stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        Transaction.generate().stream()
                .min(comparing(Transaction::getValue))
                .ifPresent(System.out::println);

        printLine();

        //Find the transaction with the smallest value.
        Optional<Transaction> minimalTransactioValue = Transaction.generate().stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println("minimalTransactioValue = " + minimalTransactioValue.get());
    }
}
