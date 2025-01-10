package ee.mihkel.webshop.service;

import ee.mihkel.webshop.entity.Order;
import ee.mihkel.webshop.entity.OrderRow;
import ee.mihkel.webshop.entity.Person;
import ee.mihkel.webshop.entity.Product;
import ee.mihkel.webshop.repository.OrderRepository;
import ee.mihkel.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public Order addOrder(Long personId, List<OrderRow> orderRows) {
        Order order = new Order();
        order.orderRows = orderRows;
        order.created = new Date();
        order.totalSum = calculateTotalSum(orderRows);

        Person person = new Person();
        person.id = personId;
        order.orderer = person;

        return orderRepository.save(order);
    }

    private double calculateTotalSum(List<OrderRow> orderRows) {
        double sum = 0;
        for (OrderRow row: orderRows) {
            Product dbProduct = productRepository.findById(row.product.id).orElseThrow();
            sum += row.quantity * dbProduct.price;
        }
        return sum;
    }
}
