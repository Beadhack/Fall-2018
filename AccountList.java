/** ***********************************************************************
 *      Patricia Renee Taylor
 *      CIST 2373
 *      Fall 2018
 *      Assignment #4
 *      9/26/18
 *
 * Part II Build an AccountList Business class.  This class will have a list
 * of Account Objects. You can use an Array or ArrayList to store this
 * list. Also has a count property to keep track of how many Accounts
 * are in the list. Methods should include a display() method, and 
 * and add(Account a1) method.  
 *
 ************************************************************************* */
package Business;

import java.sql.*;

public class AccountList {
    
//------------ Properties-------------
    public int count = 0;
    public Account aArr[] = new Account[10];
    
    public int getCount(){
        return count;
    }

//-------------addAcount() method---------
    public void addAccount(Account a) {
//    this is called from customer. In customer the db is querried,
//    pulling all accounts with same custID. In while loop rs.next
//    gets account info and puts in object a. Then that object is passed
//    as paremeter to addAccount which is added to aArr[].
//    aArr[] is an array of accounts for an individual customer.
       
        aArr[count] = a;
        count++;
        
    }//end addAccount() method  
    
//-------------Display Method-----------
    public void display() {
    
        for (int x = 0; x<count; x++){
            System.out.println(aArr[x]);
        }
    }//end display()
    
}//end class

