package de.nimble.bot.bugreports;

import de.nimble.bot.sql.NimbleConnection;

import java.sql.*;

public class BugReportSql {

  private String dbName;
  private Connection con;

  private static BugReportSql sql;

  public static BugReportSql getInstance() {
    if (sql == null) {
      sql = new BugReportSql();
    }

    return sql;
  }

  private BugReportSql() {
    this.dbName = "BugReports";
    this.con = NimbleConnection.getConnection(this.dbName);
    createTable();
  }

  public void createTable() {
    try {
      Statement st = con.createStatement();
      st.execute(
          "create table if not exists bugreports("
              + "messageId text,"
              + "bugname text,"
              + "description text)");
      st.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public void createBugReport(String messageId, String bugName, String description) {
    String query = "insert into bugreports values(?, ?, ?)";
    try {
      PreparedStatement ps = con.prepareStatement(query);

      ps.setString(1, messageId);
      ps.setString(2, bugName);
      ps.setString(3, description);

      ps.execute();

      ps.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public void deleteBugReport(String messageId) {
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement("delete from bugreports where messageId = ?");
      ps.setString(1, messageId);
      ps.execute();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
  }

  public boolean isBugReport(String messageId) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = con.prepareStatement("select * from bugreports where messageId = ?");
      ps.setString(1, messageId);
      rs = ps.executeQuery();

      System.out.println();

      return rs.next();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    return false;
  }

  public int getNewId() {
    Statement st = null;
    ResultSet rs = null;
    try {
      st = con.createStatement();
      rs = st.executeQuery("select max(id) from bugreports");
      while (rs.next()) {
        return rs.getInt("max(id)") + 1;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (st != null) {
          st.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return -1;
  }

  public void updateMessageId(int id, String messageId) {
    String query = "update bugreports set messageId = ? where id = ?";
    PreparedStatement ps = null;

    try {
      ps = con.prepareStatement(query);

      ps.setString(1, messageId);
      ps.setInt(2, id);

      ps.execute();

      ps.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public String getMessageId(String bugName) {
    String query = "select messageId from bugreports where bugName = ?";
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = con.prepareStatement(query);
      ps.setString(1, bugName);

      rs = ps.executeQuery();

      if (rs.next()) {
        return rs.getString("messageId");
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    return "No message id";
  }

  public void updateID(String bugName, int id) {
    // TODO idk
  }

  public int getID(String bugName) {
    String query = "select id from bugreports where bugName = ?";
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = con.prepareStatement(query);
      ps.setString(1, bugName);

      rs = ps.executeQuery();

      while (rs.next()) {
        // TODO idk
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    return 1;
  }

  public void updateBugName(int id, String bugName) {
    String query = "update bugreports set bugname = ? where id = ?";
    PreparedStatement ps = null;

    try {
      ps = con.prepareStatement(query);

      ps.setString(1, bugName);
      ps.setInt(2, id);

      ps.execute();

      ps.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public String getBugName(int id) {
    String query = "select bugName from bugreports where id = ?";
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = con.prepareStatement(query);
      ps.setInt(1, id);

      rs = ps.executeQuery();

      if (rs.next()) {
        return rs.getString("bugName");
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    return "No bugName";
  }

  public void updateDescription(int id, String description) {
    String query = "update bugreports set description = ? where id = ?";
    PreparedStatement ps = null;

    try {
      ps = con.prepareStatement(query);

      ps.setString(1, description);
      ps.setInt(2, id);

      ps.execute();

      ps.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public String getDescription(int id) {
    String query = "select description from bugreports where id = ?";
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = con.prepareStatement(query);
      ps.setInt(1, id);

      rs = ps.executeQuery();

      if (rs.next()) {
        return rs.getString("description");
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    return "No description";
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public String getDbName() {
    return this.dbName;
  }
}
