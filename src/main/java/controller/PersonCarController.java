package controller;

import dao.PersonCarDao;
import entities.Car;
import entities.Person;
import entities.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.PersonCarService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/")
public class PersonCarController {

    @Autowired
    public PersonCarService personCarService;

    @PostMapping(value = "/addPerson")
    public ModelAndView addPerson() {
        return new ModelAndView("addPerson");
    }

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

    @GetMapping(value = "/statistics")
    public Statistics getStatistics() {
        return null;
    }

    @GetMapping(value = "/clear")
    public void clearDB() {

    }

}
