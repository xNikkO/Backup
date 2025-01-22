package pl.edu.pjwstk.fanbasedata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String classification;
    private String designation;
    private Double averageHeight;
    private Long averageLifespan;
    private String language;

    @ManyToOne
    @JoinColumn(name = "planet_id")
    private Planet originPlanet;

    private Long swapiId;
}
