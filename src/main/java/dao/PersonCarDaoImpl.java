package dao;

import entities.Car;
import entities.Person;
import entities.PersonWithCars;
import entities.Statistics;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashSet;

public class PersonCarDaoImpl implements PersonCarDao {

    public JdbcTemplate jdbcTemplate;
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    @Autowired
    public PersonCarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void savePerson(Person person) {
        final Session session = getSession();
        try {
            session.beginTransaction();
            person.setId(createPersonId());
            session.save(person);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void saveCar(Car car) {
        final Session session = getSession();
        try {
            session.beginTransaction();
            car.setId(createCarId());
            session.save(car);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public PersonWithCars showPersonsCars(long personId) {
        final Session session = getSession();
        PersonWithCars personWithCars = new PersonWithCars();
        ArrayList<Car> cars = new ArrayList<>();
        try {
            Criteria criteria = getSession().createCriteria(Person.class);
            criteria.add(Restrictions.eq("ID", personId));
            personWithCars.setPerson((Person) criteria.uniqueResult());
            Criteria criteria2 = getSession().createCriteria(Car.class);
            criteria2.add(Restrictions.eq("OWNER_ID", personId));
            for (int i = 0; i < criteria2.list().size(); i++) {
                cars.add((Car) criteria2.list().get(i));
            }
            personWithCars.setPersonsCars(cars);
        } catch (NonUniqueResultException e) {
            throw e;
        } catch (Exception e) {
            personWithCars = null;
        } finally {
            session.close();
            return personWithCars;
        }
    }

    @Override
    public int createPersonId() {
        Criteria criteria = getSession().createCriteria(Person.class).setProjection(Projections.max("id"));
        int newId = (int) criteria.uniqueResult() + 1;
        return newId;
    }

    @Override
    public int createCarId() {
        Criteria criteria = getSession().createCriteria(Car.class).setProjection(Projections.max("id"));
        int newId = (int) criteria.uniqueResult() + 1;
        return newId;
    }

    @Override
    public void clear() {
        final Session session = getSession();
        try {
            session.clear();
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public Statistics getStatistics() {
        final Session session = getSession();
        Statistics statistics = new Statistics();
        ArrayList<Car> cars = new ArrayList<>();
        HashSet<String> vendors = new HashSet<String>();
        try {
            Criteria criteria = getSession().createCriteria(Person.class);
            Criteria criteria2 = getSession().createCriteria(Car.class);
            statistics.setPersoncount((long) criteria.list().size());
            statistics.setCarcount((long) criteria2.list().size());
            for (int i = 0; i < criteria2.list().size(); i++) {
                cars.add((Car) criteria2.list().get(i));
                vendors.add(cars.get(i).getVendorModel());
            }
            statistics.setUniquevendorcount((long)vendors.size());
        } catch (NonUniqueResultException e) {
            throw e;
        } catch (Exception e) {
            statistics = null;
        } finally {
            session.close();
            return statistics;
        }
    }
}
