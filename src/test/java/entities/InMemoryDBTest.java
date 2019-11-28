package entities;

import config.StudentJpaConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { StudentJpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@WebMvcTest
@Transactional
public class InMemoryDBTest {
    @Resource
    private StudentRepository studentRepository;

    @Test
    public void givenStudent_whenSave_thenGetOk() {
        Student student = new Student(1, "john");
        studentRepository.save(student);

        Student student2 = studentRepository.findOne(1L);
        assertEquals("john", student2.getName());
    }
}
