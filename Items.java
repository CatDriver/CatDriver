package pawsnfins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import net.ucanaccess.complex.Attachment;

/******************************************************************************
 *  Caitlin Driver
 *  Java Project
 *  Items Class
 *****************************************************************************/
public class Items {
    //create class variables
    private int itemID;
    private String itemName;
    private String itemDesc;
    private String itemSize;
    private int quantity;
    private double itemPrice;
    private String petType;
    private String itemType;
    private Object obj;
    private final String url = "jdbc:ucanaccess://C:\\Users\\dihog\\Desktop\\Project\\PetStore1.accdb";
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    
    //Create constructors
    public Items() {
        //initializes class variables
        itemID = 0;
        itemName = "";
        itemDesc = "";
        itemSize = "";
        quantity = 0;
        itemPrice = 0;
        petType = "";
        itemType = "";
    }
    
    public Items(int id, String name, String desc, String _size, int stock, double price, String pet, String item) {
        //assign incoming data to class variables
        itemID = id;
        itemName = name;
        itemDesc = desc;
        itemSize = _size;
        quantity = stock;
        itemPrice = price;
        petType = pet;
        itemType = item;
    }
    
    //Create get and set methods to handle individual class variables
    public void setID(int id) {
        itemID = id;
    }
    public int getID() {
        return itemID;
    }
    public void setName(String name) {
        itemName = name;
    }
    public String getName() {
        return itemName;
    }
    public void setDescription(String desc) {
        itemDesc = desc;
    }
    public String getDescription() {
        return itemDesc;
    }
    public void setSize(String size) {
        itemSize = size;
    }
    public String getSize() {
        return itemSize;
    }
    public void setStockAmount(int stock) {
        quantity = stock;
    }
    public int getStockAmount() {
        return quantity;
    }
    public void setPrice(double price) {
        itemPrice = price;
    }
    public double getPrice() {
        return itemPrice;
    }
    public void setPetType(String pet) {
        petType = pet;
    }
    public String getPetType() {
        return petType;
    }
    public void setItemType(String item) {
        itemType = item;
    }
    public String getItemType() {
        return itemType;
    }
    
     /***************************************************************************
     * Display method for showing the class properties and their values.
     **************************************************************************/
    public void display(){
        System.out.println("Item ID    : " + itemID);
        System.out.println("Item Name  : " + itemName);
        System.out.println("Description: " + itemDesc);
        System.out.println("Size       : " + itemSize);
        System.out.println("Stock      : " + quantity);
        System.out.println("Price      : " + itemPrice);
        System.out.println("For Pet    : " + petType);
        System.out.println("Item Type  : " + itemType);
        System.out.println("Picture    : " + obj);
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
            String sql = "SELECT * FROM Items WHERE itemID = '"+id+"'";
            System.out.println(sql);

            ResultSet results = selectStatement.executeQuery(sql);
            results.next(); //grab all data from customer id
               itemID = results.getInt("itemID");
               itemName = results.getString("itemName");
               itemDesc = results.getString("itemDescription");
               itemSize = results.getString("size");
               quantity = results.getInt("quantity");
               itemPrice = results.getDouble("pricePerItem");
               petType = results.getString("petType");
               itemType = results.getString("itemType");
               //Attachment[] attach = (Attachment[]) results.getObject
               obj = results.getObject(9);

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
    public void insertDB(int id, String name, String desc, String size, int stock, double price, String pet, String item) {
        try{
            //load the driver
            Class.forName(driver);
            
             //Establish connection
            Connection insertConnect = DriverManager.getConnection(url);
            System.out.println("Database connected");
           
            //create statement
            Statement insertStatement = insertConnect.createStatement();
           
            //execute statement
            String sql = "INSERT INTO Items (itemID,itemName,itemDescription,size,quantity,pricePerItem,petType,itemType) VALUES ('"+id+"','"+name+"','"+desc+"','"+size+"','"+stock+"','"+price+"','"+pet+"','"+item+"')";
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
            String sql = "DELETE FROM Items WHERE itemID = '"+id+"'";
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
    public void updateDB(int id, String name, String desc, String size, int stock, double price, String pet, String item){
        try{
            //load the driver
            Class.forName(driver);
            
            //Get connected
            Connection connect = DriverManager.getConnection(url);
            System.out.println("Connected to database within update method");
            
            //create statement
            Statement statement = connect.createStatement();
            
            //execute statement
            String sql = "UPDATE Items SET itemName = '"+name+"' , itemDescription = '"+desc+"' , size = '"+size+"' , quantity = '"+stock+"' , pricePerItem = '"+price+"' , petType = '"+pet+"' , itemType = '"+item+"' WHERE itemID = '"+id+"'";
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
       //Create an items object for testing
       Items myItem = new Items();
       
       myItem.selectDB(1);
       myItem.display();
       //myItem.insertDB(0, "name", "desc", "size", 5, 10.99, "pet", "item");
       //myItem.updateDB(76, "name", "desc", "size", 100, 25.49, "pet", "item");
       //myItem.deleteDB(76);
       
       //Yay everything works and is ready to go
    }
    
}
