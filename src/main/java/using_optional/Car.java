package using_optional;

import java.util.Optional;

/**
 * Created by lukasz on 13.09.17.
 */
public class Car {
    private Optional<Insurance> insurance;
    public Optional<Insurance> getInsurance(){
        return insurance;
    }
}
