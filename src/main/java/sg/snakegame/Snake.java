package sg.snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Snake {
    private Image imageFile;
    private ImageView image;
    private double xPos;
    private double yPos;
    private double verticalDistanceToMove;
    private double horizontalDistanceToMove;

    public Snake(){
        this.imageFile = new Image("file:dot.png");
        this.image = new ImageView(this.imageFile);
        this.xPos = 100;
        this.yPos = 100;
        this.verticalDistanceToMove = 0;
        this.horizontalDistanceToMove = 0;

    }

    public void setHeadImage(){
        this.imageFile = new Image("file:head.png");
        this.image = new ImageView(this.imageFile);
    }

    public ImageView getSegment(){
        return this.image;
    }

    public double getXPos(){
        return this.xPos;
    }

    public double getYPos(){
        return this.yPos;
    }

    public void updateXPos(double xPos){
        this.xPos = xPos;
    }

    public void updateYPos(double yPos){
        this.yPos = yPos;
    }

    public double getVerticalDistanceToMove() {
        return verticalDistanceToMove;
    }

    public double getHorizontalDistanceToMove() {
        return horizontalDistanceToMove;
    }

    public void setVerticalDistanceToMove(double verticalDistanceToMove) {
        this.verticalDistanceToMove = verticalDistanceToMove;
    }

    public void setHorizontalDistanceToMove(double horizontalDistanceToMove) {
        this.horizontalDistanceToMove = horizontalDistanceToMove;
    }
}
