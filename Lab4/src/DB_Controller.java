

import java.sql.*;


class DB_Controller {
    private static final String url = "jdbc:mysql://localhost:3306/lab4";
    private static final String user = "root";
    private static final String password = "qwerty";

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

    static void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void show(){
        final String selectQuerry = "SELECT * FROM Military";
        try {
            resultSet = statement.executeQuery(selectQuerry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void Add(String arg1, String arg2){
        final String updateQuerry = "INSERT INTO Military (Name, Count) VALUES ('" + arg1 + "', '" + arg2 + "')";
        try {
            statement.executeUpdate(updateQuerry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void Search(String arg){
        final String selectQuerry = "SELECT * FROM Military WHERE Name = '" + arg + "'";
        try {
            resultSet = statement.executeQuery(selectQuerry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}