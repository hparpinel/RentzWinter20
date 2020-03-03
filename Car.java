/*
 /*
 * Subclass Car. This is an extension of the class Vehicle. TCSS 305 - Rentz
 */

package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author hphobbs
 * @version Winter 2020
 */
public class Car extends AbstractVehicle {

    /** Field variable to represent the charge fee for the luxury featury. */
    public static final BigDecimal LUXURY_CHARGE = new BigDecimal(10);

    /** field variable to represent the charge fee for the navigation feature. */
    public static final BigDecimal NAVIGATION_CHARGE = new BigDecimal(1);

    /** Field variable to represent the charge fee for driving assistance feature. */
    public static final BigDecimal DRIVING_ASSISTANCE_CHARGE = new BigDecimal(2);

    /** Field variable to represent if the vehicle has luxury features. */
    private final boolean myLuxury;

    /** Field variable to represent if the vehicle has navigation features. */
    private final boolean myNavigation;

    /** Field variable to represent if the vehicle has driving assistance features. */
    private final boolean myDrivingAssistance;

    /**
     * Parameterized constructor calls parent class constructor to initialize Car() field
     * variables.
     *
     * @param theName of the vehicle.
     * @param theVin Vehicle Identification number.
     * @param theRent availability of the vehicle.
     * @param theLuxury features of the vehicle.
     * @param theNavigation features of the vehicle.
     * @param theDrivingAssistance features of the vehicle.
     */
    public Car(final String theName, final String theVin, final boolean theRent,
               final boolean theLuxury, final boolean theNavigation,
               final boolean theDrivingAssistance) {

        super(theName, theVin);

        this.myLuxury = theLuxury;
        this.myNavigation = theNavigation;
        this.myDrivingAssistance = theDrivingAssistance;

    }

    /** @return myLuxury */
    public boolean getIsMyLuxury() {
        return myLuxury;

    }

    /** @return myNavigation */
    public boolean getIsMyNavigation() {
        return myNavigation;

    }

    /** @return myDrivingAssistance */
    public boolean getIsMyDrivingAssistance() {
        return myDrivingAssistance;
    }

    /** @return RentalAmount */
    public BigDecimal calculateRentalAmount() {
        BigDecimal rentalAmount = CAR_FARE;
        if (myLuxury) {
            rentalAmount = rentalAmount.add(LUXURY_CHARGE);
        }
        if (myNavigation) {
            rentalAmount = rentalAmount.add(NAVIGATION_CHARGE);
        }
        if (myDrivingAssistance) {
            rentalAmount = rentalAmount.add(DRIVING_ASSISTANCE_CHARGE);
        }
        return rentalAmount;
    }


    /** @return an string representation of the car class */
    @Override
    public String toString() {
        // Car (ID:3, Name:BMW, VIN:V102, CanRent?:true, IsLuxury?:true,
        // HasNavigation?:true, HasAssistance?:true)
        return "Car (ID:" + getMyVehicleID() + ", Name:" + getMyName() + ", VIN:" + getMyVIN()
               + ", CanRent?:" + getMyRentalStatus() + ", IsLuxury?:" + myLuxury
               + ", HasNavigation?:" + myNavigation + ", HasAssistance?:" + myDrivingAssistance
               + ")";
    }

   
    /** Redifinition of the equals method from the one of the parent class. */
    @Override
    public boolean equals(final Object theOtherVehicle) {
        
        boolean result = false;

        if (!(theOtherVehicle instanceof Car)) {
            result = false;

        } else {
            final Car otherCar = (Car) theOtherVehicle;
            result = super.equals(otherCar) && myLuxury == otherCar.myLuxury
                     && myNavigation == otherCar.myNavigation
                     && myDrivingAssistance == otherCar.myDrivingAssistance;
        }
        return result;
    }

    /** Redifinition of the hascode method from the one in the parent class. */
    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(myLuxury, myNavigation, myDrivingAssistance);
    }

}
