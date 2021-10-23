package mainCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Email {
    private static int id = 0;
    private final int defaultpasswordLenght = 10;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String department;
    private int mailboxCapacity = 500;
    private String alternateEmail;
    private String companySuffix = "company.com";

    public Email() {
        System.out.println("Enter first name");
        this.firstName = this.checkName();
        System.out.println("Enter last name");
        this.lastName = this.checkName();
        System.out.println("EMAIL CREATED: " + this.firstName + " " + this.lastName);
        ++id;
        this.department = this.setDepartment();
        this.password = this.randomPassword(this.defaultpasswordLenght);
        System.out.println("Your password is: " + this.password);
        String var10001 = this.firstName.toLowerCase();
        this.email = var10001 + "." + this.lastName.toLowerCase() + "@" + this.department + "." + this.companySuffix;
        System.out.println("Your email is: " + this.email);
    }

    public Email(String firstName, String lastName, String password, String email, int id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    private String setDepartment() {
        System.out.println("DEPARTMENT CODE: \n1 for Sales\n2 for Development\n3 for Accounting\n0 for none\nEnter the department code:");
        Scanner in = new Scanner(System.in);
        int depChoice = in.nextInt();

        if (depChoice == 1) {
            return "sales";
        } else if (depChoice == 2) {
            return "dev";
        } else {
            return depChoice == 3 ? "acct" : "";
        }
    }

    private String randomPassword(int lenght) {
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUWXYZ0123456789!@#$%";
        char[] password = new char[lenght];

        for(int i = 0; i < lenght; ++i) {
            int rand = (int)(Math.random() * (double)passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }

        return new String(password);
    }

    private String checkName() {
        while(true) {
            Scanner myObj = new Scanner(System.in);
            String name = myObj.nextLine();
            if (!name.contains(" ")) {
                return name;
            }

            System.out.println("Please enter one word");
        }
    }

    public void setMailboxCapacity(int capacity) {
        this.mailboxCapacity = capacity;
    }

    public void setAlternateEmail(String altEmail) {
        this.alternateEmail = altEmail;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public int getMailboxCapacity() {
        return this.mailboxCapacity;
    }

    public String getAlternateEmail() {
        return this.alternateEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){return email;}

    public int getId(){return id;};

    public int getDefaultpasswordLenght(){
        return defaultpasswordLenght;
    }

    public String showInfo() {
        return "DISPLAY NAME: " + this.firstName + " " + this.lastName + "\nCOMPANY EMAIL: " + this.email + "\nMAILBOX CAPACITY: " + this.mailboxCapacity + "mb\nID: " + id;
    }
}