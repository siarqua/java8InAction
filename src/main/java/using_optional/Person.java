package using_optional;

import java.util.Optional;

/**
 * Created by lukasz on 13.09.17.
 */
public class Person {
    private Optional<Car> optCar;
    private Car car;
    private int age;

    public Optional<Car> getCar(){
        return optCar;
    }

    public Optional<Car> getCarAsOptional(){
        return Optional.ofNullable(car);
    }

    public int getAge() {
        return age;
    }
}
