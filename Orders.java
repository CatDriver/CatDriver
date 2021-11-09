package pawsnfins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*******************************************************************************
 *  Caitlin Driver
 *  Java Project
 *  Orders Class
 ******************************************************************************/
public class Orders {
    
    //Create private class properties
    private int orderID;
    private int custID;
    private double orderTotal;
    private String orderStatus;
    private final String url = "jdbc:ucanaccess://C:\\Users\\dihog\\Desktop\\Project\\PetStore1.accdb";
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    
    //Create constructors
    public Orders() {
        //initialize properties
        orderID = 0;
        custID = 0;
        orderTotal = 0;
        orderStatus = "";
    }
    public Orders(int order, int customer, double total, String status) {
        //Set incoming data to class properties
        orderID = order;
        custID = customer;
        orderTotal = total;
        orderStatus = status;
    }
    
    //Create get and set methods for each property
    public void setOrderID(int id) {
        orderID = id;
    }
    public int getOrderID() {
        return orderID;
    }
    public void setCustID(int id) {
        custID = id;
    }
    public int getCustID() {
        return custID;
    }
    public void setTotal(double total) {
        orderTotal = total;
    }
    public double getTotal() {
        return orderTotal;
    }
    public void setStatus(String status) {
        orderStatus = status;
    }
    public String getStatus() {
        return orderStatus;
    }
    
     /***************************************************************************
     * Display method for showing the class properties and their values.
     **************************************************************************/
    public void display(){
        System.out.println("OrderID     : " + orderID);
        System.out.println("Customer ID : " + custID);
        System.out.println("Total       : " + orderTotal);
        System.out.println("Order Status: " + orderStatus);
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
            String sql = "SELECT * FROM Orders WHERE orderID = '"+id+"'";
            System.out.println(sql);

            ResultSet results = selectStatement.executeQuery(sql);
            results.next(); //grab all data from customer id
               orderID = results.getInt("orderID");
               custID = results.getInt("custID");
               orderTotal = results.getDouble("orderTotal");
               orderStatus = results.getString("orderStatus");

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
    public void insertDB(int id, int customer, double total, String status) {
        try{
            //load the driver
            Class.forName(driver);
            
             //Establish connection
            Connection insertConnect = DriverManager.getConnection(url);
            System.out.println("Database connected");
           
            //create statement
            Statement insertStatement = insertConnect.createStatement();
           
            //execute statement
            String sql = "INSERT INTO Orders (orderID,custID,orderTotal,orderStatus) VALUES ('"+id+"','"+customer+"','"+total+"','"+status+"')";
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
            String sql = "DELETE FROM Orders WHERE orderID = '"+id+"'";
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
    public void updateDB(int id, int customer, double total, String status){
        try{
            //load the driver
            Class.forName(driver);
            
            //Get connected
            Connection connect = DriverManager.getConnection(url);
            System.out.println("Connected to database within update method");
            
            //create statement
            Statement statement = connect.createStatement();
            
            //execute statement
            String sql = "UPDATE Orders SET custID = '"+customer+"' , orderTotal = '"+total+"' , orderStatus  = '"+status+"'";
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
        //Create a order object
        Orders myOrder = new Orders();
        
        //myOrder.selectDB(2);
        //myOrder.display();
        //myOrder.insertDB(0, 6, 82.25, "Open");
        //myOrder.selectDB(3);
        //myOrder.display();
        //myOrder.updateDB(3, 4, 12.50, "status");
        //myOrder.selectDB(3);
        //myOrder.display();
        //myOrder.deleteDB(3);
        
        //Yay Orders is ready to go
    }
}
