/*
 * Subclass Bicycle. This is an extension of the class Vehicle. TCSS 305 - Rentz
 */

package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author hphobbs
 * @version Winter 2020
 */
public class Bicycle extends AbstractVehicle {

    /** Field variable to represent the mountain bike fee. */
    public static final BigDecimal ROAD_BIKE_CHARGE = CYCLE_FARE;

    /** field variable to represent the mountain bike charge. */
    public static final BigDecimal MOUNTAIN_BIKE_CHARGE =
                    CYCLE_FARE.multiply(new BigDecimal("0.01"));

    /** Field variable to represent the cruiser fee. */
    public static final BigDecimal CRUISER_CHARGE =
                    CYCLE_FARE.multiply(new BigDecimal("0.02"));

    /** Field variable to represent the hybrid fee. */
    public static final BigDecimal HYBRID_CHARGE = CYCLE_FARE.multiply(new BigDecimal("0.04"));

    /** Field variable represents the type of bike. */
    private BicycleType myBicycleType;

    /**
     * @param theName
     * @param theVin
     * @param theBicycleType
     */
    public Bicycle(final String theName, final String theVin,
                   final BicycleType theBicycleType) {
        super(theName, theVin);
        this.myBicycleType = theBicycleType;
    }

    /** @return my bicycle type */
    public BicycleType getMyBicycleType() {
        return myBicycleType;
    }

    /** @return a string representation of the bicycle */
    public String toString() {
        return "BiCycle (ID:" + getMyVehicleID() + ", Name:" + getMyName() + ", VIN:"
               + getMyVIN() + ", CanRent?:" + getMyRentalStatus() + ", CycleType:"
               + myBicycleType + ")";
    }

    /**
     * Redifinition of the equals method from the one in the parent class.
     * 
     * @return equals
     * @param theOtherVehicle
     */
    public boolean equals(final Object theOtherVehicle) {
        boolean result = false;
        
        if (!(theOtherVehicle instanceof Bicycle)) {
            result = false;
        } else {
            final Bicycle otherBicycle = (Bicycle) theOtherVehicle;
            result = super.equals(otherBicycle) && myBicycleType == otherBicycle.myBicycleType;
        }
        return result;
    }

    /**
     * Redifinition of the hashcode method for the one in the parent class.
     * 
     * @return hashcode
     */
    public int hashCode() {
        return super.hashCode() + Objects.hash(myBicycleType);
    }

    /**
     * @SuppressWarnings("incomplete-switch")
     * 
     * @return true when login succesful.
     */
    @Override
    public BigDecimal calculateRentalAmount() {

        BigDecimal rentalAmount = CYCLE_FARE;

        switch (myBicycleType) {
            case Mountain:
                rentalAmount = rentalAmount.add(MOUNTAIN_BIKE_CHARGE);
                break;
            case Cruiser:
                rentalAmount = rentalAmount.add(CRUISER_CHARGE);
                break;
            case Hybrid:
                rentalAmount = rentalAmount.add(HYBRID_CHARGE);
                break;
            case Road:
                rentalAmount = rentalAmount.add(CYCLE_FARE);
                break;
            default:
                break;
        }
        return rentalAmount;
    }

    /**
     * Represents the type of bicycles available.
     *
     */
    public enum BicycleType {
        /** Road Bicycle. */
        Road,

        /** Mountain Bicycle. */
        Mountain,

        /** Cruiser Bicycle. */
        Cruiser,

        /** Hybrid Bicycle. */
        Hybrid
    }

}
