/*
 * Rental Manager Class. TCSS 305 - Rentz
 */

package model;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import model.vehicles.AbstractVehicle;
import model.vehicles.Bicycle;
import model.vehicles.Car;
import model.vehicles.MotorBike;

/**
 * @author hphobbs
 * @version 1.0 Winter 2019
 */

public class RentalManager {

    /** map containing the list of vehicles for rent. */
    private Map<Integer, AbstractVehicle> myVehicleList = new HashMap<>();

    /** Reference to the bill list. */
    private Map<Integer, Bill> myBills;

    /** Reference to registration Object. */
    private Registration myRegistration;
    
    /** Reference for the stars. */
    private final String myStars = "***********************";

    /** Teference for the Bill number. */
    private int myNextBillId = 1;

 

    /**
     * Initialize myRegistration object. Initialize myVehicleList
     * 
     * @param theRegistration object
     * @throws NullException for null registration objects
     */
    public RentalManager(final Registration theRegistration) {

        this.myRegistration = Objects.requireNonNull(theRegistration);
        generateInventory();

        myBills = new HashMap<>();
    }

    /** @return myVehicleList */
    public Map<Integer, AbstractVehicle> generateInventory() {

        final String m1 = "Mountain";

        final Car fiat = new Car("Fiat", "V100", true, false, false, false);

        final Car outback = new Car("Outback", "V101", true, true, true, false);

        final Car bmw = new Car("BMW", "V102", true, true, true, true);

        final MotorBike bike1 = new MotorBike("Bike1", "B100", true, false);

        final MotorBike bike2 = new MotorBike("Bike2", "B101", true, true);

        final Bicycle roadies = new Bicycle("Roadies", "C100", Bicycle.BicycleType.Road);

        final Bicycle cruiser = new Bicycle("Cruiser", "C101", Bicycle.BicycleType.Cruiser);

        final Bicycle mountain = new Bicycle(m1, "C102", Bicycle.BicycleType.Mountain);

        myVehicleList.put(fiat.getMyVehicleID(), fiat);

        myVehicleList.put(outback.getMyVehicleID(), outback);

        myVehicleList.put(bmw.getMyVehicleID(), bmw);

        myVehicleList.put(bike1.getMyVehicleID(), bike1);

        myVehicleList.put(bike2.getMyVehicleID(), bike2);

        myVehicleList.put(roadies.getMyVehicleID(), roadies);

        myVehicleList.put(cruiser.getMyVehicleID(), cruiser);

        myVehicleList.put(mountain.getMyVehicleID(), mountain);

        return myVehicleList;
    }

    /** @return the current list of vehicles */
    public Map<Integer, AbstractVehicle> getMyVehicleList() {
        return myVehicleList;
    }

    /** @return myRegistration */
    public Registration getMyRegistration() {
        return myRegistration;
    }

    /**
     * @param theVehicleID
     * @return true if the vehicle is a part of the inventory, myVehicleList and if it's not
     *         already rented.
     */
    public boolean isRentable(final int theVehicleID) {
        final AbstractVehicle vehicle = myVehicleList.get(theVehicleID);
        return vehicle != null && vehicle.getMyRentalStatus();
    }

    /**
     * @param theVehicleID
     * @param theUserName
     * @param theNumDays
     * @param theBillID
     * @return true
     */
    public boolean rent(final int theBillID, final String theUserName, final int theVehicleID,
                        final int theNumDays) {

        Objects.requireNonNull(theVehicleID);
        Objects.requireNonNull(theUserName);
        Objects.requireNonNull(theNumDays);
        Objects.requireNonNull(theBillID);

        if (theNumDays <= 0) {
            throw new IllegalArgumentException("Invalid number of days");
        }

        if (isRentable(theVehicleID)
            && myRegistration.getMyUserList().containsKey(theUserName)) {
            myVehicleList.get(theVehicleID).setMyRentalStatus(false);

            final Bill bill = new Bill(theBillID,
                                       myRegistration.getMyUserList().get(theUserName),
                                       myVehicleList.get(theVehicleID), theNumDays);

            myBills.put(bill.getBillID(), bill);

            System.out.println();
            printBill(System.out, theUserName, theVehicleID, bill);

            final DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
            try (PrintStream fileOut =
                            new PrintStream("resources/bill-" + df.format(new Date()) + "-"
                                            + theBillID + ".txt")) {
                printBill(fileOut, theUserName, theVehicleID, bill);
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            }

            return true;
        }

        return false;
    }

