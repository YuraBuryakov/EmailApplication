package mainCode;

import java.sql.SQLException;
import java.util.Scanner;

public class PersonalAccount {
    public static void account(Email worker) throws SQLException {
        while(true){
            try
            {
                Runtime.getRuntime().exec("cmd /c cls");
            }
            catch(final Exception e)
            {
                System.out.print(e);
            }
            System.out.println("Welcome "+ worker.getFirstName() + " " + worker.getLastName()+ " to Your personal account(" + worker.getEmail() + ")");
            System.out.println("1-Change a password\n2-Show the password\n3-Show info\n4-Exit");


            int answer = LogInOrSignUp.scannerForAnswer(5);

            if (answer == 1){
                changingPassword(worker);
            } else if(answer == 2){
                System.out.println(worker.getPassword());
            } else if(answer == 3){
                System.out.println(worker.showInfo());
            } else if (answer == 4){
                break;
            }

        }

    }


    private static void changingPassword(Email worker) throws SQLException {
        System.out.println("Enter your new password(it has to contain 10 characters)");
        Scanner in = new Scanner(System.in);
        String password = in.next();

        if(password.length() == 10){
            System.out.println("Your password is successfully changed");
            SQL.savingPassword(password,worker.getId());
            worker.changePassword(password);

        } else {
            System.out.println("Your password is not changed. It does not contain 10 characters.");
        }

    }


}
