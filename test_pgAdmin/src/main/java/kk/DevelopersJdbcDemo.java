package kk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DevelopersJdbcDemo {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
//   // @Language("SQL")
//   //language=SQL
//    String q = "create table developers ( uid SERIAL PRIMARY KEY, name text, speciality text, salary int);";
    public static void main(String[] args) throws Exception {
      //  Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://85.113.129.95:5432/myBase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "123145asd");
        //props.setProperty("currentSchema", "dot_test");
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


//        CREATE TABLE PROSELYTE_TUTORIALS.developers(
//                id INT NOT NULL AUTO_INCREMENT,
//                name VARCHAR(50)NOT NULL,
//                specialty VARCHAR(50)NOT NULL,
//                salary INT NOT NULL,
//                PRIMARY KEY(id));


        try (Connection conn = DriverManager.getConnection(url, props)) {
            System.out.println("Connecting successful");

            //conn.setAutoCommit(false);
            try (Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                s.execute("drop table if exists developers");
                s.execute("create table developers (uid SERIAL PRIMARY KEY, name text, speciality text, salary int);");
                s.execute("insert into developers (name, speciality, salary) values ('Jhon','Java','2000'), ('David', 'C++', '2500')");
            }

            try (PreparedStatement ps = conn.prepareStatement("select uid, salary from developers",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int s = rs.getInt("salary");
                    if (s == 2000) {
                        s = s + 1100;
                    } else {
                        System.out.println("Not found");
                    }
                    rs.updateInt("salary", s);
                    rs.updateRow();
                    System.out.println("row updated");
                }
            }
        }
    }
}
