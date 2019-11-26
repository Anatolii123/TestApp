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
        Person person = null;
        try {
//            Criteria criteria = getSession().createCriteria(Person.class);
//            criteria.add(Restrictions.eq("ID", personId));
//            person = (Person) criteria.uniqueResult();
//            String encodedPassword = hashPassword(user2.getEmail() +
//                    user2.getPassword() + httpSession.getAttribute("salt"));
//            if (encodedPassword.equals(password)){
//                httpSession.setAttribute("password",encodedPassword);
//                person = user2;
//            } else {
//                person = (People) criteria.uniqueResult();
//                person.setPassword(encodedPassword);
//            }
        } catch (NonUniqueResultException e) {
            throw e;
        } catch (Exception e) {
            person = null;
        } finally {
            session.close();
            return null;
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
        return null;
    }
}
