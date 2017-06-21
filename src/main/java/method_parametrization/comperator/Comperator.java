package method_parametrization.comperator;

import method_parametrization.Fruit;

import java.util.Comparator;

/**
 * Created by lukasz on 17.06.17.
 */
public interface Comperator<T> extends Comparator<Fruit> {
    @Override
    int compare(Fruit o1, Fruit o2);

    @Override
    boolean equals(Object obj);
}
