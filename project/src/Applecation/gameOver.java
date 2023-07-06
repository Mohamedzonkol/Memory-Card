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
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;


public class gameOver extends Application {
    
    @Override
    public void start(Stage primaryStage)  {
        GamePage gamePage =new GamePage();
                GameFun game =new GameFun();

        soundOver();
String OverMessage = "Oops! Game Over";
Text gameoverMassage = new Text();
gameoverMassage.setFill(Color.BLANCHEDALMOND);
gameoverMassage.setFont(new Font("Times New Roman", 50));
gameoverMassage.setStroke(Color.BLACK);
gameoverMassage.setStrokeWidth(.5);
Timeline timeline = new Timeline();
for (int i =0; i < OverMessage.length(); i++) {
    int x=i;
    KeyFrame keyFrame = new KeyFrame(Duration.millis(i * 250), event -> {
        gameoverMassage.setText(gameoverMassage.getText() + OverMessage.charAt(x));
    });
    timeline.getKeyFrames().add(keyFrame);
}
timeline.play();
        
Label endGame =new Label("Better Luck Next Time, Try Again ");            

endGame.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");

Button restartButton =new Button("Try Again");
   restartButton.setPrefSize(150, 70);
        restartButton.setStyle("-fx-background-color: red;-fx-background-radius: 15; -fx-font-size: 15px ;-fx-font-weight: bold;");
        restartButton.setFont(Font.font("Arial"));
        restartButton.setTextFill(Color.WHITE);
           
             restartButton.setOnAction(event -> {
            game.resetnumIteration();
           gamePage.soundClick();
            try {   
                gamePage.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(winPage.class.getName()).log(Level.SEVERE, null, ex);
            }
             primaryStage.close();
            
        });
        
  
                BorderPane borderPane = new BorderPane();

                borderPane.setTop(gameoverMassage);
        borderPane.setAlignment(gameoverMassage, Pos.CENTER);
        borderPane.setCenter(endGame);
        borderPane.setBottom(restartButton);
     borderPane.setAlignment(restartButton, Pos.CENTER);
borderPane.setPadding((new Insets(20,20,20,20)));
        
        
      borderPane.setBackground(new Background(new BackgroundFill(Color.INDIGO,null,null)));

        Scene scene = new Scene(borderPane, 600, 600);
           primaryStage.setTitle("GameOver Page!");
        primaryStage.setScene(scene);
        primaryStage.show();
        gamePage.closeWindow();
        
    }
public void soundOver(){
      File soundFile = new File("src/sound/mixkit-falling-game-over-1942.wav");
        Media sound = new Media(soundFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
}
}
