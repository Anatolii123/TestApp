package service;

import entities.Car;
import entities.Person;
import entities.PersonWithCars;
import entities.Statistics;

public interface PersonCarService {
    void savePerson(Person person);
    void saveCar(Car car);
    PersonWithCars showPersonsCars(long personId);
    Statistics getStatistics();
}
