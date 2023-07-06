package Applecation;

import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePage extends Application {


   private static Stage stage;
   private long theTime;
   public Label number;
   private   int  seconds ;
   private  Label timerLabel;
   private Label timerTextField;
   private  static Timeline timeline;
   private static  int NUM_CARDS = 16;
    private MediaPlayer soundClick;
   private MediaPlayer gameSound;
   private   MediaPlayer sounTimer;
private static  String playMode ="normal"; 
      private  GameFun game;
        public  GridPane gridPane = new GridPane();


@Override
    public void start(Stage primaryStage)  {
game = new GameFun(NUM_CARDS);
        this.stage =primaryStage;
        
       HomePage home=new HomePage();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        for (int i = 0; i <game.getNumCards(); i++) {
            Card card = game.getCards().get(i);
            gridPane.add(card, i % 4, i / 4);

        }
        

      
        Button backButton = new Button("BACK");
        backButton.setPrefSize(150, 40);
        backButton.setStyle("-fx-background-color: gold;-fx-background-radius: 30;");
        backButton.setFont(Font.font("Arial", 18));
        backButton.setTextFill(Color.BLUEVIOLET);
        //        تغيير لون عند وضع المؤشر 
        backButton.setOnMouseEntered(e -> { 
             backButton.setTextFill(Color.BROWN);           
                });
//        اعاده لون الموس للون الطبيعي عند خروج المؤشلا من الزر
          backButton.setOnMouseExited(e -> { 
             backButton.setTextFill(Color.GOLD);  
             
             
                          });
       
        FlowPane buttonsContainer = new FlowPane(Orientation.HORIZONTAL);
        buttonsContainer.setPadding(new Insets(30, 30, 30, 30));
        buttonsContainer.setHgap(40);
        buttonsContainer.setVgap(40);
        buttonsContainer.getChildren( ).addAll(backButton);       

      timerLabel = new Label("Time:");
      timerTextField = new Label();
      timerLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: white;");
      timerTextField.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: white;");
      setTimer(playMode);

         timerTextField.setText(""+seconds);

        HBox hbox = new HBox(40, timerLabel, timerTextField);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding((new Insets(30,30,30,30)));
       Image backGround = new Image("/images/22033.png");

        BorderPane borderPane = new BorderPane();
      
        borderPane.setBottom(hbox);
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        borderPane.setBackground(new Background(new BackgroundImage(backGround,null,null,null,backgroundSize)));
        borderPane.setTop(buttonsContainer);
        borderPane.setAlignment(buttonsContainer, Pos.TOP_LEFT);
        borderPane.setCenter(gridPane);
          Scene scene = new Scene(borderPane, home.width, home.height);
            backButton.setOnAction(e -> {
            soundClick();
            primaryStage.hide();
            home.start(new Stage());
            seconds=0;
            timeline.stop();
        
});
            

               
game.showCards();
primaryStage.setScene(scene);
primaryStage.show();
primaryStage. setResizable (false);  
    } 
public void closeWindow(){
stage.close();

  }

public void setTimer(String mode){
    this.playMode=mode;
    if(mode=="normal"){
 
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), e-> {
                seconds+=1;
                timerTextField.setText(String.valueOf(seconds)+"  "+"Sec");
            
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        }
    else{
     setSecond(20);      
     timeline = new Timeline(new KeyFrame(Duration.millis(1000), e-> {
         seconds-=1;
         timerTextField.setText(String.valueOf(seconds)+"  "+"Sec");
                if(seconds==10){soundTimer();}
                if(seconds==0){
                 stopSoundTimer();
                 gameOver gameOver=new gameOver(); 
                            gameOver.start(new Stage());

                stage.hide();
                }
        }));
        timeline.setCycleCount(seconds);
        timeline.play(); 
    }



}
 public void soundClick(){
           File soundFile = new File("src/sound/ES_Button Switch Click 5 - SFX Producer.mp3");
        Media sound = new Media(soundFile.toURI().toString());
                  soundClick = new MediaPlayer(sound);
                    if (soundClick.getStatus() == MediaPlayer.Status.PLAYING) {    
                   soundClick.seek(soundClick.getStartTime());
               } else {
                   soundClick.play();
               }  
  } 
 public void stopSoundClick(){
 
 soundClick.stop();
 }
  
  
     public Timeline getTimer() {
      return timeline;
  
     } 
        public int getnumcards() {
      return NUM_CARDS;
  
     } 
     public  static void setNUM_CARDS(int num){
//                     System.out.println("num cards"+num);

     GamePage.NUM_CARDS=num;
//             System.out.println("num cards"+NUM_CARDS);

     }
     public void setSecond(int second){
     this.seconds=second;
     
     }
     public  void setMode(String mode){    
     GamePage.playMode=mode;
}
 public void soundTimer(){

    File soundFile = new File("src/sound/tickingbuzzer-75859.mp3");
        Media sound = new Media(soundFile.toURI().toString());
                  sounTimer = new MediaPlayer(sound); 
                   sounTimer.play();
                
}
public void stopSoundTimer(){

 sounTimer.stop();
}
 
}
