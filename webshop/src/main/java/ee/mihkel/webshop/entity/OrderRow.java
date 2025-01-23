package ee.mihkel.webshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class OrderRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public int quantity;

    @ManyToOne
    public Product product;
}
