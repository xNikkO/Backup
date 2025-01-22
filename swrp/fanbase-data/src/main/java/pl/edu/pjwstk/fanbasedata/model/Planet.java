package pl.edu.pjwstk.fanbasedata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer diameter;
    private Integer rotationPeriod;
    private Integer orbitalPeriod;
    private Double gravity;
    private Double population;
    private String climate;
    private String terrain;
    private Integer surfaceWaterPercentage;
    private Long swapiId;
}
