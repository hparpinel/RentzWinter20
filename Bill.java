/*
 *Bill Class. TCSS 305 - Rentz
 */
package model;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import model.vehicles.AbstractVehicle;

/**
 * @author hphobbs
 * @version Winter 2020
 */
public class Bill {

    /** * Displays an amount fashioned on the US currency system. */
    public static final NumberFormat NF = NumberFormat.getCurrencyInstance(Locale.US);

    /** * A unique integer value. */
    private final int myBillID;

    /** * A User object. */
    private final User myPrimaryUser;

    /** * A Vehicle object. */
    private final AbstractVehicle myVehicle;

    /** * An integer representing the number of days Vehicle is rented. */
    private final int myNumDays;

    /** * Total bill amount. */
    private BigDecimal myBillAmount;

    /**
     * Parameterized constructor for the Bill class. Initializes relevant instance fields. *
     * 
     * @param theBillID A unique integer value assigned to the bill.
     * @param thePrimaryUser A User object.
     * @param theVehicle A Vehicle object.
     * @param theNumDays An integer representing the number of days Vehicle is rented.
     */
    public Bill(final int theBillID, final User thePrimaryUser,
                final AbstractVehicle theVehicle, final int theNumDays) {

        this.myBillID = theBillID;
        this.myPrimaryUser = thePrimaryUser;
        this.myVehicle = theVehicle;
        this.myNumDays = theNumDays;
        this.myBillAmount = new BigDecimal("0.0");
    }

    /**
     * * Computes and prints the total bill amount.
     * 
     * @param theOutput
     */
    public void computeAndPrintAmount(final PrintStream theOutput) {

        theOutput.println("----Cost Information----");
        theOutput.println("RentalPerDay:");

        final BigDecimal calculateRent = myVehicle.calculateRentalAmount();
        theOutput.println("Cost per Day: " + NF.format(calculateRent));
        theOutput.println("No.of Rental days: " + myNumDays);

        final BigDecimal numOfDays = new BigDecimal(myNumDays);

        final BigDecimal totalAmountBefore = calculateRent.multiply(numOfDays);
        theOutput.println("Total Amount: " + NF.format(totalAmountBefore));

        final BigDecimal onePercentRate = new BigDecimal("0.01");

        final BigDecimal insurance = totalAmountBefore.multiply(onePercentRate);
        theOutput.println("Insurance: " + NF.format(insurance));

        BigDecimal vipStatus = new BigDecimal("0.00");

        if (myPrimaryUser.getMyVIPStatus()) {

            vipStatus = totalAmountBefore.multiply(onePercentRate);
            theOutput.println("VIPDiscount: -" + NF.format(vipStatus));

        } else {
            theOutput.println("VIPDiscount: " + NF.format(vipStatus));

        }

        final BigDecimal tax = totalAmountBefore.multiply(new BigDecimal("0.1"));
        theOutput.println("Tax: " + NF.format(tax));

        myBillAmount = totalAmountBefore.add(insurance).subtract(vipStatus).add(tax);

        theOutput.println("Total Rent: " + NF.format(myBillAmount));

    }

    /** @return myBillID */
    public int getBillID() {

        return myBillID;
    }
}
