package DAO;

import Model.Bill;
import Model.Client;
import Model.Currency;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by andrew on 15.05.17.
 */
public class HibSession {
    private static HibSession instance;

    static {
        try {
            instance = new HibSession();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private final EntityManagerFactory ef = Persistence.createEntityManagerFactory("test");
    private final EntityManager em = ef.createEntityManager();
    private final Session session = (Session) em.getDelegate();
    private final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/BANK?" +
            "user=root&password=9707anton");



    private HibSession() throws SQLException {

    }
    public static HibSession getInstance(){
        return instance;
    }

    public Session getSession() {
        return session;
    }

    public EntityManager getEm() {
        return em;
    }
    public void fillClients(){
        ArrayList<Bill> bills = new ArrayList<>();
        Client client = new Client("andrew","polishchuk",bills);

        Bill bill1 = new Bill("13213",21,client, Currency.UAH);
        bills.add(bill1);
        Bill bill2 = new Bill("21312",231,client,Currency.UAH);
        bills.add(bill2);

        new ClientDao().save(client);
    }

    public Connection getConnection() {
        return connection;
    }
}
