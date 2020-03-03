/*
 /*
 * Subclass MotorBike. This is an extension of the class Vehicle. TCSS 305 - Rentz
 */

package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author hphobbs
 * @version Winter 2020
 */
public class MotorBike extends AbstractVehicle {

    /** Field variable to represent the touring fee. */
    public static final BigDecimal TOURING_FEE = new BigDecimal(5);

    /** Field variable to represent touring. */
    private final boolean myTouringStatus;

    /**
     * Parameterized constructor.
     * 
     * @param theName
     * @param theVin
     * @param theRentalStatus
     * @param theTouringStatus
     */
    public MotorBike(final String theName, final String theVin, final boolean theRentalStatus,
                     final boolean theTouringStatus) {
        super(theName, theVin);
        myTouringStatus = theTouringStatus;
    }

    /** Calculates the rental amount. */
    @Override
    public BigDecimal calculateRentalAmount() {
        BigDecimal rentalAmount = BIKE_FARE;
        if (myTouringStatus) {
            rentalAmount = rentalAmount.add(TOURING_FEE);
        }
        return rentalAmount;
    }

    /** @return a string representation of the motorbike */
    public String toString() {
        return "MotorBike (ID:" + getMyVehicleID() + ", Name:" + getMyName() + ", VIN:"
               + getMyVIN() + ", CanRent?:" + getMyRentalStatus() + ", IsTouring?:"
               + myTouringStatus + ")";
    }

    /**
     * Redefinition of the equals method from the one of the parent class.
     * 
     * @param theOtherVehicle
     * @return false
     */
    public boolean equals(final Object theOtherVehicle) {
        boolean result = false;

        if (!(theOtherVehicle instanceof MotorBike)) {
            result = false;
        } else {
            final MotorBike otherMotorBike = (MotorBike) theOtherVehicle;
            result = super.equals(otherMotorBike)
                     && myTouringStatus == otherMotorBike.myTouringStatus;
        }
        return result;
    }

    /**
     * Redifinition of the hashcode method from the one of the parent class.
     * 
     * @return hashcode
     */
    public int hashCode() {
        return super.hashCode() + Objects.hash(myTouringStatus);
    }

}
