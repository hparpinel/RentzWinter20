/*
 * This file is the Test class for the User class.
 * 
 * TCSS 305 - Rentz
 */

package tests;

import model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * @author hphobbs
 * @version 1.0 Winter2020
 */
public class UserTest {

    /** Instance Variable for the object. */
    private User myUser;

    /** Instance Variable for the object. */
    private User myUser1;

    @Before
    public void setUp() {

        myUser = new User("name", "password", true);
        myUser1 = new User("name1", "password1", false);

    }

    /**
     * Test method for {@link model.User#hashCode()}.
     */
    @Test
    public void testHashCode() {

        // Test if hash code method returns the correct hash code value for the objects invoked
        assertEquals("Hashcode failed",
                     Objects.hash(myUser.getMyName(), myUser.getMyPassword(),
                                  myUser.getMyVIPStatus()),
                     myUser.hashCode());

        // Test two objects with equal values
        final User otherUser = new User("name", "password", true);
        assertEquals("Hashcode method failed", myUser.hashCode(), otherUser.hashCode());
    }

    /**
     * Test method for {@link model.User#getMyName()}.
     */
    @Test
    public void testGetMyName() {

        // test if getter method returns the right user name for both constructors
        assertEquals("Getter Failed", "name", myUser.getMyName());
    }

    /**
     * Test method for {@link model.User#getMyPassword()}.
     */
    @Test
    public void testGetMyPassword() {

        // test if getter method returns the right password for both constructors
        assertEquals("Getter Failed", myUser.getMyPassword(), "password");
    }

    /**
     * Test method for {@link model.User#getMyVIPStatus()}.
     */
    @Test
    public void testGetMyVIPStatus() {

        // test if getter method returns the right VIP status for both constructors
        assertEquals("Getter Failed", myUser.getMyVIPStatus(), true);
    }

    /**
     * Test method for {@link model.User#toString()}.
     */
    @Test
    public void testToString() {

        assertEquals("toString format is incorrect", myUser.toString(),
                     "User(name, password, true)");

    }

    /**
     * Test the user(String, String, Boolean). Checks user name and password for null values.
     * Checks if the values for user name, password and VIPstatus are the values expected. Test
     * method for {@link model.User#User(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testUserConstructorStringString() {

        // checks whether the user name contains null values
        assertNotNull("Object is null", myUser.getMyName());

        // checks whether the password contains are null values,
        assertNotNull("Object is null", myUser.getMyPassword());

        // checks if user name equals name
        assertEquals("Parameterized Constructor Failed", "name", myUser.getMyName());

        // checks if user name equals password
        assertEquals("Parameterized Constructor Failed", "password", myUser.getMyPassword());

    }

    /**
     * Test the user(String, String, Boolean). Checks user name and password for null values.
     * Checks if the values for user name, password and VIPstatus are the values expected.
     * {@link model.User#User(java.lang.String, java.lang.String, java.lang.Boolean)}.
     */
    @Test
    public void testUserStringStringBoolean() {

        // checks whether the user name contains null values
        assertNotNull("Object is null", myUser.getMyName());

        // checks whether the password contains are null values,
        assertNotNull("Object is null", myUser.getMyPassword());

        // checks if user name equals name
        assertEquals("Parameterized Costructor Failed", myUser.getMyName(), "name");

        // checks if user name equals password
        assertEquals("Parameterized Costructor Failed", myUser.getMyPassword(), "password");

        // checks if user name equals vipStatus
        assertEquals("Parameterized Costructor Failed", myUser.getMyVIPStatus(), true);

    }

    /***/
    @Test(expected = IllegalArgumentException.class)
    public void nameEmptyString3Parameters() {

        new User("", "password", true);

    }

    /***/
    @Test(expected = IllegalArgumentException.class)
    public void passwordEmptyString3Parameters() {

        new User("name", "", true);

    }

    /***/
    @Test(expected = IllegalArgumentException.class)
    public void nameEmptyString() {
        new User("", "password1");

    }

    /***/
    @Test(expected = IllegalArgumentException.class)
    public void passwordEmptyString() {
        new User("name1", "");

    }

    /**
     * Test method for {@link model.User#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectPositive() {

        final User userEqual = new User("name", "password", true);

        // test if equals() reflexive property is satisfied
        // 1stparameter.equals(2ndparameter)
        assertEquals("equals() failed - reflexive", myUser, myUser);

        // test two different objects with same values
        assertEquals("Test failed", myUser, userEqual);
    }

    /**
     * Test method for {@link model.User#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectNegative() {

        // test object for null values
        assertNotEquals("equals() failed- null object", myUser, null);

        // test two objects with different values
        assertNotEquals("equals() failed- missmatch values", myUser, myUser1);

        // test if the object is from a different class
        assertNotEquals("equals() failed- wrong class", myUser, new ArrayList<Integer>());
    }
}
