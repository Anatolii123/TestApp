package service;

import dao.PersonCarDao;
import entities.Car;
import entities.Person;
import entities.PersonWithCars;
import entities.Statistics;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonCarServiceImpl implements PersonCarService {

    @Autowired
    public PersonCarDao personCarDao;

    @Override
    public void savePerson(Person person) {
        personCarDao.savePerson(person);
    }

    @Override
    public void saveCar(Car car) {
        personCarDao.saveCar(car);
    }

    @Override
    public PersonWithCars showPersonsCars(long personId) {
        return personCarDao.showPersonsCars(personId);
    }

    @Override
    public Statistics getStatistics() {
        return personCarDao.getStatistics();
    }
}
