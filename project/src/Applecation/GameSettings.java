
package Applecation;

import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameSettings extends Application {
private RadioButton level3; 
private RadioButton level2; 
private RadioButton level1 ; 
private RadioButton easy; 
private RadioButton normal; 
private RadioButton hard ; 
private ToggleGroup toggleGroup;
private ToggleGroup toggleGroup2;

    
    
                 
       HomePage home =new HomePage();
       GamePage gamepage=new GamePage();
GameFun gamefun =new GameFun();
   public void start(Stage primaryStage) {
 
//       control about num of card
Label nOFCard = new Label("Num Of Card ");
nOFCard.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");
 toggleGroup= new ToggleGroup();
 level3 = new RadioButton("16");
 level2 = new RadioButton("8");
 level1 = new RadioButton("4");
level1.setToggleGroup(toggleGroup);
level2.setToggleGroup(toggleGroup);
level3.setToggleGroup(toggleGroup);
HBox checkBox =new HBox(80,level1,level2,level3);
checkBox.setAlignment(Pos.CENTER);
setLevel();


//control in difeculty of game 
Label playMode = new Label("Play Mode ");
playMode.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");
 toggleGroup2= new ToggleGroup();
  easy= new RadioButton("Easy");
 normal = new RadioButton("Normal");
 hard = new RadioButton("Hard");
 
easy.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
normal.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");
hard.setStyle("-fx-font-weight: bold; -fx-text-fill: blue;");
easy.setToggleGroup(toggleGroup2);
normal.setToggleGroup(toggleGroup2);
hard.setToggleGroup(toggleGroup2);
HBox checkPalyMode =new HBox(60,easy,normal,hard);
checkPalyMode.setAlignment(Pos.CENTER);
setMode();
  
      
       
      
//control volume sound

       Label lblVolume = new Label("Voulme");
      lblVolume.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");
      Slider volumeSlider = new Slider(0, 1,.5);

      volumeSlider.setStyle("-fx-pref-width: 250px; -fx-background-color: white; -fx-background-radius: 10px; -fx-border-color: white; -fx-border-radius: 10px;");
volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
    home.getstartSound().setVolume(newValue.doubleValue());
});


       
  
//   conrol sound on or off
        ToggleButton muteButton = new ToggleButton("Muted Sound");
        muteButton.setStyle("-fx-background-color: white;-fx-background-radius: 18;");
        muteButton.setTextFill(Color.GRAY);
        muteButton.setFont(Font.font("Arial", 16));
      muteButton.setOnAction(event -> {
         if (muteButton.isSelected()) {
            muteButton.setText("Muted");
            home.getstartSound().stop();
         } else {
            muteButton.setText("UnMuted");
                        home.getstartSound().play();

         }
      });
//      control background of game
      Label lblGameMode = new Label("وضع اللعبة");
       RadioButton LightModeCheckBox = new RadioButton("نهاري");
      RadioButton nightModeCheckBox = new RadioButton("ليلي");

      lblGameMode.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");
    ToggleGroup toggleGroup3=new ToggleGroup();
      LightModeCheckBox.setToggleGroup(toggleGroup3);
      nightModeCheckBox.setToggleGroup(toggleGroup3);
 
      
      toggleGroup3.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
    if (newValue == LightModeCheckBox) {
        
          home.setLightBackGround();

            } 
    else{
        home.setNightBackGround();
    } 
});      
      
//      Create gridpane and addition 
      
      GridPane grid = new GridPane();
      grid.setPadding(new Insets(20, 20, 20, 20));
      grid.setAlignment(Pos.CENTER);
      grid.setHgap(40);
      grid.setVgap(40);
      
      
      grid.add(nOFCard, 0, 0);
      grid.add(checkBox, 1, 0);
      grid.add(playMode, 0, 1);
      grid.add(checkPalyMode, 1, 1);
      grid.add(lblVolume, 0, 2);
      grid.add(volumeSlider, 1, 2);
      grid.add(muteButton, 0, 3);
      grid.add(lblGameMode, 0, 4);
      grid.add(nightModeCheckBox, 1, 4);
      grid.add(LightModeCheckBox, 2, 4);


      grid.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,null,null)));
      Scene scene = new Scene(grid, 700, 700);
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage. setResizable (false);

   }
   
   
public void setMode(){
toggleGroup2.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
    if (newValue == easy) {
        gamepage.setMode("normal");

gamefun.setshowTime(3000);
    } 
    else if (newValue == normal) {
gamepage.setMode("normal");

gamefun.setshowTime(1500);

    }
    else{   
gamepage.setMode("hard");
gamefun.setshowTime(500);
     }
});

  }
public void setLevel(){
       System.out.println("enter the fun"); 
toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
    if (newValue == level1) {
//        System.out.println("level1");
            gamepage.setNUM_CARDS(4);

    } 
    else if (newValue == level2) {
//                System.out.println("level2");

    gamepage.setNUM_CARDS(8);

    } else{   
//                System.out.println("level3");

        gamepage.setNUM_CARDS(16);

            
            }
});

  }
}