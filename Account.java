/** ***********************************************************************
 *      Patricia Renee Taylor
 *      CIST 2373
 *      Fall 2018
 *      Assignment #3
 *      9/18/18
 *
 *Part II Build an Account Business Object.  This class will have all of  
 * the database access code to lookup an Account in the database. Build a 
 * selectDB(acctNo) method in the Account class that accepts an integer acctNo. 
 * This method will then go to the database and find all information about 
 * the Account requested. Then, in the next lab, when our Servlet wants to 
 * find an Account in the DB, all we have to do is 
 * 
 * 		Account a1 = new Account();
 * 		a1.selectDB(acctNo);
 * 		abalance = a1.getBalance();
 *  
 *
 ************************************************************************* */
package Business;

import java.sql.*;
import java.sql.DriverManager;

public class Account {

    Statement stmt;// make class variables so can put connection into method
    Connection con;
//------------ Properties-------------
    private String AcctNo;
    private String Cid;
    private String Type;
    private double Balance;

//-------------Constructors----------------
    //Creating a constructor accepting all fields on instantiation.
    public Account(String AcctNo, String Cid, String Type, 
            double Balance){
             
        setAcctNo(AcctNo);
        setCid(Cid);
        setType(Type);
        setBalance(Balance);
        }
        //Constructor which sets values to empty strings.
    public Account() {
    
        setAcctNo("");
        setCid("");
        setType("");
        setBalance(0.0);
        }
        //-------------Setters and Getters---------

    public void setAcctNo(String AcctNo) {
        this.AcctNo = AcctNo;
    }
    public String getAcctNo() {
        return AcctNo;
    }
    public void setCid(String Cid) {
        this.Cid = Cid;
    }
    public String getCid() {
        return Cid;
    }
    public void setType(String Type) {
        this.Type = Type;
    }
    public String getType() {
        return Type;
    }
    public void setBalance(double Balance) {
        this.Balance = Balance;
    }
    public double getBalance() {
        return Balance;
    }

//-------------selectDB method---------
    public void selectDB(String AcctNo) {
        try {
            openConnection();

            String sql;
            sql = "select * from Accounts where AcctNo = '" + AcctNo + "'";
            System.out.println("Account;selectDB(): "+sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            rs.next();
            setAcctNo(rs.getString(1));
            setCid(rs.getString(2));
            setType(rs.getString(3));
            setBalance(rs.getDouble(4));

            con.close();

        } catch (Exception e) {
            System.out.println("pp: " + e);
        }
    }//end selectDB() method  
    
//-------------updateDB method----------
    public void updateDB(){
       try{
           System.out.println("Starting Account updateDB");
           openConnection();
           System.out.println("acctNo: "+getAcctNo()+" cid: "+getCid()+" type: "+getType()+" balance: "+getBalance());
           String sql;
           sql = "update Accounts set AcctNo = '" +getAcctNo()+"',"
                   +"Cid = '" +getCid()+"',"
                   +"Type = '" +getType()+"',"
                   +"Balance = " +getBalance()+" "
                   +"where Acctno = '" +getAcctNo()+"'";
           System.out.println(sql);
           int resultFlag = stmt.executeUpdate(sql);
           if (resultFlag == 1){
               System.out.println("Account-updateDB insert was successful. Huzza!");
           }else{
               System.out.println("Account-updateDB insert was NOT succesful. ");
           }
           
           con.close();
       } catch (Exception e){
           System.out.println("Exception from Account, updateDB(): "+e);
       }
        
    }//end updateDB method
 
//-------------deposit method---------
    public void deposit(double depAmt) {
        
        setBalance( getBalance() + depAmt );
    }//end deposit() method  
 
//-------------withdrawl method---------
    public void withdraw(double withdrawAmt) throws InsufficientFunds {
    
        if (withdrawAmt > getBalance()) {
            throw new InsufficientFunds();
        } else {
            setBalance( getBalance() - withdrawAmt );
        }
    }//end withdraw() method 
    
//-------------Display Method-----------
    public void display() {
        System.out.println("AcctNo: " + AcctNo);
        System.out.println("Cid: " + Cid);
        System.out.println("Type:  " + Type);
        System.out.println("Balance:  " + Balance);
        System.out.println(" ");
    }//end display()

//-------------toString Method-----------
    public String toString() {
        String str = "AcctNo: " + AcctNo + "  Cid: " + Cid 
                + "  Type:  " + Type + "  Balance:  " + Balance;
        return str;
    }//end toString()
    
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
    
//-------------Main Method for Testing----	
    public static void main(String args[]) {
        
//-------------Testing Constructors-------------        
        //Account a1;
        //a1 = new Account("90088","3099","SAV",9352.50);
        //a1.display();
        //System.out.println(a1);
       
//-------------Testing updateDB-----------------  
        Account a2;
        a2 = new Account();
        a2.selectDB("90010");
        a2.display();
        System.out.println("-----deposit 10,000");
        a2.deposit(10000.00);
        System.out.println(a2);
        a2.updateDB();
//        System.out.println("=====withdraw 19,000");
//        try {
//            a2.withdraw(89000.00);
//        } catch (InsufficientFunds e) {
//            System.out.println(e);
//        }
//        a2.display();
        

    }//end main
}//end class

