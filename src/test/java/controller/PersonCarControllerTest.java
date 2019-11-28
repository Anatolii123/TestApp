package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Person;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.FileReader;
import java.nio.charset.Charset;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersonCarController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class PersonCarControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(APPLICATION_JSON.getType(),
            APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void savePerson() throws Exception {
        Person person = new Person();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader("Person.json"));
        String requestBody = new ObjectMapper().writeValueAsString(jsonObject);
        this.mockMvc.perform(post("/person")
                .contentType(APPLICATION_JSON_UTF8)
                .content(person.toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void saveCar() {
    }

    @Test
    public void getPersonWithCars() {
    }

    @Test
    public void getStatistics() {
    }

    @Test
    public void clearDB() {
    }
}