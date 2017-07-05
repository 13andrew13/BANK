package Model;

import javax.persistence.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by andrew on 10.05.17.
 */
@Entity
@Table(name= "Currency")
public enum  Currency {
    USD("USD",(float)25.34),
    UAH("UAH", 1),
    EUR("EUR", (float)30.40);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private float value;



     Currency(String name, float value) {
        this.name = name;
        this.value = value;

    }

    Currency() {
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }
}
