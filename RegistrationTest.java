/*
 * This file is the Test class for the Registration class.
 * 
 * TCSS 305 - Rentz
 */

package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import model.Registration;
import model.User;
import org.junit.Before;
import org.junit.Test;

/**
 * @author hphobbs
 * @version 1.0 Winter 2020
 */
public class RegistrationTest {

    /**
     * Registration object to test.
     */
    private Registration myReg;

    /**
     * Initializing object registration to test.
     * 
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {
        // Create a new user file for testing purposes.
        this.myReg = createSampleRegistration();
    }

    /**
     * Create a sample user file on disk, then link a Registration object to it.
     * 
     * @throws IOException
     * @return testUsersFilepath
     */
    private Registration createSampleRegistration() throws IOException {

        final File testUsersFile = File.createTempFile("test-", ".tmp");
        testUsersFile.deleteOnExit();
        final FileWriter out = new FileWriter(testUsersFile);
        out.write("test-vip,test-password,true\ntest-non-vip,test-password,false");
        out.close();
        return new Registration(testUsersFile.getPath());
    }

    /**
     * Ensure Registration was created successfully in setUp() method.
     */
    @Test
    public void testRegistration() {

        assertEquals("test failed", 2, myReg.getMyUserList().size());

        assertTrue("test failed", myReg.getMyUserList().containsKey("test-vip"));

        assertEquals("test failed", "test-password",
                     myReg.getMyUserList().get("test-vip").getMyPassword());

        assertTrue("test failed", myReg.getMyUserList().get("test-vip").getMyVIPStatus());

        assertTrue("test failed", myReg.getMyUserList().containsKey("test-non-vip"));

        assertEquals("test failed", "test-password",
                     myReg.getMyUserList().get("test-non-vip").getMyPassword());

        assertFalse("test failed", myReg.getMyUserList().get("test-non-vip").getMyVIPStatus());
    }

    /**
     * Test getMyUserList() Checks if it retrieves myUserList instance field. Test method for
     * {@link model.Registration#getMyUserList()}.
     */
    @Test
    public void testGetMyUserList() {

        assertNotNull("test failed", myReg.getMyUserList());
        assertEquals("Test failed", 2, myReg.getMyUserList().size());
    }

    /**
     * Tests login() Checks user name and password for null values. Checks if the login was
     * successful. Test method for {@link model.Registration#printSignin()}.
     */
    @Test
    public void testLoginReturnTrue() {

        assertTrue("Parameter is false", myReg.login("test-vip", "test-password"));
    }

    /**
     * Test method for {@link model.Registration#printSignin()}.
     */
    @Test
    public void testLoginReturnFalse() {

        assertFalse("Parameter is false", myReg.login("User-does-not-exist", "password"));
    }

    @Test(expected = NullPointerException.class)
    public void nameNullParameter() {
        myReg.login(null, "password");
    }

    @Test(expected = NullPointerException.class)
    public void passwordNullParameter() {
        myReg.login("name", null);
    }

    /***/
    @Test(expected = IllegalArgumentException.class)
    public void passwordIsEmptyString() {
        myReg.login("name1", "");
    }

    /***/
    @Test(expected = IllegalArgumentException.class)
    public void nameIsEmptyString() {
        myReg.login("", "password1");

    }

    /**
     * Test method for {@link model.Registration#register(model.User)}. User list must increase
     * each time a new user is added
     * 
     * @throws IOException
     */
    @Test
    public void testRegisterNewUserAddedToMyUserList1() throws IOException {

        // when a new user is added the MyUserList should increase in size
        final Registration regBefore = createSampleRegistration();
        assertFalse("Test failed", regBefore.getMyUserList().containsKey("name"));

        final User regUser = new User("name", "password", true);
        regBefore.register(regUser);

        assertTrue("Test failed", regBefore.getMyUserList().containsKey("name"));
        assertSame("test failed", regUser, regBefore.getMyUserList().get("name"));

        // Reread the registration file to ensure the user was written to disk
        final Registration regAfter = new Registration(regBefore.getUserfileName());
        assertTrue("Test failed", regAfter.getMyUserList().containsKey("name"));
        assertEquals("Test failed", regUser, regAfter.getMyUserList().get("name"));
    }

    /**
     * Test register to make sure it returns a value of true when a new user has been register
     * successfully.
     * 
     * @throws IOException
     */
    @Test
    public void testRegister() throws IOException {

        final Registration reg = createSampleRegistration();
        assertTrue("Register must return true when new user has been added",
                   reg.register(new User("name", "password", true)));
    }

    /**
     * Test method for {@link model.Registration#clear()}.
     * 
     * @throws IOException
     */
    @Test
    public void testClear() throws IOException {

        // Test if the myUserList has zero elements once the clear method has been called
        final Registration reg1 = createSampleRegistration();
        reg1.clear();

        assertEquals("Clear method failed", 0, reg1.getMyUserList().size());

    }

    /**
     * Test method for {@link model.Registration#toString()}.
     */
    @Test
    public void testToString() {
        // Test if the toString method returns the correct representation of a registration
        // object for the parameterless constructor
        assertEquals("toString() parameterless constructor failed", myReg.toString(),
                     "Registered UserList" + myReg.getMyUserList().toString());
    }

}
