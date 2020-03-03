/*
 * test cases of the class car. TCSS 305 - Rentz
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.vehicles.Car;

/**
 * @author hphobbs
 * @version Winter 2020
 */
public class CarTest {

    /**
     * */
    private Car myCar;
    /**
     * */
    private Car myCar2;

    /**
     * */
    @Before
    public void setUp() {

        myCar = new Car("Name", "B100", true, true, true, true);
        myCar2 = new Car("Name2", "B102", false, false, false, false);

    }

    /**
     * {@link model.vehicles.Car
     */
    @Test
    public void testHashCode() {

        Car b1 = new Car("name1", "vin1", true, true, true, true);
        Car justLikeB1 = new Car("name1", "vin1", true, true, true, true);
        Car b2 = new Car("name2", "vin2", true, false, false, false);

        assertEquals("Same car should return same hashCode()", b1.hashCode(), b1.hashCode());
        assertNotEquals("Different cars should return different hashCode", b1.hashCode(),
                        b2.hashCode());
        assertNotEquals("Cars with same state return different hashCode since Vehicle ID is included in hashCode, and is unique",
                        b1.hashCode(), justLikeB1.hashCode());
    }

    /**
     * Test whether correct rental amount is returned from calculateRentalAmount method for
     * Cruisers. Test method for {@link Car#calculateRentalAmount()}.
     */
    @Test
    public void testCalculateRentalAmount() {
        boolean[] booleans = { true, false };
        for (boolean theRent : booleans) {
            for (boolean theLuxury : booleans) {
                for (boolean theNavigation : booleans) {
                    for (boolean theDrivingAssistance : booleans) {
                        Car car = new Car("test-name", "test-vin", theRent, theLuxury,
                                          theNavigation, theDrivingAssistance);
                        BigDecimal expectedFare = Car.CAR_FARE;
                        if (theLuxury) {
                            expectedFare = expectedFare.add(Car.LUXURY_CHARGE);
                        }
                        if (theNavigation) {
                            expectedFare = expectedFare.add(Car.NAVIGATION_CHARGE);
                        }
                        if (theDrivingAssistance) {
                            expectedFare = expectedFare.add(Car.DRIVING_ASSISTANCE_CHARGE);
                        }
                        assertEquals("Wrong fare for car: " + car, expectedFare,
                                     car.calculateRentalAmount());
                    }
                }
            }
        }
    }

    /**
     * * Test whether correct string representation of Car object is produced once toString is
     * * called. Test method for {@link model.vehicles.Car#toString()}.
     */
    @Test
    public void testToString() {

        assertTrue("toString() does not match: " + myCar.toString(), myCar.toString()
                        .matches("^Car \\(ID:\\d+, Name:Name, VIN:B100, CanRent\\?:true, IsLuxury\\?:true, HasNavigation\\?:true, HasAssistance\\?:true\\)$"));

    }

    /**
     * Test method for {@link model.vehicles.Car#equals(java.lang.Object)}.
     */
    @Test
    public void testEquals() {

        final Car carWithSameValues = new Car("Name", "B100", true, true, true, true);

        // reflexive property is satisfied
        assertEquals("Equals failed - reflexive", myCar, myCar);

        // if both Car objects with same values, equals still returns false due to the vehicle
        // ID
        assertNotEquals("Equals failed - value equality", myCar, carWithSameValues);

        // the car object must not be null.
        assertNotEquals("Equals failed - null", myCar, null);

        // two car objects with different values.
        assertNotEquals("Equals failed -different values", myCar, myCar2);

        // other object is of a different class.
        assertNotEquals("Equals failed - different class", myCar, new ArrayList<Integer>());
    }

    /**
     *
     * {@link model.vehicles.Car# Car(int, java.lang.String, java.lang.String, boolean, boolean, boolean, boolean)}.
     */
    @Test
    public void testConstructor() {

        assertEquals("Name does not match", "Name", myCar.getMyName());

        assertEquals("VIN does not match", "B100", myCar.getMyVIN());

        assertEquals("Getter for myLuxury failed - parameterized constructor", true,
                     myCar.getIsMyLuxury());

    }

    /**
     * Test vehicle IDs are assigned sequentially.
     */
    @Test
    public void testVehicleIdsAreAssignedSequentially() {
        Car c1 = new Car("test-name", "test-vin", true, true, true, true);
        Car c2 = new Car("test-name", "test-vin", true, false, false, false);
        assertEquals("vehicleIDs are sequential", 1,
                     c2.getMyVehicleID() - c1.getMyVehicleID());
    }

    public void testIsMyLuxury() {

        assertEquals("Getter for myLuxury failed - parameterized constructor", true,
                     myCar.getIsMyLuxury());
    }

    /**
     * * Test getter for whether Car object has navigation features. Test method for *
     * {@link model.vehicles.Car#isMyNavigation()}.
     */
    @Test
    public void testIsMyNavigation() {
        assertEquals("Getter for myNavigation failed - parameterized constructor", true,
                     myCar.getIsMyNavigation());
    }

    /**
     * * Test getter for whether Car object has driving assistance features. Test method for *
     * {@link model.vehicles.Car#isMyDrivingAssistance()}.
     */
    @Test
    public void testIsMyDrivingAssistance() {
        assertEquals("Getter for myDrivingAssistance failed - parameterized constructor", true,
                     myCar.getIsMyDrivingAssistance());
    }
}
