package streams.practice;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lukasz on 13.07.17.
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format("Trader %s in %s", this.name, this.city);
    }

    public static List<Trader> generate(){
        return Arrays.asList(
                new Trader("Raul", "Cambridge"),
                new Trader("Mario","Milan"),
                new Trader("Alan","Cambridge"),
                new Trader("Brian","Cambridge"),
                new Trader("Charls","Seattle"),
                new Trader("Denis","New York")
        );
    }
}
