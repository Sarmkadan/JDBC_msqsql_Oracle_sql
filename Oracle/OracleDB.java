import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;


public class OracleDB {
    public static void main(String[] args) {
        System.out.println("-------- Oracle JDBC Connection Testing ------");

        //----------------------------------------------------------
        //CONNECT TO DATABASE

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "System",
                    "123");
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        //----------------------------------------------------------
        //CREATE TABLE

        String create = "CREATE TABLE STUD("
                + "NAME VARCHAR(20) NOT NULL, "
                + "GROUPNAME VARCHAR(20) NOT NULL, "
                + "GROUPNUMBER NUMBER(5) NOT NULL "
                + ")";
        try {
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(create);
            ps.execute();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

        //----------------------------------------------------------
        //INSERT STATEMENT

        try {
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO STUD (name, groupname, groupnumber) VALUES (?, ?, ?)");
            ps.setString(1,"taras");
            ps.setString(2,"pzs");
            ps.setInt(3,32);
            ps.execute();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

        //----------------------------------------------------------
        //UPDATE STATEMENT

        try {
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("UPDATE STUD SET groupname = ?  WHERE name = 'taras'");
            ps.setString(1,"ksm");
            ps.execute();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

        //----------------------------------------------------------
        //SELECT STATEMENT

        try {
            Statement stmt = connection.createStatement();
            ResultSet rset = stmt.executeQuery("select * from stud");
            while (rset.next())
                System.out.println(rset.getString("name")+" "+rset.getString("groupname")+"-"+rset.getString("groupnumber"));
            stmt.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

        //----------------------------------------------------------
      /*  //DELETE STATEMENT

        try {
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("DELETE STUD  WHERE groupname = ?");
            ps.setString(1,"pzs");
            ps.execute();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

        //----------------------------------------------------------
        //DROP TABLE STATEMENT

        try {
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("DROP TABLE STUD");
            ps.execute();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

        //----------------------------------------------------------
        */
         //DISCONNECT FROM DATABASE

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}