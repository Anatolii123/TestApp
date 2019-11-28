package controller;

import dao.PersonCarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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



}
