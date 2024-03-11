package ramadan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dhikr {
    public static String dhikr(int day){
        try{
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RamadanDB","AMR","AMR");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("Select Dhikrr from Dhikr");
            
            rs.absolute(day);
            String word = rs.getString(1);
            
            rs.close();
            stmt.close();
            con.close();
            
            return word;
            
        }catch(Exception e){
            return "There is something wrong dhikr DB";
        }
    }
}
