package pawsnfins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

/***********************************************************
 *  Caitlin Driver
 *  Java Project
 *  Customer Class
 ***********************************************************/
public class Customer {
    
    //Create private variables
    private int custID;
    private String custName;
    private String custAddress;
    private String custPhone;
    private String custEmail;
    private String username;
    private String password;
    private String cardNumber;
    private String cardExp;
    
    private final String url = "jdbc:ucanaccess://C:\\Users\\dihog\\Desktop\\Project\\PetStore1.accdb";
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    
    //Create constructors
    public Customer() {
        //initialize class variables
        custID = 0;
        custName = "";
        custAddress = "";
        custPhone = "";
        custEmail = "";
        username = "";
        password = "";
        cardNumber = "";
        cardExp = "";
    }
    
    public Customer(int id, String name, String address, String phone, String email, String un, String pw, String card, String exp) {
        //assign incoming variables to class variables
        custID = id;
        custName = name;
        custAddress = address;
        custPhone = phone;
        custEmail = email;
        username = un;
        password = pw;
        cardNumber = card;
        cardExp = exp;
    }
    
    //Create get and set methods to handle individual class variables
    public void setCustID(int id) {
        custID = id;
    }
    public int getCustID() {
        return custID;
    }
    public void setCustName(String name) {
        custName = name;
    }
    public String getCustName() {
        return custName;
    }
    public void setCustAddress(String add) {
        custAddress = add;
    }
    public String getCustAddress() {
        return custAddress;
    }
    public void setCustPhone(String phone) {
        custPhone = phone;
    }
    public String getCustPhone() {
        return custPhone;
    }
    public void setCustEmail(String email) {
        custEmail = email;
    }
    public String getCustEmail() {
        return custEmail;
    }
    public void setCustUser(String un) {
        username = un;
    }
    public String getCustUser() {
        return username;
    }
    public void setCustPW(String pw) {
        password = pw;
    }
    public String getCustPW() {
        return password;
    }
    public void setCardNumber(String cn) {
        cardNumber = cn;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setExpiration(String exp) {
        cardExp = exp;
    }
    public String getExpiration() {
        return cardExp;
    }
    
     /***************************************************************************
     * Display method for showing the class properties and their values.
     **************************************************************************/
    public void display(){
        System.out.println("CustID         : " + custID);
        System.out.println("Customer Name  : " + custName);
        System.out.println("Address        : " + custAddress);
        System.out.println("Phone          : " + custPhone);
        System.out.println("Email          : " + custEmail);
        System.out.println("Username       : " + username);
        System.out.println("Password       : " + password);
        System.out.println("Card Number    : " + cardNumber);
        System.out.println("Card Expiration: " + cardExp);
        System.out.println("===================================");
    }
    
    /***************************************************************************
     *  Select database method that communicates with the database and selects a
     *  specific course.
     **************************************************************************/
    public void selectDB(int id) {
        
        try {
            //load the driver
            Class.forName(driver);
            
            //Establish a connection
            Connection selectConnect = DriverManager.getConnection(url);
            System.out.println("SelectDB Connected");
            
            //Create statement
            Statement selectStatement = selectConnect.createStatement();
            
            //execute statement
            String sql = "SELECT * FROM Customer WHERE custID = '"+id+"'";
            System.out.println(sql);

            ResultSet results = selectStatement.executeQuery(sql);
            results.next(); //grab all data from customer id
               custID = results.getInt(1);
               custName = results.getString(2);
               custAddress = results.getString(3);
               custPhone = results.getString(4);
               custEmail = results.getString(5);
               username = results.getString(6);
               password = results.getString(7);
               cardNumber = results.getString(8);
               cardExp = results.getString(9);

            //close connection to database
            selectConnect.close();
        }
        catch(Exception e) { //catch exception
            System.out.println("Select Database Error: " + e);
        }
    }//end selectdb
    
    /***************************************************************************
     * Insert database method to insert a new course into the database.
     **************************************************************************/
    public void insertDB(int id, String name, String add, String phone, String email, String un, String pw, String card, String exp) {
        try{
            //load the driver
            Class.forName(driver);
            
             //Establish connection
            Connection insertConnect = DriverManager.getConnection(url);
            System.out.println("Database connected");
           
            //create statement
            Statement insertStatement = insertConnect.createStatement();
           
            //execute statement
            String sql = "INSERT INTO Customer (custID,custName,custAddress,custPhone,custEmail,username,password,cardNumber,cardExp) VALUES ('"+id+"','"+name+"','"+add+"','"+phone+"','"+email+"','"+un+"','"+pw+"','"+card+"','"+exp+"')";
            System.out.println(sql);
            
            int x = insertStatement.executeUpdate(sql);
            if(x>0){
                System.out.println("Database updated!");
            }
            else{
                System.out.println("Database not updated.");
            }
            
            //close connection
            insertConnect.close();
        }
        catch(Exception e){
            System.out.println("InsertDB Error: " + e);
        }
    }//end insertdb
    
    /***************************************************************************
     * Delete database method that deletes a course from the database.
     **************************************************************************/
    public void deleteDB(int id) {
        try{
            //load the driver
            Class.forName(driver);
            
            //Get connected
            Connection deleteConnect = DriverManager.getConnection(url);
            System.out.println("Connected to database within delete method");
            
            //create statement
            Statement deleteStatement = deleteConnect.createStatement();
            
            //execute statement
            String sql = "DELETE FROM Customer WHERE custID = '"+id+"'";
            System.out.println(sql);
            
            int z = deleteStatement.executeUpdate(sql);
            if (z>0){
                System.out.println("Database deleted");
            }
            else{
                System.out.println("Database not deleted");
            }
            
            //close connection
            deleteConnect.close();
        }
        catch(Exception e){
            System.out.println("Error within deleteDB: " + e);
        }
    }//end deletedb
    
    /***************************************************************************
     * Update database method that updates a current course in the database.
     **************************************************************************/
    public void updateDB(int id, String name, String add, String phone, String email, String un, String pw, String card, String exp){
        try{
            //load the driver
            Class.forName(driver);
            
            //Get connected
            Connection connect = DriverManager.getConnection(url);
            System.out.println("Connected to database within update method");
            
            //create statement
            Statement statement = connect.createStatement();
            
            //execute statement
            String sql = "UPDATE Customer SET custName = '"+name+"' , custAddress = '"+add+"' , custPhone = '"+phone+"' , custEmail = '"+email+"' , username = '"+un+"' , password = '"+pw+"' , cardNumber = '"+card+"' , cardExp = '"+exp+"' WHERE custID = '"+id+"'";
            System.out.println(sql);
            
            int y = statement.executeUpdate(sql);
            if (y>0){
                System.out.println("Database updated");
            }
            else{
                System.out.println("Database not updated");
            }
            
            //close connection
            connect.close();
        }
        catch(Exception e){
            System.out.println("Error within updateDB: " + e);
        }
        
    }//end of updateDB method
    
    /***************************************************************************
     * Main method to use for testing the courses class.
     **************************************************************************/
    public static void main(String[]args) {
       //Create a customer object
        Customer cust1 = new Customer();
        
        //cust1.insertDB(100, "Your Mama", "add", "phone", "email", "un", "pw", "2568-2222-4444-5555", "exp");
        //cust1.updateDB(7, "FACE", "3 years", "phone", "email.com", "HELLO", "password", "cardNum", "exp");
        cust1.selectDB(1);
        cust1.display();
        //cust1.deleteDB(7);
        
        //YAY everything works and is ready to go
    }
    
}
