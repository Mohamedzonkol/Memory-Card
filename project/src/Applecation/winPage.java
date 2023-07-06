/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applecation;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;


public class winPage extends Application {
    
    @Override
    public void start(Stage primaryStage)  {
        GamePage gamePage =new GamePage();
        GameFun game =new GameFun();
        soundwin();
String welcomeMessage = "Congratulations! You Win";
Text winMassage = new Text();
winMassage.setFill(Color.GOLD);
winMassage.setFont(new Font("Times New Roman", 50));
winMassage.setStroke(Color.BLACK);
winMassage.setStrokeWidth(.5);
Timeline timeline = new Timeline();
for (int i =0; i < welcomeMessage.length(); i++) {
    int x=i;

    KeyFrame keyFrame = new KeyFrame(Duration.millis(i * 250), event -> {
        winMassage.setText(winMassage.getText() + welcomeMessage.charAt(x));
    });
    timeline.getKeyFrames().add(keyFrame);
}
timeline.play();
        
        
        
        
Label numIteraction =new Label("Num Of Attemps:");            
Label iteravtion =new Label();   
        
  iteravtion.setText(String.valueOf(game.getnumIteration()));

Label timer =new Label("The Time:");            
Label time =new Label();
     time.setText(String.valueOf(game.getTimegame())+ " "+"Seconds");

numIteraction.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");
iteravtion.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");
timer.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");
time.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");
Button restartButton =new Button("Play Again");
Button exitButton =new Button("Exit");

   restartButton.setPrefSize(120, 70);
        restartButton.setStyle("-fx-background-color: black;-fx-background-radius: 15; -fx-font-size: 15px ;-fx-font-weight: bold;");
        restartButton.setFont(Font.font("Arial"));
        restartButton.setTextFill(Color.BROWN);
         exitButton.setPrefSize(120, 70);
        exitButton.setStyle("-fx-background-color: black;-fx-background-radius: 15; -fx-font-size: 15px ;-fx-font-weight: bold;");
        exitButton.setFont(Font.font("Arial"));
        exitButton.setTextFill(Color.TEAL);
           
                // تأثير التظليل (DropShadow)

        DropShadow dropShadowEffect = new DropShadow();
        dropShadowEffect.setColor(Color.GRAY);
        dropShadowEffect.setRadius(10);
        dropShadowEffect.setOffsetX(5);
        dropShadowEffect.setOffsetY(5);
        restartButton.setEffect(dropShadowEffect);
                exitButton.setEffect(dropShadowEffect);

        
             restartButton.setOnAction(event -> {
gamePage.soundClick();
game.resetnumIteration();

            try {
                
                gamePage.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(winPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             
             primaryStage.close();
            
        });
                exitButton.setOnAction(event -> {
gamePage.soundClick();             
             primaryStage.close();
            
        }); 
        
HBox hbox =new HBox(50,restartButton,exitButton);
hbox.setAlignment(Pos.CENTER);
    GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.add(numIteraction,0,0);
        gridPane.add(iteravtion,2,0);
        gridPane.add(timer,0,3);
        gridPane.add(time,2,3);

        
//                    Image backGround = new Image("/images/17792.png");

        
                BorderPane borderPane = new BorderPane();

                borderPane.setTop(winMassage);
        borderPane.setAlignment(winMassage, Pos.CENTER);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(hbox);
borderPane.setPadding((new Insets(20,20,20,20)));
        
        
      borderPane.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));

        Scene scene = new Scene(borderPane, 600, 600);
           primaryStage.setTitle("Win Page!");
        primaryStage.setScene(scene);
        primaryStage.show();
        gamePage.closeWindow();
        
    }
public void soundwin(){
      File soundFile = new File("src/sound/mixkit-final-level-bonus-2061.wav");
        Media sound = new Media(soundFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
}
}
