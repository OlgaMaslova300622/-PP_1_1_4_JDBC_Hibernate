package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.resource.beans.container.spi.FallbackContainedBean;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

      private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
      private static final String DB_URL = "jdbc:mysql://localhost:3306";
      private static final String DB_USERNAME = "Olga";
      private static final String DB_PASSWORD = "root";

      public static Connection getConnection() {
          Connection connection = null;
          try {
              Class.forName(DB_DRIVER);
              connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
              System.out.println("Соединение установлено");
          } catch (ClassNotFoundException | SQLException e) {
              e.printStackTrace();
              System.out.println("ERROR");
          }
          return connection;
      }

     private static SessionFactory sessionFactory;

      private Util() {};
      public static SessionFactory getSessionFactory() {
          if (sessionFactory == null) {
              try {
                  Configuration configuration = new Configuration();
                  Properties settings = new Properties();
                  settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                  settings.put(Environment.URL, "jdbc:mysql://localhost:3306/new_schema?serverTimezone=Europe/Moscow");
                  settings.put(Environment.USER, "Olga");
                  settings.put(Environment.PASS, "root");
                  settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                  settings.put(Environment.SHOW_SQL, "false");
                  settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                  settings.put(Environment.HBM2DDL_AUTO, "update");

                  configuration.setProperties(settings);

                  configuration.addAnnotatedClass(User.class);
                  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                          .applySettings(configuration.getProperties()).build();

                  sessionFactory = configuration.buildSessionFactory(serviceRegistry);


                  System.out.println("Соединение установлено1");

              } catch (Exception e) {
                  e.printStackTrace();
                  System.out.println("ERROR1");
              }
          }
          return sessionFactory;
      }




}
