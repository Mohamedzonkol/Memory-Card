
package Applecation;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameInstructions extends Application {

    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));

        Label titleLabel = new Label("Memory Card Game");
        titleLabel.setFont(Font.font("Arial", 35));
        titleLabel.setTextFill(Color.DARKBLUE);

        Label instructionsLabel = new Label("Game Instructions:");
        instructionsLabel.setFont(Font.font("Arial", 20));
        instructionsLabel.setTextFill(Color.DARKBLUE);

        VBox instructionsBox = new VBox(10);
        instructionsBox.getChildren().addAll(
                new Label("1. You will be presented with a set of face-down cards."),
                new Label("2. Flip the cards and remember their positions."),
                new Label("3. Open two cards at a time."),
                new Label("4. If the two cards match, they will hide."),
                new Label("5. If the two cards do not match, they will be flipped again."),
                new Label("6. Try to find all the pairs in the fewest attempts and shortest time."),
                new Label("7. Enjoy the game and challenge yourself!")
        );
        instructionsBox.setAlignment(Pos.CENTER_LEFT);

        // Back Button to Home Page
        Button backButton = new Button("Back to Home Page");
        backButton.setStyle("-fx-background-color: darkblue; -fx-text-fill: white;-fx-background-radius: 15;");
        backButton.setOnAction(event -> {
            primaryStage.close();
        });

        VBox contentBox = new VBox(20);
        contentBox.getChildren().addAll(titleLabel, instructionsLabel, instructionsBox, backButton);
        contentBox.setAlignment(Pos.CENTER);
       
        root.setCenter(contentBox);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Instructions");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
