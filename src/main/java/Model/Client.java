package Model;

import Model.Bill;

import javax.persistence.*;
import java.util.*;/**
 * Created by andrew on 10.05.17.
 */
@Entity
@Table(name= "Clients" )
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;


    @Column(name = "name")
    private String name;

    @Column(name= "surname")
    private String surname;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bills")
    private List<Bill> bills;



    public Client(String name, String surname, ArrayList<Bill> bills) {
        this.name = name;
        this.surname = surname;
        this.bills = bills;
    }

    public List<Bill> getBills() {
        return bills;
    }
}
