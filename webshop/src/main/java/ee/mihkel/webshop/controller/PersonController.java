package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.entity.Person;
import ee.mihkel.webshop.model.LoginResponse;
import ee.mihkel.webshop.model.SignupResponse;
import ee.mihkel.webshop.repository.CategoryRepository;
import ee.mihkel.webshop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping("signup")
    public SignupResponse signup(@RequestBody Person person) {
        SignupResponse response = new SignupResponse();

        if (personRepository.findByUsername(person.username) == null) {
            personRepository.save(person);
            response.setMessage("Registreerumine õnnestus!");
            response.setSuccessful(true);
            return response;
        } else {
            response.setSuccessful(false);
            response.setMessage("Kasutajanimi puudub!");
            return response;
        }
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody Person person) {
        if (personRepository.findByUsername(person.username) != null) {
            Person dbPerson =personRepository.findByUsername(person.username);
            if (dbPerson.password.equals(person.password)) {
                LoginResponse response = new LoginResponse();
                response.setExpiration(new Date());
                response.setToken("Base-64-kujul-tähed-ja-numbrid");
                return response; // kui isik leiti ja parool on õige
            } else {
                throw new RuntimeException("Parool on vale");
                //return false; // kui isik leiti ja parool on vale
            }
        } else {
            throw new RuntimeException("Kasutajanime pole olemas");
//            return false; // kui isikut ei leitud sellise kasutajanimega
        }
    }


}
