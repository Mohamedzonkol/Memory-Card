package Applecation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameFun {

    private int numCards;
    private int score = 0;
    private static int numIteration;
    private static long startTime;
    private static long endTime;
    private static int showTime = 2000;
    private final List<Card> cards = new ArrayList<>();
    List<Card> selectedCards = new ArrayList<>();
    private TimerTask flipCardsTask;

    public GameFun(int numCards) {
        this.numCards = numCards;

        // تحميل الصور
        for (int i = 1; i <= numCards / 2; i++) {
            Image backImage = new Image("/images/A.png");
            Image frontImage = new Image("/images/image" + i + ".png");

            Card card1 = new Card(i, backImage, frontImage);
            Card card2 = new Card(-i, backImage, frontImage);
            cards.add(card1);
            cards.add(card2);
        }
        Collections.shuffle(cards);
    }

    GameFun() {
    }

    public void showCards() {
        flipCardsTask = new TimerTask() {
            @Override
            public void run() {
                // قم بتغيير صورة كل بطاقة إلى الصورة الخلفية
                for (Card card : cards) {
                    // إنشاء حركة دوران وتحويل الكارت إلى الصورة الخلفية
                    RotateTransition rotateCard = new RotateTransition(Duration.millis(800), card);
                    rotateCard.setAxis(Rotate.Y_AXIS);
                    rotateCard.setFromAngle(0);
                    rotateCard.setToAngle(90);
                    rotateCard.setOnFinished(e -> {
                        card.flipCard();
                        startTime = (System.currentTimeMillis()) / 1000; // تخزين الوقت عند البدء
                        // إنشاء حركة دوران وتحويل الكارت إلى الصورة الأمامية
                        RotateTransition rotateCardBack = new RotateTransition(Duration.millis(800), card);
                        rotateCardBack.setAxis(Rotate.Y_AXIS);
                        rotateCardBack.setFromAngle(90);
                        rotateCardBack.setToAngle(0);
                        rotateCardBack.play();
                    });
                    rotateCard.play();
                }

                // إلغاء تنفيذ المهمة بعد انتهاء وقت العرض
                flipCardsTask.cancel();
                for (Card card : cards) {
                    card.setOnMouseClicked(event -> {
                        handleCardClick(card);
                        GamePage page = new GamePage();
                        page.soundClick();
                    }
                    );
                }
            }
        };

        // قم بتنفيذ المهمة وتخزينها في المتغير flipCardsTask
        Timer timer = new Timer();
        timer.schedule(flipCardsTask, showTime);

        // عرض البطاقات بصورتها الأمامية
        for (Card card : cards) {
            card.getFrontImageView().setVisible(true);
            card.getBackImageView().setVisible(false);
        }
    }

    public void handleCardClick(Card card) {
        // إظهار صورة البطاقة الأمامية وإخفاء الخلفية
        card.getFrontImageView().setVisible(true);
        card.getBackImageView().setVisible(false);
        if (selectedCards.size() == 0) {
            selectedCards.add(card);
        } else if (selectedCards.size() == 1) {

            selectedCards.add(card);
            Card firstCard = selectedCards.get(0);
            Card secondCard = selectedCards.get(1);
            if (firstCard.gtId() == secondCard.gtId()) {
                selectedCards.remove(secondCard);
            } else {
                numIteration++;

                checkForMatch(firstCard, secondCard);
            }

            if (score == numCards / 2) {
                endTime = (System.currentTimeMillis()) / 1000;
                GamePage page = new GamePage();
                page.getTimer().stop();
                winPage winPage = new winPage();
                winPage.start(new Stage());

            }

        }
    }

    private void checkForMatch(Card firstCard, Card secondCard) {
        if (firstCard.gtId() == -1 * (secondCard.gtId())) {

            score += 1;
            soundCardMatched();

            RotateTransition rt1 = new RotateTransition(Duration.millis(400), firstCard);
            rt1.setAxis(Rotate.Y_AXIS);
            rt1.setToAngle(90);
            rt1.setOnFinished(e -> {

                firstCard.setVisible(false);

                firstCard.setRotate(0); // إعادة تعيين زاوية الدوران إلى 0
                RotateTransition rt2 = new RotateTransition(Duration.millis(400), secondCard);
                rt2.setAxis(Rotate.Y_AXIS);
                rt2.setToAngle(90);
                rt2.setOnFinished(e2 -> {

                    secondCard.setVisible(false);
                    secondCard.setRotate(0); // إعادة تعيين زاوية الدوران إلى 0
                });
                rt2.play();
            });

            rt1.play();
            selectedCards.clear();

        } else {

            soundCardNoMatched();

            RotateTransition rt1 = new RotateTransition(Duration.millis(250), firstCard.getBackImageView());
            rt1.setToAngle(90);
            rt1.setOnFinished(e2 -> {

                firstCard.getFrontImageView().setVisible(false);
                firstCard.getBackImageView().setVisible(true);
                RotateTransition rt2 = new RotateTransition(Duration.millis(250), firstCard.getBackImageView());
                rt2.setToAngle(0);
                rt2.play();
            }
            );
            RotateTransition rt3 = new RotateTransition(Duration.millis(250), secondCard.getBackImageView());
            rt3.setToAngle(90);
            rt3.setOnFinished(e2 -> {

                RotateTransition rt4 = new RotateTransition(Duration.millis(250), secondCard.getBackImageView());
                rt4.setToAngle(0);
                rt4.play();
                secondCard.getFrontImageView().setVisible(false);
                secondCard.getBackImageView().setVisible(true);

            });
            SequentialTransition seqT = new SequentialTransition(rt1, rt3);
            seqT.play();
            selectedCards.clear();

        }
    }

    public int getNumCards() {
        return numCards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getnumIteration() {
        return numIteration;
    }

    public void resetnumIteration() {
        numIteration = 0;
    }

    public void setshowTime(int num) {
        this.showTime = num;
    }

    public long getTimegame() {
        return endTime - startTime;
    }

    public void soundCardNoMatched() {
        File soundFile = new File("src/sound/mixkit-arcade-retro-game-over-213.wav");
        Media sound = new Media(soundFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void soundCardMatched() {
        File soundFile = new File("src/sound/mixkit-winning-a-coin-video-game-2069.wav");
        Media sound = new Media(soundFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

}
