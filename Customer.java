/** ***********************************************************************
 *      Patricia Renee Taylor
 *      CIST 2373
 *      Fall 2018
 *      Assignment #3
 *      9/18/18
 *
 * Part 1 - Build a Customer Business Object. class will have 6 properties;
 * custId, CustPassword, CustfirstName, CustLastName, CustAddress, and CustEmail.
 * Build empty constructor in the Customer class that initializes all properties
 * to zero or blank. Build a full constructor also. Build DB methods
 * selectDB(), insertDB(), deleteDB() and updateDB().
 * Check login id/pw is valid
 *
 ************************************************************************* */
package Business;

import java.sql.*;

public class Customer {

    Statement stmt;// make class variables so can put connection into method
    Connection con;
//------------ Properties-------------
    private String CustID;
    private String CustPassword;
    private String CustFirstName;
    private String CustLastName;
    private String CustAddress;
    private String CustEmail;
    public AccountList alist;

//-------------Constructors----------------
    //Creating a constructor accepting all fields on instantiation.
    public Customer(String CustID, String CustPassword, String CustFirstName, 
            String CustLastName, String CustAddress, String CustEmail){
             
        setCustID(CustID);
        setCustPassword(CustPassword);
        setCustFirstName(CustFirstName);
        setCustLastName(CustLastName);
        setCustAddress(CustAddress);
        setCustEmail(CustEmail);
        }
        //Constructor which sets values to empty strings.
    public Customer() {
    
        setCustID("");
        setCustPassword("");
        setCustFirstName("");
        setCustLastName("");
        setCustAddress("");
        setCustEmail("");
        }
        //-------------Setters and Getters---------

