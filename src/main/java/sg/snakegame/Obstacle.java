package sg.snakegame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle {
    private final Rectangle rectangle;

    public Obstacle(double xPos, double yPos, double width, double height){
        this.rectangle = new Rectangle(xPos, yPos, width, height);
        this.rectangle.setFill(Color.YELLOW);
    }

    public Rectangle getObstacle() {
        return rectangle;
    }
}
