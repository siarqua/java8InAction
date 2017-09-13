package using_optional;

import java.util.Optional;

/**
 * Created by lukasz on 13.09.17.
 */
public class Main {
    public static void main(String[] args) {

    }

    /*public String getCarInsuranceName(Person person){
        return person.getCar().getInsurance().getName();
    }*/

    //Defensing programing
//    public String getCarInsuranceNameNullCheck(Person person){
//        if (person != null){
//            Car car = person.getCar();
//            if (car != null){
//                Insurance insurance = car.getInsurance();
//                if (insurance != null){
//                    return insurance.getName();
//                }
//            }
//        }
//        return "Unknown";
//    }

    // wont compile because Optional<Optional<Car>>
//    public String getInsuranceNameOptionalMap(Person person){
//        Optional<Person> optPerson = Optional.of(person);
//        Optional<String> name =
//                optPerson.map(Person::getCar)
//                .map(Car::getInsurance)
//                .map(Insurance::getName);
//    }

    public String getInsuranceNameOptionalFlatMap(Person person){
        Optional<Person> optPerson = Optional.of(person);
        String name =
                optPerson.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
        return name;
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance( Optional<Person> person,Optional<Car> car ){
        return person.flatMap(p -> car.map(
                c -> findCheapestInsurance(p,c)
        ));
    }

    private <Insurance> Insurance findCheapestInsurance(Person p, Car c) {
        return null;
    }




}
