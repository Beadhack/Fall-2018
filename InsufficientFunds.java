/**********************************************************************
 * When the withdraw method attempts to set the balance below zero,
 * the withdraw() method is called, and the amount the user is trying to 
 * withdraw is greater than the available balance, 
 * the InsufficientFundsException will be thrown.
 *
 * In the Account class's main method, use try-catch blocks to catch
 * this InsufficientFundsException when you call the withdraw() method.
 *
 *********************************************************************/
package Business;

/**
 *
 * @author prtaylor
 */
public class InsufficientFunds extends Exception {

    public String msg = "   >>>***>>>   Insuffecient Funds";

    public String toString() {
        return msg;
    }
}//end class

