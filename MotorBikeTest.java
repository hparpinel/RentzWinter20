/*
 * test cases of the class motorbike clas. TCSS 305 - Rentz
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import model.vehicles.MotorBike;
import org.junit.Before;

/**
 * @author hphobbs
 * @version Winter 2020
 */
public class MotorBikeTest {

    private MotorBike myTouringMotorBike, myNonTouringMotorBike;

    @Before
    public void setUp() {

        myTouringMotorBike = new MotorBike("Name", "B100", true, true);
        myNonTouringMotorBike = new MotorBike("Name2", "B102", false, false);

    }

    /**
     * {@link model.vehicles.MotorBike.
     */
    @Test
    public void testHashCode() {

        final MotorBike b1 = new MotorBike("name1", "vin1", true, true);
        final MotorBike justLikeB1 = new MotorBike("name1", "vin1", true, true);
        final MotorBike b2 = new MotorBike("name2", "vin2", true, false);

        assertEquals("Same motorBike should return same hashCode()", b1.hashCode(),
                     b1.hashCode());
        assertNotEquals("Different motorbikes should return different hashCode", b1.hashCode(),
                        b2.hashCode());
        assertNotEquals("Motorbikes with same state return different hashCode since Vehicle ID is included in hashCode, and is unique",
                        b1.hashCode(), justLikeB1.hashCode());
    }

    /**
     * Test whether correct rental amount is returned from calculateRentalAmount method for
     * Touring MotorBikes. Test method for {@link MotorBike#calculateRentalAmount()}.
     */
    @Test
    public void testCalculateRentalAmount_Touring() {
        final MotorBike motorBikeWithTouring = new MotorBike("test-name", "test-vin", true, true);
        assertEquals(MotorBike.BIKE_FARE.add(MotorBike.TOURING_FEE),
                     motorBikeWithTouring.calculateRentalAmount());
    }

    /**
     * Test whether correct rental amount is returned from calculateRentalAmount method for
     * Non-Touring MotorBikes. Test method for {@link MotorBike#calculateRentalAmount()}.
     */
    @Test
    public void testCalculateRentalAmount_NonTouring() {
        final MotorBike motorBikeWithTouring = new MotorBike("test-name", "test-vin", true, false);
        assertEquals(MotorBike.BIKE_FARE, motorBikeWithTouring.calculateRentalAmount());
    }

    /**
     * * Test whether correct string representation of MotorBike object is produced once
     * toString is * called. Test method for {@link model.vehicles.MotorBike#toString()}.
     */
    @Test
    public void testToString() {

        assertTrue("toString() does not match: " + myTouringMotorBike.toString(),
                   myTouringMotorBike.toString()
                                   .matches("^MotorBike \\(ID:\\d+, Name:Name, VIN:B100, CanRent\\?:true, IsTouring\\?:true\\)$"));
    }

    /**
     * Test method for {@link model.vehicles.MotorBike#equals(java.lang.Object)}.
     */
    @Test
    public void testEquals() {

        final MotorBike motorBikeWithSameValues = new MotorBike("Name", "B100", true, true);

        // reflexive property is satisfied
        assertEquals("Equals failed - reflexive", myTouringMotorBike, myTouringMotorBike);

        // if both MotorBike objects with same values, equals still returns false due to the
        // vehicle ID
        assertNotEquals("Equals failed - value equality", myTouringMotorBike,
                        motorBikeWithSameValues);

        // the motorBike object must not be null.
        assertNotEquals("Equals failed - null", myTouringMotorBike, null);

        // two motorBike objects with different values.
        assertNotEquals("Equals failed -different values", myTouringMotorBike,
                        myNonTouringMotorBike);

        // other object is of a different class.
        assertNotEquals("Equals failed - different class", myTouringMotorBike,
                        new ArrayList<Integer>());
    }

    /**
     *
     * {@link model.vehicles.MotorBike# MotorBike(int, java.lang.String, java.lang.String, boolean, boolean, boolean, boolean)}.
     */
    @Test
    public void testConstructor() {

        assertEquals("Name does not match", "Name", myTouringMotorBike.getMyName());

        assertEquals("VIN does not match", "B100", myTouringMotorBike.getMyVIN());

    }

    /**
     * Test vehicle IDs are assigned sequentially.
     */
    @Test
    public void testVehicleIdsAreAssignedSequentially() {
        final MotorBike b1 = new MotorBike("test-name", "test-vin", true, true);
        final MotorBike b2 = new MotorBike("test-name", "test-vin", true, true);
        assertEquals("vehicleIDs are sequential", 1,
                     b2.getMyVehicleID() - b1.getMyVehicleID());
    }

}
