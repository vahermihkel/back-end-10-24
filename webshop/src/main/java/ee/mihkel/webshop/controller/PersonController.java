package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.entity.Person;
import ee.mihkel.webshop.model.LoginResponse;
import ee.mihkel.webshop.model.SignupResponse;
import ee.mihkel.webshop.repository.CategoryRepository;
import ee.mihkel.webshop.repository.PersonRepository;
import ee.mihkel.webshop.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @PostMapping("signup")
    public SignupResponse signup(@RequestBody Person person) {
        SignupResponse response = new SignupResponse();

        SignupResponse errorResponse = personService.checkIfAllCorrect(person, response);
        if (errorResponse != null) return errorResponse;

        personRepository.save(person);
        response.setMessage("Registreerumine õnnestus!");
        response.setSuccessful(true);
        return response;
    }

    // Controllerisse ei panda funktsioone millel pole @GetMapping/@PostMapping jne..


    @PostMapping("login")
    public LoginResponse login(@RequestBody Person person) {
        Person dbPerson = personService.checkForErrorsAndGetDbPerson(person);

        return personService.generateToken(dbPerson); // kui isik leiti ja parool on õige
    }

    @GetMapping("person")
    public Person getPerson(@RequestParam String token) {
        return personService.findPersonByToken(token);
    }

    @PutMapping("person")
    public Person editPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }


}
