
/*
 * This file is the registration class for the Vehicle Rental System.
 * 
 * TCSS 305 - Rentz
 */

package model;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import utility.FileLoader;

/**
 * Represents User Sign-in Object.
 * 
 * @author hphobbs
 * @version Winter2020.
 */

public class Registration {

    /**
     * Scanner for the application.
     */
    public static final Scanner SCANNER = new Scanner(System.in);

    /**
     * User Storage File.
     */
    private static final String DEFAULT_USERFILE_NAME = "./resources/registeredusers.txt";

    /**
     * The registered user list for sign in.
     */
    private final Map<String, User> myUserList;

    /** The file of user names. */
    private final String myUserfileName;

    /**
     * Constructs a sign in/registration system.
     */
    public Registration() {

        // load all the users presents in the file
        this(DEFAULT_USERFILE_NAME);
    }

    public Registration(final String theUserfileName) {

        this.myUserfileName = theUserfileName;

        // Loads all the users presents in the file.
        myUserList = FileLoader.readItemsFromFile(theUserfileName);

    }

    /**
     * getter for myUserList.
     * 
     * @return myUserList
     */
    public Map<String, User> getMyUserList() {

        return myUserList;
    }

    /**
     * display sign-in or registration options.
     * 
     * @return boolean true
     */
    public boolean printSignin() {

        boolean result = false;
        final String printPassword = "Password:";
        final String printUserName = "User Name:";

        System.out.print("Enter 1 or 2 (1. New Registration 2. Login):");
        final int numberInput = Integer.parseInt(SCANNER.nextLine());
        //final int numberInput = Integer.parseInt(input.nextLine());
        // nextInt(): reads integers entered

        System.out.println("You entered option " + numberInput);
        System.out.println();
        System.out.println("********************** \n" + "Enter Details \n"
                           + "**********************");

        if (numberInput == 1) {
            System.out.print(printUserName);
            String userName = SCANNER.nextLine();
            // Checks if there is user name input

            while (myUserList.containsKey(userName)) {
                System.out.print("User already exists, enter different user name:");
                userName = SCANNER.nextLine();
            }
            System.out.print(printPassword);
            String password = SCANNER.nextLine();
            // checks for password input

            boolean validPassword = validatingPassword(password, userName);

            while (!validPassword) {
               
                System.out.println("-----Password Does Not Comply----");
                System.out.println("Password should not be username");
                System.out.println("Password must contain: ");
                System.out.println("at least 6 characters");
                System.out.println("at least one digit");
                System.out.println("at least one upper case character");
                System.out.println("at least one lower case character");
                System.out.println("at least one special character");
               
                System.out.print("Enter a Valid Password:");
                password = SCANNER.nextLine();
                validPassword = validatingPassword(password, userName);
            }
            System.out.print("isVIP(true/false):");
            final String vipStat = SCANNER.nextLine();

            final boolean vipStatus;
            if ("true".equals(vipStat)) {
                vipStatus = true;

            } else {
                vipStatus = false;
            }
            final User newUser = new User(userName, password, vipStatus);

            if (register(newUser)) {
                System.out.println("Registration Successfull");
            }

        } else if (numberInput == 2) {
            System.out.print(printUserName);
            String userName = SCANNER.nextLine();
            // Checks if there is user name input

            System.out.print(printPassword);
            String password = SCANNER.nextLine();
            // checks for password input

            while (!login(userName, password)) {
                System.out.println();
                System.out.println("Wrong Credentials");
                System.out.print("Enter User Name:");
                userName = SCANNER.nextLine();
                // Checks if there is user name input

                System.out.print("Enter Password");
                password = SCANNER.nextLine();
                // checks for password input
            }
            System.out.println("Login Successfull");
            result = true;
        }
        return result;
    }

    /**
     * Verify if the password meets the requirements.
     * 
     * @param theUserName
     * @param thePassword
     * @return validPassword
     */

    private boolean validatingPassword(final String thePassword, final String theUserName) {

        boolean validPassword = false;
        final int minLength = 6;
        final String symbols = ".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*";

        if (thePassword.length() >= minLength
            && Pattern.compile(".*[a-z].*").matcher(thePassword).find()
            && Pattern.compile(".*[0-9].*").matcher(thePassword).find()
            && Pattern.compile(".*[A-Z].*").matcher(thePassword).find()
            && Pattern.compile(symbols).matcher(thePassword).find()
            && !(thePassword.contains(theUserName))) {
            validPassword = true;
        }
        return validPassword;
    }

    /**
     * Verify Sign-in procedure.
     * 
     * @param theUsername user name for sign-in
     * @param thePassword password for sign in
     * @return sign-in success
     */
    public boolean login(final String theUsername, final String thePassword) {

        // check if they match and if
        Objects.requireNonNull(theUsername);
        Objects.requireNonNull(thePassword);

        if (theUsername.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException();
        }

        final User userInFile = myUserList.get(theUsername);

        return userInFile != null && userInFile.getMyPassword().equals(thePassword);
    }

    /**
     * Adds a user to the registered user list.
     * 
     * @param theUser an order to add to this shopping cart
     * @return true/false returns if registration is successful
     */
    public boolean register(final User theUser) {
        myUserList.put(theUser.getMyName(), theUser);
        FileLoader.writeUserToFile(myUserfileName, theUser);

        return true;
    }

    /**
     * Empties the user list.
     */
    public void clear() {
        myUserList.clear();
    }

    public String getUserfileName() {
        return myUserfileName;
    }

    @Override
    /**
     * @return the string representation of registration
     */
    public String toString() {
        return "Registered UserList" + myUserList.toString();

    }

}
