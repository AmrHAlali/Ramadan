package ramadan;

import static java.lang.Math.abs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class NumberOfDay {
    public static int day(){        
        LocalDate date1 = LocalDate.of(2024, 3, 12);
        LocalDate date2 = LocalDate.now();
        int days = (int)abs(ChronoUnit.DAYS.between(date1, date2))+1;
        if(days <=30)
            return days;
        else 
            return days%30;
    }
    public static String salat(int x){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        String timeNow = currentTime.format(formatter);
        String MAGHRiB = "00:00";
        String FAJR = "00:00";
        try{
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RamadanDB","AMR","AMR");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select MAGHRiB, fajr from quran");
            
            rs.absolute(day());
            MAGHRiB = rs.getString(1);
            FAJR = rs.getString(2);
            
            rs.close();
            stmt.close();
            con.close();
        }catch(Exception e){
            System.out.println("There is wrong in salawat");
        }
//        
//        int now = (Integer.parseInt(timeNow.substring(0,timeNow.indexOf(":")))+1)*100 + Integer.parseInt(timeNow.substring(timeNow.indexOf(":")+1));
//        int magrib = (Integer.parseInt(MAGHRiB.substring(0,MAGHRiB.indexOf(":"))))*100 + Integer.parseInt(MAGHRiB.substring(MAGHRiB.indexOf(":")+1));;
//        int fajr = (Integer.parseInt(FAJR.substring(0,FAJR.indexOf(":"))))*100 + Integer.parseInt(FAJR.substring(FAJR.indexOf(":")+1));
//        
//        if(now > magrib) 
        
        if(x == 1){
            return MAGHRiB;
        }else{
            return FAJR;
        }
    }
}
