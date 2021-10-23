package mainCode;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailApp {
    protected static ArrayList<Email> Workers = new ArrayList();

    public static void main(String[] args) throws SQLException {

        boolean answerForFinish = true;

        do {

            System.out.println("Welcome to Mail application for workers of our company");
            System.out.println("Please choose an action\n1-Log in our account \n2-Create your account\n3-Exit");
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

                    int id = SQL.selectRecord(userAnswerEmail,userAnswerPassword);

                    if ( id != 0) {
                        SQL.createAWorker(id);
                    } else {
                        System.out.println("Unfortunately this account does not exist");
                    }


            } else if(answer == 2){
                Email worker = new Email();
                System.out.println(worker.showInfo());

                SQL.insertRecord(worker.getFirstName(),worker.getLastName(),worker.getPassword(),worker.getEmail());

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


}



