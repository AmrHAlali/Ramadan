package ramadan;

import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ramadan extends Application {
    public void start(Stage stage) throws SQLException{
        Pane pane = new Pane();
        Scene scene = new Scene(pane,500,550);
        pane.getChildren().add(Design.backGround());
        
        // Day Ramadan + Date
        Label date = Design.date();
        pane.getChildren().add(date);
        date.setTranslateX(220); date.setTranslateY(100);
        
        // Hadith
        Button hadith = Design.hadith(stage, scene);
        pane.getChildren().add(hadith);
        hadith.setTranslateX(260); hadith.setTranslateY(150);
        
        // Dihkr
        Button dihkr = Design.dhikr(stage, scene);
        pane.getChildren().add(dihkr);
        dihkr.setTranslateX(350); dihkr.setTranslateY(150);
        
        // مستطيل
        Rectangle rec = new Rectangle(550,100);
        rec.setFill(Color.CADETBLUE);
        rec.setTranslateY(300);
        pane.getChildren().add(rec);
        
        // اذان الفجر
        Label fajr = Design.fajr();
        pane.getChildren().add(fajr);
        fajr.setTranslateX(20); fajr.setTranslateY(310);
        
        // اذان المغرب
        Label maghrib = Design.maghrib();
        pane.getChildren().add(maghrib);
        maghrib.setTranslateX(20); maghrib.setTranslateY(340);
        
        // قراءة
        Button read = Quran.read();
        
        // قراءةالقران
        Label quran1 = new Label("Reading quran: ");
        quran1.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 24px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20 20 20 20;"
        );
        pane.getChildren().add(quran1);
        quran1.setTranslateX(280); quran1.setTranslateY(310);
        CheckBox quran = Design.quran(read);
        HBox boox = new HBox();
        
        Button cc = Quran.undo(read,quran);
        boox.getChildren().addAll(quran,cc);
        cc.setTranslateY(5);
        pane.getChildren().add(boox);
        boox.setTranslateX(300); boox.setTranslateY(340);
        
        
        // بحث
        TextField surah = new TextField("Search Surah");
        surah.setPrefWidth(125);
        surah.setPrefHeight(40);
        surah.setAlignment(Pos.CENTER_RIGHT);
        surah.setStyle("-fx-font-size: 18px; -fx-text-fill: darkgray;");
        surah.setOnMouseClicked(e ->{ surah.clear(); surah.setStyle("-fx-font-size: 18px; -fx-text-fill: black;");});
        
        Button search = Design.search(surah);
                
        HBox ss = new HBox(10);
        ss.getChildren().addAll(read,surah,search);
        pane.getChildren().add(ss);
        
        ss.setTranslateY(435);
        ss.setTranslateX(50);
        

        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
