
import java.sql.*;
public class Program {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/hunter";

    static final String USER =  "system";
    static final String PASS =  "123456";

    public static void main(String s[])  {
        Connection conn = null;

        PreparedStatement parametersInsert;
        PreparedStatement parametersUpdate;
        PreparedStatement parametersDelete;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            // CONNECTION
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            Statement stmtDrop = conn.createStatement();
            stmtDrop.executeUpdate("DROP TABLE if exists  Person");

            // CREATE
            Statement stmtCreate = conn.createStatement();
            String commandCreate = "CREATE TABLE Person ( " +
                                        " id int unsigned NOT NULL AUTO_INCREMENT, " +
                                        " name VARCHAR(50) NOT NULL, " +
                                        " telephone VARCHAR(50) NOT NULL," +
                                        " PRIMARY KEY(id) " +
                                        ")  ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT = 1; ";
            stmtCreate.executeUpdate(commandCreate);

            // INSERT
            Statement stmtInsert = conn.createStatement();
            String name = "Tolya";
            String phone = "0993812832";
            parametersInsert = conn.prepareStatement("INSERT INTO Person(name,telephone) VALUES (?, ?)");
            parametersInsert.setString(1, name);
            parametersInsert.setString(2, phone);
            parametersInsert.executeUpdate();

            // UPDATE
            Statement stmtUpdate = conn.createStatement();
            String newName = "NewTolya";
            parametersUpdate =  conn.prepareStatement("UPDATE Person SET name = ? WHERE name= ?");
            parametersUpdate.setString(1, newName);
            parametersUpdate.setString(2, name);
            parametersUpdate.executeUpdate();

            // SELECT
            Statement stmtSelect = conn.createStatement();
            ResultSet rs = stmtSelect.executeQuery("SELECT name, telephone FROM Person");
            while(rs.next()){
               System.out.println(" name: " + rs.getString("name"));
               System.out.println(" telephone: " +  rs.getString("telephone"));
            }
/*
            // DELETE
            parametersDelete = conn.prepareStatement("DELETE FROM Person  WHERE telephone = ? ");
            parametersDelete.setString(1, "0993812832");
            parametersDelete.execute();

            // DROP TABLE
            stmtDrop.executeUpdate("DROP TABLE if exists  Person");*/

        } catch (Exception ex) {
              System.out.println("Exception : " + ex.getMessage());
        }

        System.out.println("-_-");
    }
}