//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class Application {
//    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
//    private static final String DB_CONNECTION = "jdbc:postgresql://127.0.0.1:5432/myBase";
//    private static final String DB_USER = "postgres";
//    private static final String DB_PASSWORD = "123145asd";
//
//    public static void main(String[] args) {
//        try {
//            createDbUserTable();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public static Connection getDbConnection() {
//
//        try {
//            Class.forName(DB_DRIVER);
//        } catch (ClassNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        Connection dbConnection = null;
//        try {
//            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
//            if (dbConnection != null) {
//                System.out.println("Connected to the database!");
//                return dbConnection;
//            } else {
//                System.out.println("Failed to make connection!");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dbConnection;
//
//    }
//
//    private static void createDbUserTable() throws SQLException {
//        Connection dbConnection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        PreparedStatement preparedStatement = null;
//        String createTableSQL = "CREATE TABLE public.testTable1( name text ,password text)";
//        String val1 = "INSERT INTO testTable1 (name, password) VALUES ('Petya', 'qwerty1');";
//        String val2 = "INSERT INTO testTable1 (name, password) VALUES ('Vova', 'qwerty2');";
//        String val3 = "INSERT INTO testTable1 (name, password) VALUES ('Misha', 'qwerty3');";
//        String val4 = "INSERT INTO testTable1 (name, password) VALUES ('Dima', 'qwerty4');";
//        try {
//            dbConnection = getDbConnection();
//            statement = dbConnection.createStatement(
//                    ResultSet.TYPE_SCROLL_INSENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE
//            );
//            // выполнить SQL запрос
//            // statement.execute(createTableSQL);
//
//
////            statement.execute(val1);
////            statement.execute(val2);
////            statement.execute(val3);
//
//
//            System.out.println("Table \"testTable1\" is created!");
//
//            String sql;
//            String SQL4 = "Update testTable1  SET password=? WHERE name =?";
//
//            String SQL0 = "SELECT name, password FROM testTable1";
//            sql = "SELECT password FROM testTable1 WHERE password ='qwerty1'";
//            String sql1 = "DELETE FROM testTable1 WHERE name = 'Petya';";
//            String sql2 = "DELETE FROM testTable1 WHERE name = 'Vova';";
//            String sql3 = "DELETE FROM testTable1 WHERE name = 'Misha';";
//
////            statement.execute(val1);
////            statement.execute(val2);
////            statement.execute(val3);
//            //   statement.executeUpdate(val4);
//
//
//            preparedStatement = dbConnection.prepareStatement(SQL0, ResultSet.TYPE_FORWARD_ONLY,
//                    ResultSet.CONCUR_UPDATABLE);
//            //preparedStatement.setString(1, "test");
//            //preparedStatement.setString(2, "Dima");
//            // System.out.println("Rows impacted: " + preparedStatement.executeUpdate());
//
//            resultSet = preparedStatement.executeQuery();
//
//            //boolean isIt = resultSet.first();
//            // System.out.println("Переместился ли указатель? " + isIt);
//            //String newName = resultSet.getString("name") + "_new";
//            // System.out.println("Новое имя? " + newName);
//            // int colm = resultSet.getInt(1);
//            while (resultSet.next()) {
//                resultSet.updateString(1, "11111");
//                resultSet.updateRow();
//            }
//            //statement.executeQuery(sql1);
//            //statement.executeQuery(sql2);
//            // statement.executeQuery(sql3);
//
////            System.out.println("New name: " + resultSet.getString("name"));
//
////            while (resultSet.next()) {
////                String name = resultSet.getString("name");
////                String password = resultSet.getString("password");
////                System.out.println("Username: " + name + "\n" + " Password: " + password);
////            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            System.out.println("Closing connection and releasing resources...");
//            if (statement != null) {
//                statement.close();
//            }
//
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (dbConnection != null) {
//                dbConnection.close();
//            }
//            if (resultSet != null) {
//                resultSet.close();
//            }
//        }
//    }
//}
//
