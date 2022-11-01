package sg.snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Food {

    private final ImageView image;
    private double xPos;
    private double yPos;

    public Food(){
        Image imageFile = new Image("file:apple.png");
        this.image = new ImageView(imageFile);
        this.xPos = 200;
        this.yPos = 200;
    }

    public ImageView getFood(){
        return this.image;
    }

    public double getXPos() {
        return xPos;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }
}
