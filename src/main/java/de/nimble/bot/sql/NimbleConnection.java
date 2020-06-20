package de.nimble.bot.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NimbleConnection {

  public static Connection getConnection(String path) {
    try {
      return DriverManager.getConnection("jdbc:sqlite:" + path + ".db");
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static void main(String[] args) {
    Connection con = getConnection("test");
    try {
      Statement st = con.createStatement();
      st.executeQuery("create table penis (test Integer)");
      st.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