    public void setCustID(String CustID) {
        this.CustID = CustID;
    }
    public String getCustID() {
        return CustID;
    }
    public void setCustPassword(String CustPassword) {
        this.CustPassword = CustPassword;
    }
    public String getCustPassword() {
        return CustPassword;
    }
    public void setCustFirstName(String CustFirstName) {
        this.CustFirstName = CustFirstName;
    }
    public String getCustFirstName() {
        return CustFirstName;
    }
    public void setCustLastName(String CustLastName) {
        this.CustLastName = CustLastName;
    }
    public String getCustLastName() {
        return CustLastName;
    }
    public void setCustAddress(String CustAddress) {
        this.CustAddress = CustAddress;
    }
    public String getCustAddress() {
        return CustAddress;
    }
    public void setCustEmail(String CustEmail) {
        this.CustEmail = CustEmail;
    }
    public String getCustEmail() {
        return CustEmail;
    }

//-------------selectDB method---------
    public void selectDB(String CustID) {
        try {
            openConnection();

            String sql;
            sql = "select * from Customers where CustID = '" + CustID + "'";
            System.out.println("Customer;selectDB(): "+sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            rs.next();
            setCustID(rs.getString(1));
            setCustPassword(rs.getString(2));
            setCustFirstName(rs.getString(3));
            setCustLastName(rs.getString(4));
            setCustAddress(rs.getString(5));
            setCustEmail(rs.getString(6));

            con.close();

        } catch (Exception e) {
            System.out.println("pp: " + e);
        }
        getAccounts();
    }//end selectDB() method  
    
    //-------------getAccounts method---------
    public void getAccounts() {
        try {
            openConnection();

            String sql;
            ResultSet rs;
            String an;
            Account a1;
            alist = new AccountList();
            sql = "select AcctNo from Accounts where Cid = '" + getCustID() + "'";
            System.out.println("Customer;getAccounts(): "+sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                an = rs.getString(1);
                a1 = new Account();
                a1.selectDB(an);
                alist.addAccount(a1); //adds this account to aArr[] in AccountList
            }
            con.close();

        } catch (Exception e) {
            System.out.println("pp: " + e);
        }
    }//end selectDB() method  

    //-------------insertDB method---------
    public void insertDB(String CustID, String CustPassword, String CustFirstName, 
            String CustLastName, String CustAddress, String CustEmail) {
        try {
            System.out.println("Starting insertDB............");
            openConnection();
            String sql;
            sql = "insert into Customers values('" + CustID + "','"
                    + CustPassword + "','"
                    + CustFirstName + "','"
                    + CustLastName + "','"
                    + CustAddress + "','"
                    + CustEmail + "');";
            System.out.println(sql);
            int resultFlag = stmt.executeUpdate(sql);

            if (resultFlag == 1) {
                System.out.println("Insert was successful.");
            } else {
                System.out.println("Insert was NOT successful.");
            }

            con.close();

        } catch (Exception e) {
            System.out.println("pp: " + e);
        }
    }//end insertDB() method

//-------------Delete Method------------
    public void deleteDB() {
        try {
            System.out.println("Starting deleteDB............");
            openConnection();
            String sql;
            sql = "delete from Customers where CustID = '" + CustID + "'";
            System.out.println(sql);
            int resultFlag = stmt.executeUpdate(sql);

            if (resultFlag == 1) {
                System.out.println("Delete was successful.");
            } else {
                System.out.println("Delete was NOT successful.");
            }

            con.close();

        } catch (Exception e) {
            System.out.println("pp: " + e);
        }
    }//end deleteDB() method

    //-------------updateDB method---------
    public void updateDB() {
        try {
            System.out.println("Starting updateDB............");
            openConnection();
            String sql;
            sql = "update Customers set CustID = '" + getCustID() + "','"
                    + "CustPassword  = '" + getCustPassword() + "', '"
                    + "CustFirstName = '" + getCustFirstName() + "', '"
                    + "CustLastName  = '" + getCustLastName() + "', '"
                    + "CustAddress   = '" + getCustAddress() + "', '"
                    + "CustEmail     = '" + getCustEmail() + "';";
                   
            System.out.println(sql);
            int resultFlag = stmt.executeUpdate(sql);

            if (resultFlag == 1) {
                System.out.println("Insert was successful.");
            } else {
                System.out.println("Insert was NOT successful.");
            }

            con.close();

        } catch (Exception e) {
            System.out.println("pp: " + e);
        }
    }//end updateDB() method

//-------------Display Method-----------
    public void display() {
        System.out.println("Customer ID: " + CustID);
        System.out.println("Customer PW: " + CustPassword);
        System.out.println("First Name:  " + CustFirstName);
        System.out.println("Last Name:  " + CustLastName);
        System.out.println("Address:    " + CustAddress);
        System.out.println("Email:      " + CustEmail);
        System.out.println(" ");
        alist.display();
    }//end display()

//-------------Connects to DB-----------
    public void openConnection() {
        try {
            String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
            Class.forName(driver);
            System.out.println("Starting to open the database");
        
            con =
                //DriverManager.getConnection("jdbc:ucanaccess://C:/Use"
                //      + "rs/ptaylo14/Desktop/Java/Lab5/RegistrationDB.mdb");
                DriverManager.getConnection("jdbc:ucanaccess://C:/Users/"
                        + "prtaylor/Desktop/ChattBankMDB.mdb");

            stmt = con.createStatement();
        }catch (Exception e) {
            System.out.println(">>>>> exception caught when opening database:");
            System.out.println(e);
        }
    }//close openConnection
    
//-------------Check if login is valid-----------
    public boolean verifyLogin(String ID,String enteredPW) {
        
        Customer cCheck;            //create object to check login credentials
        cCheck = new Customer();
        cCheck.selectDB(ID);        //load cCheck object with info from user ID
        String passFromDB = cCheck.getCustPassword();
        
        if (enteredPW.equals(passFromDB)){ //compare entered pw against database
            return true;
        }else {
            return false;
        }
    }
    
//-------------Main Method for Testing----	
    public static void main(String args[]) {
        
//-------------Testing Constructors-------------        
        //Customer c1 =  new Customer("3001","1234","John","doe","atlanta","A@aol.com");
        //c1.selectDB("3001");
        //c1.display();
       
//-------------Testing SelectDB-----------------  
        Customer c2;
        c2 = new Customer();
        c2.selectDB("3001");
        c2.display();

//-------------Testing InsertDB----------------- 
        //Customer c3;
        //c3 = new Customer();
        //c3.insertDB("3066","126*","Jon","de","atlanta","jd@aol.com");
 
//-------------Testing UpdateDB----------------- 
        //Customer c4;
        //c4 = new Customer();
        //c4.selectDB("3006");
        //c4.setCustEmail("Jon@gmail.com");
        //c4.setCustAddress("Macon");
        //c4.updateDB();
        //c4.display();

//-------------Testing DeletetDB-----------------         
        //Customer c5;
        //c5 = new Customer();
        //c5.selectDB("3066");
        //c5.deleteDB();
        
//-------------testing login-------------------
//        Customer c6;
//        c6 = new Customer();
//        c6.selectDB("3002");
//        String enteredPW = "test";
//        String id;
//        
//        System.out.println(c6.verifyLogin("3001", "1234"));

               
        
    }//end main
}//end class

