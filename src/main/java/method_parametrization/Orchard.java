package method_parametrization;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz on 17.06.17.
 */
public class Orchard {
    public List<Fruit> gatherApples() {
        List<Fruit> inventory = new ArrayList<>();
        inventory.add(new Apple(1, 120, "green"));
        inventory.add(new Apple(2, 170, "red"));
        inventory.add(new Apple(3, 150, "red"));
        inventory.add(new Apple(4, 130, "green"));
        inventory.add(new Apple(5, 165, "green"));
        return inventory;
    }
}
