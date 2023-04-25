package peaksoft.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession().openSession();
        session.beginTransaction();
        session.createQuery("select create table if not exists users");
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created!");

    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession().openSession();
        session.getTransaction().begin();
        session.createQuery("delete table users");
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted!");

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession().openSession();
        session.getTransaction().begin();
      User user = new User(name,lastName,age);
      session.persist(user);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession().openSession();
        session.getTransaction().begin();
        User user = (User) session.get(User.class ,id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession().openSession();
        session.getTransaction().begin();
       // List<User> users = session.createQuery("select u from User u", User.class).getResultList();
        Query query = session.createQuery("select u from User u");
        List<Integer>users = Collections.singletonList(query.getFirstResult());
        session.getTransaction().commit();
        session.close();
        return Collections.singletonList((User) users);
    }


    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession().openSession();
        session.getTransaction().begin();
        session.createQuery("delete from User");
        session.getTransaction().commit();
        session.close();
    }
}
