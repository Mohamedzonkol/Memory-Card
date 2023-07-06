/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applecation;

import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Card extends StackPane {
//protected boolean isFlipped;
private int id;
private  ImageView frontImageView;
private  ImageView backImageView;
    public Card(int id,Image backImage, Image frontImage) {
        this.id =id;
        backImageView = new ImageView(backImage);
        frontImageView = new ImageView(frontImage);
        frontImageView.setVisible(false);
//        isFlipped = false;
    

        Rectangle rectangle = new Rectangle(160, 160);
        rectangle.setArcWidth (40);
        rectangle.setArcHeight ( 40);
        rectangle.setFill(Color.LIGHTBLUE);
        rectangle.setStroke(Color.BLACK);

 
        rectangle.setEffect(new BoxBlur(6, 6,3));// تأثير الزجاج

        this.getChildren().addAll(rectangle,backImageView,frontImageView);
     
    }


 public ImageView getBackImageView() {
        return backImageView;
    }
public ImageView getFrontImageView() {
    return frontImageView;
}

public void setFrontImageView() {
frontImageView =new ImageView();

}
public void setBackImageView() {
backImageView =new ImageView();

}

   public void flipCard() {
         backImageView.setVisible(true);
         frontImageView.setVisible(false);
        
    }

 public int gtId() {
        return id;
    }

}



 


    

   


