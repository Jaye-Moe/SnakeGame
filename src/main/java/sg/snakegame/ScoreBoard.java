package sg.snakegame;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.text.NumberFormat;

public class ScoreBoard {
    private final Label scoreBoard;
    private int score;
    private int level;
    private final NumberFormat nf;

    public ScoreBoard(){
        this.scoreBoard = new Label();
        this.score = 0;
        this.level = 1;
        this.nf = NumberFormat.getInstance();
    }

    public void generateScoreBoard(){
        this.scoreBoard.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
        this.scoreBoard.setTextFill(Color.CYAN);
        this.displayScore();

    }

    public void displayScore(){
        this.scoreBoard.setText("Score: " + this.nf.format(this.score) +"               Level: " + this.level);
    }

    public Label getScoreBoard(){
        return this.scoreBoard;
    }

    public void increaseScore(){
        this.score++;
        this.displayScore();
    }

    public void resetScore(){
        this.score = 0;
        this.displayScore();
    }

    public void gameOver(){
        this.scoreBoard.setText("Score: " + this.nf.format(this.score) +"               Level: " + this.level +
                "          GAME OVER - Press Enter to Reset");
    }

    public void startingInstructions(){
        this.scoreBoard.setText("Score: " + this.nf.format(this.score) +"               Level: " + this.level +
                "          Press Enter to Start");
    }


}
