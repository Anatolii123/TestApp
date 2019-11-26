package dao;

import entities.Car;
import entities.Person;
import entities.PersonWithCars;
import entities.Statistics;

public interface PersonCarDao {

    void savePerson(Person person);

    void saveCar(Car car);

    PersonWithCars showPersonsCars(String email, String password);

    int createPersonId();

    int createCarId();

    void clear();

    Statistics getStatistics();
}