    /**
     * Print the bill in the file and in the console.
     * 
     * @param theOutput
     * @param theUserName
     * @param theVehicleID
     * @param theBill
     */
    private void printBill(final PrintStream theOutput, final String theUserName,
                           final int theVehicleID, final Bill theBill) {

        theOutput.println(myStars);
        theOutput.println(" Rental Bill Summary");
        theOutput.println(myStars);
        theOutput.println("User Name: " + theUserName);
        theOutput.println("----Vehicle Information----");
        theOutput.println("VehicleName " + myVehicleList.get(theVehicleID).getMyName());
        theOutput.println("VehicleID " + theVehicleID);
        theOutput.println("VehicleType "
                          + myVehicleList.get(theVehicleID).getClass().getSimpleName());

        theOutput.println("VIN " + myVehicleList.get(theVehicleID).getMyVIN());

        theBill.computeAndPrintAmount(theOutput);
    }

    /**
     * * Checks theVehicleID is valid and if is renatble by calling isRentable.
     * 
     * @param theVehicleID
     * @return if the user is allowed to return the vehicle
     */
    public boolean drop(final int theVehicleID) {
        final AbstractVehicle vehicle = myVehicleList.get(theVehicleID);
        if (vehicle == null) {
            System.out.println("Vehicle does not exists");
            return false;
        }
        if (vehicle.getMyRentalStatus()) {
            System.out.println("Vehicle is not rented already");
            return false;
        }
        vehicle.setMyRentalStatus(true);
        return true;
    }

    /**
     * From the inventory generated by generateInventory method it selects the available
     * vehicles and prints them.
     */
    public void printOptions() {
        boolean userContinue = true;

        while (userContinue) {
            final String s = "Enter 1, 2, 3, or 4 (1. Rent 2. Drop-off 3. Exit 4. Vehicles):";
            System.out.println(s);
            final int numbInput = Integer.parseInt(Registration.SCANNER.nextLine());
            System.out.println("You entered option " + numbInput);
            System.out.println();

            switch (numbInput) {
                case 1:
                    printRent();
                    break;
                case 2:
                    printDrop();
                    break;
                case 3:
                    userContinue = false;
                    break;
                case 4:
                    displayVehicles();
                    break;
                default:
                    System.out.println("That is not a valid option.");
            }

            if (userContinue) {
                System.out.print(" Do you want to continue?");

                final String answer = Registration.SCANNER.nextLine();
                if (!"true".equalsIgnoreCase(answer)) {
                    userContinue = false;
                }
            }
        }
    }

    /**
     * displays the vehicles (both available and non-available), sorted based on their rental
     * amount per day.
     */
    private void displayVehicles() {
        System.out.println("List of available vehicles by rental amount per day:");

        final List<AbstractVehicle> sortedVehicles = new ArrayList<>(myVehicleList.values());
        sortedVehicles.sort(Comparator.comparing(AbstractVehicle::calculateRentalAmount));
        for (AbstractVehicle vehicle : sortedVehicles) {
            final String str = vehicle + " - $" + vehicle.calculateRentalAmount() + " per day";
            System.out.println(str);
        }
    }

    private void printRent() {

        int vehicleID;
        String userName;
        int numDays;

        System.out.println(myStars + myStars);

        System.out.println("List of available vehicles:");
        for (AbstractVehicle vehicle : myVehicleList.values()) {
            System.out.println(vehicle);
        }

        boolean foundVehicle = false;
        do {
            System.out.println(myStars);
            System.out.println(" Enter Rental Details");
            System.out.println(myStars);

            System.out.print("Enter Vehicle ID:");
            vehicleID = Integer.parseInt(Registration.SCANNER.nextLine());

            System.out.print("Enter User Name:");
            userName = Registration.SCANNER.nextLine();

            System.out.print("Enter NumDays to Rent:");
            numDays = Integer.parseInt(Registration.SCANNER.nextLine());

            if (!isRentable(vehicleID)) {
                System.out.println("Vehicle not rentable");

            } else if (!myRegistration.getMyUserList().containsKey(userName)) {
                System.out.println("User does not exists, enter different user name:");

            } else {
                foundVehicle = true;
            }
        }
        while (!foundVehicle);

        if (rent(myNextBillId++, userName, vehicleID, numDays)) {
            System.out.println("Rent Successful");
            System.out.println(myStars);
        }

    }

    private void printDrop() {

        System.out.println(myStars);
        System.out.println("Enter Drop-off Details");
        System.out.println(myStars);

        boolean droppedVehicle = false;
        while (!droppedVehicle) {
            System.out.print("Enter Drop-off Vehicle ID:");
            final int dropOffID = Integer.parseInt(Registration.SCANNER.nextLine());
            if (drop(dropOffID)) {
                droppedVehicle = true;
                System.out.println("Drop-off Successfull");
            }
        }
        System.out.println(myStars);
    }

    /***/
    public void clearList() {

        myVehicleList.clear();
        myBills.clear();
    }

}
