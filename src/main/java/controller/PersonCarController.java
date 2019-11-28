package controller;

import dao.PersonCarDao;
import entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.PersonCarService;

import java.util.Date;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/")
public class PersonCarController {

    @Autowired
    public PersonCarService personCarService;

    @Autowired
    public PersonCarDao personCarDao;

    @PostMapping(value = "/person")
    public void savePerson(@RequestBody Person person) {
        personCarService.savePerson(person);
    }

}
