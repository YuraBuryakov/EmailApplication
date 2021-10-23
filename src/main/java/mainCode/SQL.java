package mainCode;

import java.sql.*;

public class SQL {

    private static final String URL = "jdbc:mysql://localhost:3306/EmailWorker";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    public static final String INSERT_USERS_SQL = "insert into users (firstName,lastName,password, mail) values (?,?,?,?);";

    //Creating an object of Worker
    public static void createAWorker(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preparedStatement = connection.prepareStatement("select firstName,lastName,password,mail,id from users where id = ?");
            preparedStatement.setInt(1,id);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String password = resultSet.getString("password");
                String mail = resultSet.getString("mail");

                Email worker = new Email(firstName,lastName,password,mail,id);

                PersonalAccount.account(worker);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertRecord( String firstName, String lastName, String password, String mail) throws SQLException{
        try (Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,mail);
            preparedStatement.executeUpdate();
            connection.close();
        }

    }

    public static int selectRecord(String mail, String password) throws  SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preparedStatement = connection.prepareStatement("select firstName,lastName,id from users where mail = ? and password = ?");
            preparedStatement.setString(1,mail);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("Welcome " + resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                System.out.println("id - " + resultSet.getInt("id"));
                id = resultSet.getInt("id");
            }


            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;

    }

    // Saving a new password to database
    public static void savingPassword(String password, int id) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{

            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preparedStatement = connection.prepareStatement("update users set password = ? where id = ?");
            preparedStatement.setString(1,password);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
