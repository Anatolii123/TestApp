package dao;

import config.DBUnitConfig;
import entities.Person;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;
import service.PersonCarServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Date;

public class PersonCarDaoImplTest extends DBUnitConfig {

    private PersonCarServiceImpl service = new PersonCarServiceImpl();
    private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("entity/person-data.xml"));

        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public PersonCarDaoImplTest(String name) throws IOException {
        super(name);
    }

    @Test
    public void getSession() {

    }

    @Test
    public void testSavePerson() throws Exception {
        Person person = new Person();
        person.setId(3);
        person.setName("Name");
        person.setBirthDate(Date.valueOf("2000-05-25"));
        service.save(person);

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("entity/person-data-save.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "person", ignore);
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