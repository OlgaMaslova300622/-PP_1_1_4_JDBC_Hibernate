package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {

 private final SessionFactory sessionFactory = Util.getSessionFactory();


 public UserDaoHibernateImpl() {

 }


 @Override
 public void createUsersTable() {

  Transaction transaction = null;
  try (Session session = sessionFactory.openSession()) {
   transaction = session.beginTransaction();

   String createUsTabl = "CREATE TABLE IF NOT EXISTS new_schema.users" + "(id BIGINT NOT NULL AUTO_INCREMENT, " + "name VARCHAR(45), "
           + "lastName VARCHAR(45), "+ "age INT, " + "PRIMARY KEY(id))";

   Query query = session.createSQLQuery(createUsTabl).addEntity(User.class);
   query.executeUpdate();
   transaction.commit();
  } catch (Exception e) {
   if (transaction != null)
    transaction.rollback();
  }
 }

 @Override
 public void dropUsersTable() {

  Transaction transaction = null;
  try (Session session = sessionFactory.openSession()) {
   transaction = session.beginTransaction();

   String dropUsTabl = "DROP TABLE IF EXISTS new_schema.users";

   Query query = session.createSQLQuery(dropUsTabl).addEntity(User.class);
   query.executeUpdate();
   transaction.commit();
  } catch (Exception e) {
   if (transaction != null)
    transaction.rollback();
  }
 }

 @Override
 public void saveUser(String name, String lastName, byte age) {

  Transaction transaction = null;
  try (Session session = sessionFactory.openSession()) {
   transaction = session.beginTransaction();

   User user = new User(name, lastName, age);
   session.save(user);
   transaction.commit();
  } catch (Exception e) {
   if (transaction != null)
    transaction.rollback();
  }
 }

 @Override
 public void removeUserById(long id) {

  Transaction transaction = null;
  try (Session session = sessionFactory.openSession()) {
   transaction = session.beginTransaction();

   User user = session.get(User.class, id);
   session.delete(user);
   transaction.commit();
  } catch (Exception e) {
   if (transaction != null)
    transaction.rollback();
  }
 }

 @Override
 public List<User> getAllUsers() {

  Transaction transaction = null;
  List<User> users = new ArrayList<>();
  try (Session session = sessionFactory.openSession()) {
   transaction = session.beginTransaction();
   users = session.createQuery("from User").list();
   transaction.commit();
  } catch (Exception e) {
   if (transaction != null)
    transaction.rollback();
  }
  return users;
 }

 @Override
 public void cleanUsersTable() {

  Transaction transaction = null;
  try (Session session = sessionFactory.openSession()) {
   transaction = session.beginTransaction();
   String cleanUsTabl = "DELETE FROM User";
   Query query = session.createQuery(cleanUsTabl);
   query.executeUpdate();
   transaction.commit();
  } catch (Exception e) {
   if (transaction != null)
    transaction.rollback();
  }
 }
}
