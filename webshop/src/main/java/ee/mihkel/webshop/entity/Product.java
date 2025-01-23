package ee.mihkel.webshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public double price;
    public String description;

    @ManyToOne // --> mitu toodet saavad ühe kategooria all olla
    public Category category;
    public String image;
    public boolean active;

    @OneToOne(cascade = CascadeType.ALL) // kõik liikumised Ratinguga (lisamine/kustumine)
        // toimuvad koos tootega . kui toode kaob, siis reiting kaob.
    public Rating rating;
}
