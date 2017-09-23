package Java_2.Lessons_Java_2.lesson_7;
/**
 * Created by Александр Руденко on 23.09.2017.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class MakeDBFile implements IConstants {

    final String NAME_TABLE = "users";
    final String SQL_CREATE_TABLE = "CREATE TABLE " + NAME_TABLE +
            "(login  CHAR(6) PRIMARY KEY NOT NULL," +
            " passwd CHAR(6) NOT NULL);";
    final String SQL_INSERT_MIKE = "INSERT INTO " + NAME_TABLE +
            " (login, passwd) " +
            "VALUES ('mike', 'qwerty');";
    final String SQL_INSERT_JONH = "INSERT INTO " + NAME_TABLE +
            " (login, passwd) " +
            "VALUES ('john', '12345');";
    final String SQL_SELECT = "SELECT * FROM " + NAME_TABLE + ";";

    Connection connect;
    Statement stmt;
    ResultSet rs;
    String sql;

    public static void main(String[] args) {
        MakeDBFile my = new MakeDBFile();
        my.addInTable("I", "123");
        my.addInTable("a", "a");
        my.addInTable("b", "b");
        my.printTable();
    }

    MakeDBFile() {
        // open db file
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection(SQLITE_DB);
        } catch (Exception e) {
        }

        // create table
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate(SQL_CREATE_TABLE);
        } catch (Exception e) {
        }

        // insert record(s)
        try {
            stmt.executeUpdate(SQL_INSERT_MIKE);
            stmt.executeUpdate(SQL_INSERT_JONH);
        } catch (Exception e) {
        }

        // print records
        try {
            rs = stmt.executeQuery(SQL_SELECT);
            System.out.println("LOGIN\tPASSWD");
            while (rs.next()) {
                System.out.println(rs.getString("login") + "\t" +
                        rs.getString(PASSWD_COL));
            }
        } catch (Exception e) {
        }
    }


    public void addInTable(String login, String password) {
        // insert record(s)
        try {
            String sqlAdd = "INSERT INTO " + NAME_TABLE +
                    " (login, passwd) " +
                    "VALUES ('" + login + "', '" + password + "');";

            stmt.executeUpdate(sqlAdd);
        } catch (Exception e) {
        }
    }

    public void printTable() {
        // print records
        try {
            rs = stmt.executeQuery(SQL_SELECT);
            System.out.println("LOGIN\tPASSWD");
            while (rs.next()) {
                System.out.println(rs.getString("login") + "\t" +
                        rs.getString(PASSWD_COL));
            }
        } catch (Exception e) {
        }
    }
}