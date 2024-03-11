package ramadan;

import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;

public class Design {
    public static void style(Node o){
        o.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 22px;" +
                "-fx-text-fill: white;" +
                "-fx-background-color: black;" +
                "-fx-padding: 5px;" +
                //"-fx-background-color: lightblue;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
    }
    
    public static Label date(){
        Label l = new Label();
        l.setText(NumberOfDay.day() + " Ramadan / " + LocalDate.now());
        style(l);
        
        return l;
    }
    public static CheckBox quran(Button l){
        CheckBox c = new CheckBox();
        c.setText("Chapter "+(Quran.chapter()));
        c.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 20px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
        
        c.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException ex) {}
                    Quran.update();
                    c.setText("Chapter "+(Quran.chapter()));
                    c.setSelected(false); 
                    int xx = Integer.parseInt(Quran.read().getText().substring(13));
                    l.setText("Read Chapter "+xx);
                }
            }
        });
        
        return c;
    }
    
    public static Button hadith(Stage stage,Scene scene){
        Button btn = new Button("Hadith");
        
        btn.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 24px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-background-color: CADETBLUE;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
        
        Label l = new Label(Hadith.hadith(NumberOfDay.day()));
        l.setPrefWidth(450);
        l.setWrapText(true);
        l.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        l.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 18px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-background-color: CADETBLUE;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
        
        Button back = new Button("Back");
        back.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 24px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-background-color: CADETBLUE;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
        
        VBox box = new VBox(20);
        box.getChildren().addAll(l,back);
        box.setAlignment(Pos.CENTER);
        box.setTranslateY(50);
                
        StackPane pane = new StackPane();
        pane.getChildren().addAll(backGround(),box);
        Scene scene1 = new Scene(pane,500,550);
        
        btn.setOnAction(e->{
            stage.setScene(scene1);
        });
        back.setOnAction(e->{
            stage.setScene(scene);
        });
        
        return btn;
    }
    
    public static Button dhikr(Stage stage,Scene scene){
        Button btn = new Button("Dhikr");
        
        btn.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 24px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-background-color: CADETBLUE;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
        
        Label l = new Label(Dhikr.dhikr(NumberOfDay.day()));
        l.setPrefWidth(450);
        l.setWrapText(true);
        l.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        l.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 18px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-background-color: CADETBLUE;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
        
        Button back = new Button("Back");
        back.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 24px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-background-color: CADETBLUE;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
        
        VBox box = new VBox(20);
        box.getChildren().addAll(l,back);
        box.setAlignment(Pos.CENTER);
        box.setTranslateY(50);
        
        StackPane pane = new StackPane();
        pane.getChildren().addAll(backGround(),box);
        Scene scene1 = new Scene(pane,500,550);
        
        btn.setOnAction(e->{
            stage.setScene(scene1);
        });
        back.setOnAction(e->{
            stage.setScene(scene);
        });
        
        return btn;
    }
    public static Label fajr(){
        Label l = new Label("Fajr: " + Quran.fajr()+"AM");
        l.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 24px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
        
        return l;
    }
    public static Label maghrib(){
        Label l = new Label("Maghrib: " + Quran.maghrib()+"PM");
        l.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 24px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
        
        return l;
    }
    public static Pane backGround(){
        // sky
        javafx.scene.shape.Rectangle rect1 = new javafx.scene.shape.Rectangle(0, 0, 630, 630);
        rect1.setStyle("-fx-fill: linear-gradient(to bottom, black 60%, white);");
        
        // Main pane
        Pane root = new Pane();
        root.getChildren().addAll(rect1);           
        
        // Moon
        Circle circle1 =new Circle(120 , 121 , 110,Color.WHITE);
        Circle circle2 =new Circle(160 , 121 , 86,Color.BLACK);
        Circle circle3 =new Circle(125 , 190 , 30,Color.GAINSBORO);
        Circle circle4 =new Circle(120 , 55 , 40,Color.GAINSBORO);
        Circle circle5 =new Circle(20 , 130 , 45,Color.GAINSBORO);
        
        // gray circles on moon
        QuadCurve quadCurve0 = new QuadCurve(13, 66,-15, 115, 13, 176);
        quadCurve0.setStroke(Color.BLACK);
        quadCurve0.setStrokeWidth(21);
        quadCurve0.setFill(null);
        
        root.getChildren().addAll(circle1,circle3,circle4,circle5,circle2,quadCurve0);
        
        return root;
    }
    public static Button search(TextField txt){
        Button btn = new Button("Search");
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
            int surah=0;
            try{
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RamadanDB","AMR","AMR");
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("Select * from surahs");
                
                while(rs.next()){
                    if(rs.getString(1).matches(".*"+txt.getText()+".*")){
                        surah = rs.getInt(2);
                        break;
                    }
                }
                
                rs.close();
                stmt.close();
                con.close();
            }catch(Exception z){
                System.out.println("There is surahh error");
            }
            if(surah>=1 && surah<=114){
                try {
                    URI uri = new URI("https://surahquran.com/"+ surah +".html");
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
            }else{
                txt.setText("Not Found!");
            }
        });
        
        return btn;
    }
}
