package pawsnfins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*******************************************************************************
 *  Caitlin Driver
 *  Java Project
 *  OrderItems Class
 ******************************************************************************/
public class OrderItems {
    //Create class properties
    private int orderID;
    private int itemID;
    private int[] itemArray;
    private final String url = "jdbc:ucanaccess://C:\\Users\\dihog\\Desktop\\Project\\PetStore1.accdb";
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    
    //Create constructors
    public OrderItems() {
        //initialize properties
        orderID = 0;
        itemID = 0;
    }
    public OrderItems(int order, int item) {
        //set incoming data to class properties
        orderID = order;
        itemID = item;
    }
    
    //Create get and set methods for properties
    public void setOrderID(int id) {
        orderID = id;
    }
    public int getOrderID() {
        return orderID;
    }
    public void setItemID(int id) {
        itemID = id;
    }
    public int getItemID() {
        return itemID;
    }
    public int[] getItemArray() {
        return itemArray;
    }
    
    /***************************************************************************
     * Display method for showing the class properties and their values.
     **************************************************************************/
    public void display(){
        //create loop to display all items in an order
        System.out.println("Order ID: " + orderID);
        for(int x = 0; x < itemArray.length; x++) {
            System.out.println("Item ID : " + itemArray[x]);
        }
        System.out.println("===================================");
    }
    
    /***************************************************************************
     *  Select database method that communicates with the database and selects a
     *  specific order.
     **************************************************************************/
    public void selectDB(int id) {
        //connect incoming variable to class variable
        orderID = id;
        try {
            //load the driver
            Class.forName(driver);
            
            //Establish a connection
            Connection selectConnect = DriverManager.getConnection(url);
            System.out.println("SelectDB Connected");
            
            //Create statement
            Statement selectStatement = selectConnect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            //execute statement
            String sql = "SELECT itemID FROM OrderItems WHERE orderID = '"+orderID+"'";
            System.out.println(sql);

            ResultSet results = selectStatement.executeQuery(sql);
            results.last(); //grab all data from order id
            int size = results.getRow();
            System.out.println("Size is " + size);
            results.beforeFirst();
            itemArray = new int[size];
            
            for(int x = 0; x < itemArray.length; x++) {
                results.next();
                itemArray[x] = results.getInt("itemID");
            }

            //close connection to database
            selectConnect.close();
        }
        catch(Exception e) { //catch exception
            System.out.println("Select Database Error: " + e);
        }
    }//end selectdb
    
    /***************************************************************************
     * Insert database method to insert a new order into the database.
     **************************************************************************/
    public void insertDB(int order, int item) {
        try{
            //load the driver
            Class.forName(driver);
            
             //Establish connection
            Connection insertConnect = DriverManager.getConnection(url);
            System.out.println("Database connected");
           
            //create statement
            Statement insertStatement = insertConnect.createStatement();
           
            //execute statement
            String sql = "INSERT INTO OrderItems (orderID,itemID) VALUES ('"+order+"','"+item+"')";
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
     * Delete database method that deletes an order from the database.
     **************************************************************************/
    public void deleteDB(int order) {
        try{
            //load the driver
            Class.forName(driver);
            
            //Get connected
            Connection deleteConnect = DriverManager.getConnection(url);
            System.out.println("Connected to database within delete method");
            
            //create statement
            Statement deleteStatement = deleteConnect.createStatement();
            
            //execute statement
            String sql = "DELETE FROM OrderItems WHERE orderID = '"+order+"'";
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
     * Update database method that updates a current order in the database.
     **************************************************************************/
    public void updateDB(int order, int item, int oldID){
        try{
            //load the driver
            Class.forName(driver);
            
            //Get connected
            Connection connect = DriverManager.getConnection(url);
            System.out.println("Connected to database within update method");
            
            //create statement
            Statement statement = connect.createStatement();
            
            //execute statement
            String sql = "UPDATE OrderItems SET itemID = '"+item+"' WHERE orderID = '"+order+"' AND itemID = '"+oldID+"'";
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
        //Create a orderitems object
        OrderItems oi = new OrderItems();
        
        //oi.selectDB(1);
        //oi.display();
        
        //oi.insertDB(2, 65);
        //oi.selectDB(2);
        //oi.display();
        
        oi.updateDB(1, 51, 46);
        oi.selectDB(1);
        oi.display();
        
        //oi.deleteDB(2);
        
        /********************************
        *OrderItems class has been tested
        *********************************/
    }
    
}
