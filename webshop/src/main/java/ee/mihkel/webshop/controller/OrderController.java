package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.entity.Order;
import ee.mihkel.webshop.entity.OrderRow;
import ee.mihkel.webshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    OrderService orderService;

    // localhost:8080/orders?personId=1    BODY --> orderRows
    @PostMapping("orders")
    public Order addOrder(@RequestParam Long personId, @RequestBody List<OrderRow> orderRows) {
        return orderService.addOrder(personId, orderRows);
    }
}
