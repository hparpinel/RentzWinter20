/*
 * Abstract vehicle class.
 * TCSS 305 - Rentz
 */
package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author hphobbs
 * @version Winter 2020
 */
public abstract class AbstractVehicle {
    /**
     * Field variable to represet the base fee for renting a vehicle.
     */
    public static final BigDecimal BASE_FARE = new BigDecimal(10);

    /**
     * Field to represent the cycle fee.
     */
    public static final BigDecimal CYCLE_FARE = BASE_FARE;

    /**
     * Field to represent the Bike fee.
     */
    public static final BigDecimal BIKE_FARE = BASE_FARE.multiply(new BigDecimal(2));

    /**
     * Field to represent the car fee.
     */
    public static final BigDecimal CAR_FARE = BASE_FARE.multiply(new BigDecimal(3));

    /** Counter for the vehicle's ID. */
    private static int nextID = 1;

    /** Represents the unique ID for the vehicle. */
    private final int myVehicleID;

    /** Represents the unique VIN for the vehicle. */
    private final String myVIN;

    /** Represents the name of the vehicle. */
    private final String myName;

    /** Represents the rental status of the vehicle. */
    private boolean myRentalStatus = true;

    /**
     * Creates a new vehicle.
     *  @param theName of the vehicle.
     * @param theVIN of the vehicle.
     */
    public AbstractVehicle(final String theName, final String theVIN) {
        this.myVehicleID = nextID++;
        this.myName = theName;
        this.myVIN = theVIN;
    }

    /** @return Calculate rental amount. */
    public abstract BigDecimal calculateRentalAmount();

    /** @return String representation of the vehicle. */
    public abstract String toString();

    /**
     * @param theOtherVehicle to be compared.
     * @return if vehicle is equal to another vehicle.
     */
    public boolean equals(final Object theOtherVehicle) {
        if (!(theOtherVehicle instanceof AbstractVehicle)) {
            return false;
        }
        final AbstractVehicle otherVehicle = (AbstractVehicle) theOtherVehicle;
        return myVehicleID == otherVehicle.myVehicleID
               && Objects.equals(myVIN, otherVehicle.myVIN)
               && Objects.equals(myName, otherVehicle.myName)
               && myRentalStatus == otherVehicle.myRentalStatus;
    }

    @Override
    /**
     * Redifinition of the hashcode method from the one in the parent class.
     * 
     * @return hascode
     */
    public int hashCode() {
        return Objects.hash(myVehicleID, myVIN, myName, myRentalStatus);
    }

    /** @return Vehicle base fare. */
    public static BigDecimal getBaseFare() {
        return BASE_FARE;
    }

    /** @return Vehicle ID. */
    public int getMyVehicleID() {
        return myVehicleID;
    }

    /** @return Vehicle VIN. */
    public String getMyVIN() {
        return myVIN;
    }

    /** @return Vehicle name. */
    public String getMyName() {
        return myName;
    }

    /** @return Vehicle rental status. */
    public boolean getMyRentalStatus() {
        return myRentalStatus;
    }

    /** @param theRentalStatus to set. */ 
    public void setMyRentalStatus(final boolean theRentalStatus) {
        this.myRentalStatus = theRentalStatus;
    }
}
