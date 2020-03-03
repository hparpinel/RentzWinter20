/*
 * test cases of the class AbstractVehicle. TCSS 305 - Rentz
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.vehicles.AbstractVehicle;

/**
 * @author hphobbs
 * @version Winter 2020
 */
public class AbstractVehicleTest {

    /***/
    private AbstractVehicle myVehicle;
    /***/
    private AbstractVehicle myVehicle2;

    /***/
    @Before
    public void setUp() {

        myVehicle = new TestAbstractVehicle("Name", "V100", true);
        myVehicle2 = new TestAbstractVehicle("Name2", "V102", false);

    }

    /**
     * {@link model.vehicles.AbstractVehicle.
     */
    @Test
    public void testHashCode() {

        final AbstractVehicle v1 = new TestAbstractVehicle("name1", "vin1", true);
        final AbstractVehicle justLikeV1 = new TestAbstractVehicle("name1", "vin1", true);
        final AbstractVehicle v2 = new TestAbstractVehicle("name2", "vin2", true);

        assertEquals("Same abstractVehicle should return same hashCode()", v1.hashCode(),
                     v1.hashCode());
        assertNotEquals("Different abstractVehicles should return different hashCode",
                        v1.hashCode(), v2.hashCode());
        assertNotEquals("AbstractVehicles with same state return different hashCode since Vehicle ID is included in hashCode, and is unique",
                        v1.hashCode(), justLikeV1.hashCode());
    }

    /**
     * Test method for {@link model.vehicles.AbstractVehicle#equals(java.lang.Object)}.
     */
    @Test
    public void testEquals() {

        final AbstractVehicle abstractVehicleWithSameValues =
                        new TestAbstractVehicle("Name", "C100", true);

        // reflexive property is satisfied
        assertEquals("Equals failed - reflexive", myVehicle, myVehicle);

        // if both AbstractVehicle objects with same values, equals still returns false due to
        // the vehicle ID
        assertNotEquals("Equals failed - value equality", myVehicle,
                        abstractVehicleWithSameValues);

        // the abstractVehicle object must not be null.
        assertNotEquals("Equals failed - null", myVehicle, null);

        // two abstractVehicle objects with different values.
        assertNotEquals("Equals failed -different values", myVehicle, myVehicle2);

        // other object is of a different class.
        assertNotEquals("Equals failed - different class", myVehicle,
                        new ArrayList<Integer>());
    }

    /**
     *
     * {@link model.vehicles.AbstractVehicle# AbstractVehicle(int, java.lang.String, java.lang.String, boolean, boolean, boolean, boolean)}.
     */
    @Test
    public void testConstructor() {

        assertEquals("Name does not match", "Name", myVehicle.getMyName());

        assertEquals("VIN does not match", "V100", myVehicle.getMyVIN());

    }

    /**
     * Test vehicle IDs are assigned sequentially.
     */
    @Test
    public void testVehicleIdsAreAssignedSequentially() {

        final AbstractVehicle c1 = new TestAbstractVehicle("test-name", "test-vin", true);
        final AbstractVehicle c2 = new TestAbstractVehicle("test-name", "test-vin", false);

        assertEquals("vehicleIDs are sequential", 1,
                     c2.getMyVehicleID() - c1.getMyVehicleID());
    }

    @Test
    public void testGetMyVehicleID() {

        final AbstractVehicle c1 = new TestAbstractVehicle("test-name", "test-vin", true);
        final AbstractVehicle c2 = new TestAbstractVehicle("test-name", "test-vin", false);

        assertEquals("Getter for vehicle ID failed", 1,
                     c2.getMyVehicleID() - c1.getMyVehicleID(), myVehicle.getMyVehicleID());
    }

    @Test
    public void testGetMyName() {

        assertEquals("Name does not match", "Name", myVehicle.getMyName());
    }

    @Test
    public void testGetMyVIN() {

        assertEquals("getMyVIN() failed- parameterized constructor", "V100",
                     myVehicle.getMyVIN());
    }

    /**
     * * Test whether correct string representation of Vehicle object is produced once toString
     * * is called.
     */

    /**
     * * Test whether correct rental amount is returned from calculateRentalAmount method in
     * the * Vehicle class.
     */
    @Test
    public void testCalculateRentalAmount() {
        final BigDecimal rent = myVehicle.calculateRentalAmount();
        assertEquals("Incorrect rental amount - calculateRentalAmount failed",
                     myVehicle.calculateRentalAmount(), rent);
    }

    /** Test whether the right Base fare is returned */
    @Test
    public void testGetBaseFare() {
        assertEquals("getBaseFare() failed", AbstractVehicle.getBaseFare(),
                     new BigDecimal(10));
    }

    /** test whether the rental status is set */
    @Test
    public void testSetMyRentalStatus() {

        myVehicle.setMyRentalStatus(true);
        assertEquals(myVehicle.getMyRentalStatus(), true);
        myVehicle.setMyRentalStatus(false);
        assertEquals(myVehicle.getMyRentalStatus(), false);
    }

    /***/
    private static class TestAbstractVehicle extends AbstractVehicle {

        /**
         * @param theName
         * @param theVIN
         * @param theRentalStatus
         */
        public TestAbstractVehicle(final String theName, final String theVIN,
                                   final boolean theRentalStatus) {
            super(theName, theVIN);
        }

        @Override
        public BigDecimal calculateRentalAmount() {
            return new BigDecimal(42);
        }

        @Override
        public String toString() {
            return "test toString() value";
        }

    }

}
