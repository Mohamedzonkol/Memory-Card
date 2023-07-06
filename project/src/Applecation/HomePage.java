
package Applecation;

/**
 *
 * @author Lenovo
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;        
        public class HomePage extends Application {
        public double width =1000;
        public  double height=1000;
 private static MediaPlayer startSound ;
  private static BorderPane borderPane ;


    @Override
    public void start(Stage primaryStage) {
        GamePage play= new GamePage();
GameInstructions about =new GameInstructions();
GameSettings sitting=new GameSettings();
startSound(); 
String welcomeMessage = "Welcome To Memory Game";
Text welcomeMassage = new Text();
welcomeMassage.setFill(Color.DARKTURQUOISE);
welcomeMassage.setFont(new Font("Chiller",80));
welcomeMassage.setStroke(Color.BLACK);
welcomeMassage.setStrokeWidth(.5);

Timeline timeline = new Timeline();
for (int i =0; i < welcomeMessage.length(); i++) {
    int x=i;


    KeyFrame keyFrame = new KeyFrame(Duration.millis(i * 150), event -> {
        welcomeMassage.setText(welcomeMassage.getText() + welcomeMessage.charAt(x));
    });
    timeline.getKeyFrames().add(keyFrame);
}
timeline.play();


      
       Button button1 = new Button("PLAY ");
       Button button2 = new Button("Instructions ");
       Button button3 = new Button("Settings");
       Button button4 = new Button("EXIT");
     
Image image = new Image("/images/cards.png");
ImageView imageView = new ImageView(image);
StackPane stackPane = new StackPane();
stackPane.getChildren().add(imageView);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), stackPane);
        transition.setFromY(-600); // Start the image above the scene
        transition.setToY(0); // Move the image to its original position
        transition.play();
//        // تأثير التظليل (DropShadow)
//        DropShadow dropShadowEffect = new DropShadow();
//        dropShadowEffect.setColor(Color.GRAY);
//        dropShadowEffect.setRadius(10);
//        dropShadowEffect.setOffsetX(5);
//        dropShadowEffect.setOffsetY(5);
//        button1.setEffect(dropShadowEffect);
//        button2.setEffect(dropShadowEffect);
//        button3.setEffect(dropShadowEffect);
//        button4.setEffect(dropShadowEffect);

//        // تأثير الاندفاع (Translate)
//Button[] buttons = {button1, button2, button3, button4};
//
//for (Button button : buttons) {
//    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), button);
//    translateTransition.setFromY(0);
//    translateTransition.setToY(-20);
//    translateTransition.setCycleCount(Animation.INDEFINITE);
//    translateTransition.setAutoReverse(true);
//    translateTransition.play();
//}

//styling of buttons 

        button1.setPrefSize(490, 70);
        button1.setStyle("-fx-background-color: red;-fx-background-radius: 18;");
        button1.setFont(Font.font("Arial", 30));
        button1.setTextFill(Color.WHITE);
        //        تغيير لون عند وضع المؤشر 
        button1.setOnMouseEntered(e -> { 
             button1.setTextFill(Color.BROWN);           
                });
//        اعاده لون الموس للون الطبيعي عند خروج المؤشلا من الزر
          button1.setOnMouseExited(e -> { 
             button1.setTextFill(Color.WHITE);  
                         });
        button2.setPrefSize(490, 70);
        button2.setStyle("-fx-background-color: red;-fx-background-radius: 18;");
        button2.setFont(Font.font("Arial", 30));
        button2.setTextFill(Color.WHITE);
        button2.setOnMouseEntered(e -> { 
             button2.setTextFill(Color.BROWN);           
                });
          button2.setOnMouseExited(e -> { 
             button2.setTextFill(Color.WHITE);  

                });

        button3.setStyle("-fx-background-color: red;-fx-background-radius: 18;");
        button3.setPrefSize(490, 70);
        button3.setFont(Font.font("Arial", 30));
        button3.setTextFill(Color.WHITE);
        button3.setOnMouseEntered(e -> { 
             button3.setTextFill(Color.BROWN);

                });
          button3.setOnMouseExited(e -> { 

             button3.setTextFill(Color.WHITE);    
                });
        button4.setStyle("-fx-background-color: red;-fx-background-radius: 18;");
        button4.setPrefSize(490, 70);
        button4.setFont(Font.font("Arial", 30));
        button4.setTextFill(Color.WHITE);
          
        button4.setOnMouseEntered(e -> { 

             button4.setTextFill(Color.BROWN);
                });
          button4.setOnMouseExited(e -> { 

             button4.setTextFill(Color.WHITE);         
                });
  

//Action on buttons

       
         button1.setOnAction(event -> {
             soundClick();
           
          try {
              play.start(new Stage());
          } catch (Exception ex) {
              Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
          }          
        primaryStage.hide();
        startSound.stop();
        

         });
        button2.setOnAction(event -> {           
                         soundClick();
                           about.start(new Stage());

        });
           button3.setOnAction(event -> {
                       soundClick();
                        sitting.start(new Stage());


            } );
           
     
        
                 button4.setOnAction(e -> {
                       soundClick();
//            closr game
            primaryStage.close();
            
            } ); 
                 
                 
                 
        VBox buttonsBox = new VBox( 60,stackPane,button1, button2, button3,button4);
        buttonsBox.setAlignment(Pos.CENTER);
        borderPane = new BorderPane();
        borderPane.setTop(welcomeMassage);
        borderPane.setAlignment(welcomeMassage, Pos.CENTER);
        borderPane.setCenter(buttonsBox);
        
 



      borderPane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
      Scene scene = new Scene(borderPane, width, height);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);

        primaryStage.show();
        primaryStage. setResizable (false);
  
            

    }
  public MediaPlayer getstartSound(){
  return startSound;
  }
    public void setNightBackGround(){
              borderPane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

  
  }
       public void setLightBackGround(){
           borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
  }
           public void startSound(){
       
    
      File soundGame =new File("src/sound/mixkit-serene-view-443.mp3");
        Media  soundgame = new Media(soundGame.toURI().toString());
         startSound = new MediaPlayer(soundgame);
        startSound.play();
 


    
}
       public void soundClick(){
       

        File soundFile = new File("src/sound/ES_Button Switch Click 5 - SFX Producer.mp3");
        Media sound = new Media(soundFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
                        mediaPlayer.play();


    
}
       
        }