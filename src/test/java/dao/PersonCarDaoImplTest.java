package dao;

import config.JpaConfig;
import entities.Car;
import entities.CarRepository;
import entities.Person;
import entities.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.sql.Date;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { JpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@WebMvcTest
@Transactional
public class PersonCarDaoImplTest {

    @Resource
    private PersonRepository personRepository;

    @Resource
    private CarRepository carRepository;

    @Test
    public void getSession() {

    }

    @Test
    public void testSavePerson() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setName("Name");
        person.setBirthDate(Date.valueOf("2000-05-25"));
        personRepository.save(person);
        Person person2 = personRepository.findOne(1L);

        assertEquals("Name", person2.getName());
    }

    @Test
    public void testSaveCar() {
        Car car = new Car();
        car.setId(1);
        car.setModel("BMW-X5");
        car.setHorsepower(381);
        car.setOwnerId(1);
        carRepository.save(car);
        Car car2 = carRepository.findOne(1L);

        assertEquals("BMW", car2.getVendorModel());
    }

    @Test
    public void showPersonsCars() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void getStatistics() {
    }
}