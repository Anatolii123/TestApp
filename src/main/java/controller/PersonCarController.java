package controller;

import dao.PersonCarDao;
import entities.Car;
import entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.PersonCarService;

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

    @PostMapping(value = "/car")
    public void saveCar(@RequestBody Car car) {
        personCarService.saveCar(car);
    }

    @GetMapping(value = "/personwithcars", params = {"personId"})
    public void getPersonWithCars(@RequestParam("personId") Long personId) {

    }

}
