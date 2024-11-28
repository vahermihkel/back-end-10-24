package ee.ken.seosed.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lemmikloom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String tyyp;
    int kaal;
    double pikkus;

    @ManyToMany
    List<Omanik> omanik;
}
