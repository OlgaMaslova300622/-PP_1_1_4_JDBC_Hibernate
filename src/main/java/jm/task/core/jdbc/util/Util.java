package jm.task.core.jdbc.util;

import org.hibernate.resource.beans.container.spi.FallbackContainedBean;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

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




}
