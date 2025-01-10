package ee.mihkel.webshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders") // Tabeli nime vahetuseks
@SequenceGenerator(name = "seq", initialValue = 514312300, allocationSize = 1)
public class Order { // Order tabel on juba PostgreSQL sees reserveeritud. User tabel on ka reserveeritud.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    public Long id;
    public Date created;
    public double totalSum;

    @ManyToOne
    public Person orderer;

    @OneToMany(cascade = CascadeType.ALL)
    public List<OrderRow> orderRows;
}
