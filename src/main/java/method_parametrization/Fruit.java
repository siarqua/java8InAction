package method_parametrization;

/**
 * Created by lukasz on 18.06.17.
 */
public interface Fruit {
    String getColor();

    Integer getId();

    @Override
    String toString();

    int getIntWeight();

    String getStringWeight();
}
