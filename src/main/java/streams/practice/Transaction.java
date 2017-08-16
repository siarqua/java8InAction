package streams.practice;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

/**
 * Created by lukasz on 13.07.17.
 */
public class Transaction {

    private final Trader trader;
    private final int yer;
    private final int value;
    private Currency currency;

    public Transaction(Trader trader, int yer, int value) {
        this.trader = trader;
        this.yer = yer;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYer() {
        return yer;
    }

    public int getValue() {
        return value;
    }

    @org.jetbrains.annotations.NotNull
    public static List<Transaction> generate(){
        List<Trader> traders = Trader.generate();
        return Arrays.asList(
                new Transaction(traders.get(0),2011,300),
                new Transaction(traders.get(1),2012,1000),
                new Transaction(traders.get(2),2011,400),
                new Transaction(traders.get(3),2012,710),
                new Transaction(traders.get(4),2012,700),
                new Transaction(traders.get(5),2012,950)
        );
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", yer=" + yer +
                ", value=" + value +
                '}';
    }

    public Currency getCurrency() {
        return currency;
    }
}
