package ramadan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hadith {
    public static String hadith(int day){
        try{
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RamadanDB","AMR","AMR");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("Select haadith from Hadith");
            
            rs.absolute(day);
            String word = rs.getString(1);
            
            rs.close();
            stmt.close();
            con.close();
            
            return word;
            
        }catch(Exception e){
            return "There is something wrong Hadith DB";
        }
    }
}
