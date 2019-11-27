package dao;

import entities.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.HSQLDialect;
import org.junit.Test;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.sql.Date;

public class PersonCarDaoImplTest extends JPAHibernateTest {

    private static final String HBM_DIR_PREFIX = "./src";
    private PersonCarDaoImpl personCarDao = new PersonCarDaoImpl();

//    public PersonCarDaoImplTest() {
//        Configuration configuration = new Configuration();
//        configuration.setProperty(Environment.DRIVER, "org.hsqldb.jdbcDriver");
//        configuration.setProperty(Environment.URL, "jdbc:hsqldb:mem:Test");
//        configuration.setProperty(Environment.USER, "sa");
//        configuration.setProperty(Environment.DIALECT, HSQLDialect.class.getName());
//        configuration.setProperty(Environment.SHOW_SQL, "true");
//        configuration.setProperty(Environment.CACHE_PROVIDER_CONFIG, "org.hibernate.cache.NoCacheProvider");
//        configuration.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
//        configuration.addFile(HBM_DIR_PREFIX + "/hbm/User.hbm.xml");
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
//    }

    @Test
    public void getSession() {

    }

    @Test
    public void savePerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("Name");
        person.setBirthDate(Date.valueOf("25.05.2000"));
        personCarDao.savePerson(person);
    }

    @Test
    public void saveCar() {
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