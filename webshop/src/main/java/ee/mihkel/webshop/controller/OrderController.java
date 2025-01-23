package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.entity.Order;
import ee.mihkel.webshop.entity.OrderRow;
import ee.mihkel.webshop.entity.Person;
import ee.mihkel.webshop.service.OrderService;
import ee.mihkel.webshop.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@Log4j2 // <-- logide väljanäitamiseks
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    PersonService personService;

    // localhost:8080/orders?personId=1    BODY --> orderRows
    @PostMapping("orders")
    public Order addOrder(@RequestBody List<OrderRow> orderRows, @RequestParam String token) {
        //System.out.println();
        Person person = personService.findPersonByToken(token);
        log.info("Tehti uus tellimus kasutaja poolt, kelle ID: {}. Tellitud tooted: {}", person.id, orderRows);
        return orderService.addOrder(person.id, orderRows);
    }

    @GetMapping("orders")
    public List<Order> getOrders(@RequestParam String token) {
        Person person = personService.findPersonByToken(token);
        return orderService.getPersonOrders(person.id);
    }
}
