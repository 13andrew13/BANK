package Model;

import DAO.BillDao;

import javax.persistence.*;

/**
 * Created by andrew on 10.05.17.
 */
@Entity
@Table(name = "Bills")
public class Bill  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "numberOfBill")
    private String numberOfBill;

    @Column(name = "MONEY")
    private float value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client")
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency")
    private Currency currency = Currency.UAH;




    public Bill(String numberOfBill, float value,Client client,Currency currency) {
        this.numberOfBill = numberOfBill;
        this.value = value;
        this.client = client;
        this.currency = currency;



    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {

        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
