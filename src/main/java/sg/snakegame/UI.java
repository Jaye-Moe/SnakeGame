package sg.snakegame;

import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Random;

public class UI {
    double moveIncrement;
    double startingXPos;
    double startingYPos;

    public UI(double moveIncrement, double startingXPos, double startingYPos){
        this.moveIncrement = moveIncrement;
        this.startingXPos = startingXPos;
        this.startingYPos = startingYPos;
    }

    public void generateSnake(@NotNull ArrayList<Snake> segments){
        Snake snake = new Snake();
        snake.setHeadImage();
        segments.add(snake);

        int y = 3;
        while(y>0){
            snake = new Snake();
            segments.add(snake);
            y--;
        }
    }

    public void addSegment(@NotNull ArrayList<Snake> segments, @NotNull Pane pane){
        Snake snake = new Snake();
        int length = segments.size();
        double xPos = segments.get(length-1).getXPos();
        double yPos = segments.get(length-1).getYPos();
        snake.getSegment().setTranslateY(yPos);
        snake.getSegment().setTranslateX(xPos);
        snake.updateYPos(yPos);
        snake.updateXPos(xPos);
        segments.add(snake);
        pane.getChildren().add(snake.getSegment());
    }

    public void addSegmentsToScreen(@NotNull ArrayList<Snake> segments, @NotNull Pane pane){
        for (Snake segment : segments) {
            pane.getChildren().add(segment.getSegment());
        }
    }

    public void setStartingPositions(@NotNull ArrayList<Snake> segments){
        int length = segments.size();
        int y= 0;
        while (y <length){
            segments.get(y).getSegment().setTranslateY(startingYPos+(moveIncrement*y));
            segments.get(y).getSegment().setTranslateX(startingXPos);
            segments.get(y).updateYPos(startingYPos+(moveIncrement*y));
            y++;
        }
    }

    public void generateWalls(@NotNull ArrayList<Obstacle> obstacles, @NotNull Pane pane){
        Obstacle leftWall = new Obstacle(0,30,moveIncrement,600);
        Obstacle rightWall = new Obstacle(590,30,moveIncrement,600);
        Obstacle topWall = new Obstacle(0,30,600, moveIncrement);
        Obstacle bottomWall = new Obstacle(0,590,600,10);

        pane.getChildren().add(leftWall.getObstacle());
        pane.getChildren().add(rightWall.getObstacle());
        pane.getChildren().add(topWall.getObstacle());
        pane.getChildren().add(bottomWall.getObstacle());

        obstacles.add(leftWall);
        obstacles.add(rightWall);
        obstacles.add(topWall);
        obstacles.add(bottomWall);
    }

    public boolean checkForCollisionWithWalls(@NotNull ArrayList<Snake> segments){
        if(segments.get(0).getSegment().getTranslateX() + segments.get(0).getHorizontalDistanceToMove() == 0){
            System.out.println("hit left wall");
            return true;
        }
        if(segments.get(0).getSegment().getTranslateX() + segments.get(0).getHorizontalDistanceToMove() == 590){
            System.out.println("hit right wall");
            return true;
        }
        if(segments.get(0).getSegment().getTranslateY() + segments.get(0).getVerticalDistanceToMove() == 30){
            System.out.println("hit top wall");
            return true;
        }
        if(segments.get(0).getSegment().getTranslateY() + segments.get(0).getVerticalDistanceToMove() == 590){
            System.out.println("hit bottom wall");
            return true;
        }
        return false;
    }

    public void checkForCollisionWithFood(@NotNull ArrayList<Snake> segments, @NotNull Food food, ScoreBoard scoreBoard, Pane pane){
        if(segments.get(0).getXPos() == food.getXPos() && segments.get(0).getYPos() == food.getYPos()){
            System.out.println("Ate Food");
            scoreBoard.increaseScore();
            this.addSegment(segments, pane);
            moveFood(segments, food);
        }
    }

    public void moveFood(@NotNull ArrayList<Snake> segments, Food food) {
        Random rand = new Random();


        double newXForFood;
        double newYForFood;

        while (true) {
            int newX = rand.nextInt(58) + 1;
            int newY = rand.nextInt(55) + 4;
            newXForFood = newX * 10;
            newYForFood = newY * 10;
            int y = segments.size();
            boolean needToMoveFood = false;

            while (y > 0) {
                if (segments.get(y - 1).getYPos() == newYForFood && segments.get(y - 1).getXPos() == newXForFood) {
                    System.out.println("Food on snake at : " + newXForFood + ", " + newYForFood + " moving food");
                    needToMoveFood = true;
                }
                y--;
            }

            if(!needToMoveFood){
                System.out.println("Placing food at: " + newXForFood + ", " + newYForFood);
                break;
            }

        }

        food.getFood().setTranslateX(newXForFood);
        food.setXPos(newXForFood);
        food.getFood().setTranslateY(newYForFood);
        food.setYPos(newYForFood);
    }

    public void moveSnakeForward(@NotNull ArrayList<Snake> segments){
        int numberOfSegments = segments.size() - 1;
        System.out.println(numberOfSegments);

        while (numberOfSegments > 0) {
            segments.get(numberOfSegments).getSegment().setTranslateY(segments.get(numberOfSegments - 1).getYPos());
            segments.get(numberOfSegments).getSegment().setTranslateX(segments.get(numberOfSegments - 1).getXPos());
            segments.get(numberOfSegments).updateYPos(segments.get(numberOfSegments).getSegment().getTranslateY());
            segments.get(numberOfSegments).updateXPos(segments.get(numberOfSegments).getSegment().getTranslateX());
            numberOfSegments--;
        }

        segments.get(0).getSegment().setTranslateY(segments.get(0).getYPos() + segments.get(0).getVerticalDistanceToMove());
        segments.get(0).updateYPos(segments.get(0).getSegment().getTranslateY());
        segments.get(0).getSegment().setTranslateX(segments.get(0).getXPos() + segments.get(0).getHorizontalDistanceToMove());
        segments.get(0).updateXPos(segments.get(0).getSegment().getTranslateX());
    }


}
