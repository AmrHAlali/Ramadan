package ramadan;

import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class Quran {
    public static int chapter(){
        try{
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RamadanDB","AMR","AMR");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("Select doneornot from quran");
            int chapter = 0;
            for(int i=1;i<=30;i++){
                rs.next();
                if(rs.getBoolean(1))
                    chapter = i;
            }
            rs.close();
            stmt.close();
            con.close();
            return chapter;
        }catch(Exception e){
            System.out.println("There is something wrong Quran DB");
            return -1;
        }
    }
    public static String fajr(){
        try{
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RamadanDB","AMR","AMR");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("Select fajr from quran");
            
            rs.absolute(NumberOfDay.day());
            String s = rs.getString(1);
            
            rs.close();
            stmt.close();
            con.close();
            
            return s;
            
        }catch(Exception e){
            System.out.println("wrong salat fajr");
        }
        
        return "00:00";
    }
    public static String maghrib(){
        try{
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RamadanDB","AMR","AMR");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("Select maghrib from quran");
            
            rs.absolute(NumberOfDay.day());
            String s = rs.getString(1);
            
            rs.close();
            stmt.close();
            con.close();
            
            return s;
            
        }catch(Exception e){
            System.out.println("wrong salat maghrib");
        }
        
        return "00:00";
    }
    
    public static void update(){
            try{
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RamadanDB", "AMR", "AMR");
                PreparedStatement prep = con.prepareStatement("UPDATE Quran SET DoneOrNot = true WHERE Chapter = ?");

                prep.setInt(1,chapter()+1);
                prep.executeUpdate();

            }catch(Exception e){
                System.out.println("There is something wrong Quran DB1");
            }
    }
    public static Button read(){
        Button btn = new Button("Read Chapter "+(chapter()+1));
        btn.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 24px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-background-color: CADETBLUE;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
        btn.setOnAction(e->{
            int chapter = chapter()+1;
            try {
                URI uri = new URI("https://surahquran.com/quran-search/chapter-"+chapter+".html");
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(uri);
                    } else {
                        System.out.println("Opening links is not supported on this platform.");
                    }
                } else {
                    System.out.println("Desktop is not supported.");
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        });
        
        return btn;
    }
    public static Button undo(Button read,CheckBox quran){
        Button btn = new Button("Undo");
        btn.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 16px;" +
                "-fx-padding: 5px;"
                + "-fx-background-color: transparent;"
        );
        btn.setOnAction(e->{
            try{
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RamadanDB", "AMR", "AMR");
                PreparedStatement prep = con.prepareStatement("UPDATE Quran SET DoneOrNot = false WHERE Chapter = ?");

                prep.setInt(1,chapter());
                prep.executeUpdate();
                
                read.setText(Quran.read().getText());
                quran.setText(Design.quran(read).getText());
            }catch(Exception r){
                System.out.println("wrrong");
            }
        });
        
        return btn;
    }
}
