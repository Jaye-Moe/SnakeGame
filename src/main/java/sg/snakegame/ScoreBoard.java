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
    private final NumberFormat nf;

    public ScoreBoard(){
        this.scoreBoard = new Label();
        this.score = 0;
        this.nf = NumberFormat.getInstance();
    }

    public void generateScoreBoard(){
        this.scoreBoard.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
        this.scoreBoard.setTextFill(Color.CYAN);
        this.scoreBoard.setText("Score: " + this.nf.format(this.score));
    }

    public Label getScoreBoard(){
        return this.scoreBoard;
    }

    public void increaseScore(){
        this.score++;
        this.scoreBoard.setText("Score: " + this.nf.format(this.score));
    }

    public void resetScore(){
        this.score = 0;
    }


}
