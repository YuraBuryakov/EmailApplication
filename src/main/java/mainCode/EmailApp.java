package mainCode;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailApp {
    protected static ArrayList<Email> Workers = new ArrayList();

    private static final String URL = "jdbc:mysql://localhost:3306/EmailWorker";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    public static final String INSERT_USERS_SQL = "insert into users (firstName,lastName,password, mail) values (?,?,?,?);";

    public static void main(String[] args) throws SQLException {




        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();

            DriverManager.registerDriver(driver);
           Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            Statement statement = connection.createStatement();



        } catch (SQLException e){
            e.printStackTrace();
        }





        boolean answerForFinish = true;

        do {

            System.out.println("Welcome to Mail application for workers of our company");
            System.out.println("Please choose an action\n1-Log in our account (Does not work now)\n2-Create your account\n3-Exit");
            int answer = LogInOrSignUp.scannerForAnswer(3);

            if  (answer == 1) {
                System.out.println("Welcome to Email for workers!\nPlease enter your email and password");

                    System.out.println("Email:");
                    Scanner inEmail = new Scanner(System.in);
                    String userAnswerEmail = inEmail.next();

                    System.out.println("Password:");
                    Scanner inPassword = new Scanner(System.in);
                    String userAnswerPassword = inPassword.next();

                    System.out.println("Just a second: we are checking you in our system ");

                    int id = selectRecord(userAnswerEmail,userAnswerPassword);

                    if ( id != 0) {
                        createAWorker(id);
                    } else {
                        System.out.println("Unfortunately this account does not exist");
                    }


            } else if(answer == 2){
                Email worker = new Email();
                System.out.println(worker.showInfo());

                insertRecord(worker.getFirstName(),worker.getLastName(),worker.getPassword(),worker.getEmail());

                PersonalAccount.account(worker);

            } else if (answer == 3){
                answerForFinish = Finish();
            }

        } while(answerForFinish);

    }

    public static boolean Finish() {
        System.out.println("Do you want to finish (y/n)");
        Scanner in = new Scanner(System.in);
        char answer = in.next().charAt(0);
        return answer != 'y';
    }

    //SQL COMMANDS

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

    //Creating an object of Worker
    public static void createAWorker(int id) throws  SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preparedStatement = connection.prepareStatement("select firstName,lastName,password,mail from users where id = ?");
            preparedStatement.setInt(1,id);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String password = resultSet.getString("password");
                String mail = resultSet.getString("mail");

                Email worker = new Email(firstName,lastName,password,mail);

                PersonalAccount.account(worker);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



