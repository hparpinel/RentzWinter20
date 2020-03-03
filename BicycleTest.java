/*
 * test cases of the class bicycle. TCSS 305 - Rentz
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import model.vehicles.Bicycle;
import model.vehicles.Bicycle.BicycleType;

/**
 * @author hphobbs
 * @version Winter 2020
 */
public class BicycleTest {

    /** Object representation of the bicycle. */
    private Bicycle myBicycle;

    /** Object representation of the bicycle. */
    private Bicycle myBicycle2;

    /** set up method */
    @Before
    public void setUp() {

        myBicycle = new Bicycle("Name", "B100", BicycleType.Cruiser);
        myBicycle2 = new Bicycle("Name2", "B102", BicycleType.Mountain);

    }

    /** {@link model.vehicles.Bicycle# */
    @Test
    public void enumBicycleTypeTest() {

        assertEquals(4, BicycleType.values().length);
        assertNotNull(BicycleType.valueOf("Road"));
        assertNotNull(BicycleType.valueOf("Mountain"));
        assertNotNull(BicycleType.valueOf("Cruiser"));
        assertNotNull(BicycleType.valueOf("Hybrid"));

    }

    /**
     * {@link model.vehicles.Bicycle
     */
    @Test
    public void testHashCode() {

        final Bicycle b1 = new Bicycle("name1", "vin1", BicycleType.Road);
        final Bicycle justLikeB1 = new Bicycle("name1", "vin1", BicycleType.Road);
        final Bicycle b2 = new Bicycle("name2", "vin2", BicycleType.Cruiser);

        assertEquals("Same bicycle should return same hashCode()", b1.hashCode(),
                     b1.hashCode());
        assertNotEquals("Different bicycles should return different hashCode", b1.hashCode(),
                        b2.hashCode());
        assertNotEquals("Bicycles with same state return different hashCode since Vehicle ID is included in hashCode, and is unique",
                        b1.hashCode(), justLikeB1.hashCode());
    }

    /**
     * Test whether correct rental amount is returned from calculateRentalAmount method for
     * Cruisers. Test method for {@link Bicycle#calculateRentalAmount()}.
     */
    @Test
    public void testCalculateRentalAmount_Cruiser() {
        final Bicycle bicycle =
                        new Bicycle("test-name", "test-vin", BicycleType.Cruiser);
        assertEquals(Bicycle.CYCLE_FARE.add(Bicycle.CRUISER_CHARGE),
                     bicycle.calculateRentalAmount());
    }

    /**
     * Test whether correct rental amount is returned from calculateRentalAmount method for
     * Hybrid Bicycles. Test method for {@link Bicycle#calculateRentalAmount()}.
     */
    @Test
    public void testCalculateRentalAmount_Hybrid() {
        final Bicycle bicycle = new Bicycle("test-name", "test-vin", BicycleType.Hybrid);
        assertEquals(Bicycle.CYCLE_FARE.add(Bicycle.HYBRID_CHARGE),
                     bicycle.calculateRentalAmount());
    }

    /**
     * Test whether correct rental amount is returned from calculateRentalAmount method for
     * Road Bicycles. Test method for {@link Bicycle#calculateRentalAmount()}.
     */
    @Test
    public void testCalculateRentalAmount_Road() {
        final Bicycle bicycle = new Bicycle("test-name", "test-vin", BicycleType.Road);
        assertEquals(Bicycle.CYCLE_FARE.add(Bicycle.ROAD_BIKE_CHARGE),
                     bicycle.calculateRentalAmount());
    }

    /**
     * Test whether correct rental amount is returned from calculateRentalAmount method for
     * Mountain Bicycles. Test method for {@link Bicycle#calculateRentalAmount()}.
     */
    @Test
    public void testCalculateRentalAmount_Mountain() {
        final Bicycle bicycle =
                        new Bicycle("test-name", "test-vin", BicycleType.Mountain);
        assertEquals(Bicycle.CYCLE_FARE.add(Bicycle.MOUNTAIN_BIKE_CHARGE),
                     bicycle.calculateRentalAmount());
    }

    /**
     * * Test whether correct string representation of Bicycle object is produced once toString
     * is * called. Test method for {@link model.vehicles.Bicycle#toString()}.
     */
    @Test
    public void testToString() {
        assertTrue("toString() does not match: " + myBicycle.toString(), myBicycle.toString()
                        .matches("^BiCycle \\(ID:\\d+, Name:Name, VIN:B100, CanRent\\?:true, CycleType:Cruiser\\)$"));
    }

    /**
     * Test method for {@link model.vehicles.Bicycle#equals(java.lang.Object)}.
     */
    @Test
    public void testEquals() {

        final Bicycle bicycleWithSameValues =
                        new Bicycle("Name", "C100", BicycleType.Road);

        // reflexive property is satisfied
        assertEquals("Equals failed - reflexive", myBicycle, myBicycle);

        // if both Bicycle objects with same values, equals still returns false due to the
        // vehicle ID
        assertNotEquals("Equals failed - value equality", myBicycle, bicycleWithSameValues);

        // the bicycle object must not be null.
        assertNotEquals("Equals failed - null", myBicycle, null);

        // two bicycle objects with different values.
        assertNotEquals("Equals failed -different values", myBicycle, myBicycle2);

        // other object is of a different class.
        assertNotEquals("Equals failed - different class", myBicycle,
                        new ArrayList<Integer>());
    }

    /**
     * 
     * {@link model.vehicles.Bicycle# Bicycle(int, java.lang.String, java.lang.String, boolean, boolean, boolean, boolean)}.
     */
    @Test
    public void testConstructor() {

        assertEquals("Name does not match", "Name", myBicycle.getMyName());

        assertEquals("VIN does not match", "B100", myBicycle.getMyVIN());

        assertEquals("Type does not match", Bicycle.BicycleType.Cruiser,
                     myBicycle.getMyBicycleType());

    }

    /**
     * Test vehicle IDs are assigned sequentially.
     */
    @Test
    public void testVehicleIdsAreAssignedSequentially() {
        final Bicycle c1 = new Bicycle("test-name", "test-vin", BicycleType.Cruiser);
        final Bicycle c2 = new Bicycle("test-name", "test-vin", BicycleType.Hybrid);
        assertEquals("vehicleIDs are sequential", 1,
                     c2.getMyVehicleID() - c1.getMyVehicleID());
    }

    /**
     * {@link model.vehicles.Bicycle#
     */
    @Test
    public void testGetMyName() {

        final Bicycle bicycle = new Bicycle("name1", "vin1", BicycleType.Road);
        assertEquals("getMyName() failed", "name1", bicycle.getMyName());
    }

    /**
     * {@link model.vehicles.Bicycle#
     */
    @Test
    public void testGetMyVIN() {

        final Bicycle bicycle = new Bicycle("name1", "vin1", BicycleType.Road);
        assertEquals("getMyVin() failed", "vin1", bicycle.getMyVIN());
    }

    /**
     * {@link model.vehicles.Bicycle#
     */
    @Test
    public void getMyBicycleType() {
        assertEquals("Type does not match", Bicycle.BicycleType.Cruiser,
                     myBicycle.getMyBicycleType());
    }
}
