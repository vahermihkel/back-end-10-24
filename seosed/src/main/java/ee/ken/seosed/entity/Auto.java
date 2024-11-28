package ee.ken.seosed.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String mark;
    double pikkus;
    int mass;
    int aasta;

    // @OneToOne --> Omanik ei tohi omada rohkem kui ühte autot
    // @ManyToOne --> Omanik klassil võib olla mitu autot

    // @OneToMany --> peab olema List tüübiks
    // @ManyToMany --> peab olema List tüübiks

    @ManyToOne
    Omanik omanik;
}
