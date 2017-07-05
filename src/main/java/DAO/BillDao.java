package DAO;

import Model.Bill;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by andrew on 20.05.17.
 */
public class BillDao {
    HibSession instance = HibSession.getInstance();
    EntityManager em = HibSession.getInstance().getEm();

    public Bill GetBillByID(String bill_id){
        Bill bill = null;
        Session session = instance.getSession();
        try {
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria(Bill.class).add(Restrictions.eq("numberOfBill",bill_id));
            bill = (Bill) criteria.uniqueResult();
            session.getTransaction().commit();
        }catch (Exception ex){
            session.getTransaction().rollback();
        }
        return bill;
    }
    public void save(Bill bill){
        em.getTransaction().begin();
        em.persist(bill);
        em.getTransaction().commit();
    }


    public List<Bill> getBillsOfClient(int client_id){
        List<Bill> bills = new ArrayList<>();
        Session session = instance.getSession();
        try {
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria(Bill.class).add(Restrictions.eq("client_id",client_id));
            for (Object o : criteria.list()) {
                bills.add((Bill)o);
            }
            session.getTransaction().commit();
        }catch (Exception ex){
            session.getTransaction().rollback();
        }
        return bills;
    }

}
