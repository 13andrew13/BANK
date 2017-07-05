package DAO;

import Model.Client;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernatePersistence;

import javax.persistence.EntityManager;

/**
 * Created by andrew on 20.05.17.
 */
public class ClientDao {
    private Session session = HibSession.getInstance().getSession();
    private EntityManager em = HibSession.getInstance().getEm();
    public Client getClientByName(String name){
        Client client = null;
        try {
            em.getTransaction().begin();
            Criteria q = session.createCriteria(Client.class).add(Restrictions.eq("name", name));
            client = (Client)q.uniqueResult();
            em.getTransaction().commit();

        }catch (Exception e){
            session.getTransaction().rollback();
        }
        return client;
    }
    public void save(Client client){
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }
}
