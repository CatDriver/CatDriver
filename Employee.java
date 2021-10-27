package pawsnfins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*******************************************************************************
 *  Caitlin Driver
 *  Java Project
 *  Employee Class
 ******************************************************************************/
public class Employee {
    //Create class properties
    private int empID;
    private String empName;
    private String empAddress;
    private String empPhone;
    private String empEmail;
    private String username;
    private String password;
    private String empTitle;
    private final String url = "jdbc:ucanaccess://C:\\Users\\dihog\\Desktop\\Project\\PetStore1.accdb";
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    
    //Create constructors
    public Employee() {
        //initialize properties
        empID = 0;
        empName = "";
        empAddress = "";
        empPhone = "";
        empEmail = "";
        username = "";
        password = "";
        empTitle = "";
    }
    public Employee(int id, String name, String add, String phone, String email, String un, String pw, String title) {
        empID = id;
        empName = name;
        empAddress = add;
        empPhone = phone;
        empEmail = email;
        username = un;
        password = pw;
        empTitle = title;
    }
    
    //get and set methods for properties
    public void setEmpID(int id) {
        empID = id;
    }
    public int getEmpID() {
        return empID;
    }
    public void setEmpName(String name) {
        empName = name;
    }
    public String getEmpName() {
        return empName;
    }
    public void setAddress(String add) {
        empAddress = add;
    }
    public String getAddress() {
        return empAddress;
    }
    public void setPhone(String phone) {
        empPhone = phone;
    }
    public String getPhone() {
        return empPhone;
    }
    public void setEmail(String email) {
        empEmail = email;
    } 
    public String getEmail() {
        return empEmail;
    }
    public void setUsername(String un) {
        username = un;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String pw) {
        password = pw;
    }
    public String getPassword() {
        return password;
    }
    public void setTitle(String _title) {
        empTitle = _title;
    }
    public String getTitle() {
        return empTitle;
    }

    /***************************************************************************
     * Display method for showing the class properties and their values.
     **************************************************************************/
    public void display(){
        System.out.println("Employee ID: " + empID);
        System.out.println("Name       : " + empName);
        System.out.println("Address    : " + empAddress);
        System.out.println("Phone      : " + empPhone);
        System.out.println("Email      : " + empEmail);
        System.out.println("Username   : " + username);
        System.out.println("Password   : " + password);
        System.out.println("Title      : " + empTitle);
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
            String sql = "SELECT * FROM Employee WHERE empID = '"+id+"'";
            System.out.println(sql);

            ResultSet results = selectStatement.executeQuery(sql);
            results.next(); //grab all data from customer id
               empID = results.getInt(1);
               empName = results.getString(2);
               empAddress = results.getString(3);
               empPhone = results.getString(4);
               empEmail = results.getString(5);
               username = results.getString(6);
               password = results.getString(7);
               empTitle = results.getString(8);

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
    public void insertDB(int id, String name, String add, String phone, String email, String un, String pw, String title) {
        try{
            //load the driver
            Class.forName(driver);
            
             //Establish connection
            Connection insertConnect = DriverManager.getConnection(url);
            System.out.println("Database connected");
           
            //create statement
            Statement insertStatement = insertConnect.createStatement();
           
            //execute statement
            String sql = "INSERT INTO Employee (empID,empName,empAddress,empPhone,empEmail,username,password,empTitle) VALUES ('"+id+"','"+name+"','"+add+"','"+phone+"','"+email+"','"+un+"','"+pw+"','"+title+"')";
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
            String sql = "DELETE FROM Employee WHERE empID = '"+id+"'";
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
    public void updateDB(int id, String name, String add, String phone, String email, String un, String pw, String title){
        try{
            //load the driver
            Class.forName(driver);
            
            //Get connected
            Connection connect = DriverManager.getConnection(url);
            System.out.println("Connected to database within update method");
            
            //create statement
            Statement statement = connect.createStatement();
            
            //execute statement
            String sql = "UPDATE Employee SET empName = '"+name+"' , empAddress = '"+add+"' , empPhone = '"+phone+"' , empEmail = '"+email+"' , username = '"+un+"' , password = '"+pw+"' , empTitle = '"+title+"' WHERE empID = '"+id+"'";
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
        Employee emp1 = new Employee();
        
        //emp1.selectDB(1);
        //emp1.display();
        
        //emp1.selectDB(1);
        //emp1.display();
        //emp1.insertDB(6, "name", "add", "phone", "email", "un", "pw", "title");
        //emp1.updateDB(7, "yourmama", "add", "phone", "email@yahoo.com", "thisismyname", "thisismyshit", "job");
        //emp1.deleteDB(6);
        //emp1.deleteDB(7);
        
        //Yay Employee is ready to go
    }
    
}
