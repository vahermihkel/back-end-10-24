package ee.mihkel.salat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Toiduaine {
    @Id
    String nimi;
    int valk;
    int sysivesik;
    int rasv;
}

// constructor --> uue objekti loomiseks
// getter --> API otspunktist objektide kättesaamiseks
