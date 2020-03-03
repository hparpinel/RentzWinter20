/*
 * for the Vehicle Rental System TCSS 305 - Rentz
 */

package model;

import java.util.Objects;

/**
 * Represents a single user for registration or sign-in. User is an immutable object.
 * 
 * Constructors and methods of this class throw NullPointerException if required parameters are
 * null.
 * 
 * @author hphobbs
 * @version Winter 2020
 */
public final class User {

    /** Represent the user's user name. */
    private String myName;

    /** Represent the user's password. */
    private String myPassword;

    /** Represent the user's VIP status. */
    private boolean myVIPStatus;

    /**
     * This parameterized constructor is called when an User object is created only with user
     * name and password. VIPStatus default value is false
     * 
     * @param theName entered user name
     * @param thePassword entered password
     */
    public User(final String theName, final String thePassword) {
        this(theName, thePassword, false); // call into the next constructor to use the logic
    }

    /**
     * Parameterized constructor with user name, password and VIPStatus. It throws exception is
     * name or password is empty
     * 
     * @param theName entered user name
     * @param thePassword entered password
     * @param theVIPStatus entered VIP status
     * @throws NullPointerException for null parameters
     * @throws IllegalArgumentException();
     */
    public User(final String theName, final String thePassword, final Boolean theVIPStatus) {

        myName = Objects.requireNonNull(theName);
        myPassword = Objects.requireNonNull(thePassword);
        myVIPStatus = Objects.requireNonNull(theVIPStatus);

        if (theName.isEmpty() || thePassword.isEmpty()) { // isEmpty(): returns true if the
                                                          // string length is 0
            throw new IllegalArgumentException();
        }
    }

    /** @return the user name. */
    public String getMyName() {
        return myName;
    }

    /** @return the password. */
    public String getMyPassword() {
        return myPassword;
    }

    /** @return the VIPStatus. */
    public Boolean getMyVIPStatus() {
        return myVIPStatus;
    }

    @Override
    /**
     * @return String representation of user object
     */
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        final String comma = ", ";

        sb.append(getClass().getSimpleName());
        sb.append("(");
        sb.append(this.getMyName());
        sb.append(comma);
        sb.append(this.getMyPassword());
        sb.append(comma);
        sb.append(this.getMyVIPStatus());
        sb.append(")");

        return sb.toString();
    }

    @Override
    /**
     * check the state of the two objects to see if they're equal return true if they match,
     * false if they don't
     * 
     * @param theOtherObject another user
     * @return if user is equal to other user
     */
    public boolean equals(final Object theOtherObject) { // the parameter cannot be other than
        // object type
        // one return statement instead of three
        boolean result = false;
        if (this == theOtherObject) {
            result = true;
        } else if (theOtherObject == null) {
            result = false;
        } else if (this.getClass() != theOtherObject.getClass()) {
            result = false;
        } else {
            final User other = (User) theOtherObject;
            result = Objects.equals(myName, other.myName)
                     && Objects.equals(myPassword, other.myPassword)
                     && Objects.equals(myVIPStatus, other.myVIPStatus);
        }
        return result;
    }

    /**
     * Create the hash code to get the user name, password and VIPStatus.
     * 
     * @return hash code of the user
     */
    @Override
    public int hashCode() {
        return Objects.hash(myName, myPassword, myVIPStatus);

    }
}
