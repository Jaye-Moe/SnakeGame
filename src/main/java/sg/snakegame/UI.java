package sg.snakegame;

import javafx.scene.layout.Pane;

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

    public void generateSnake(ArrayList<Snake> segments){
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

    public void addSegmentsToScreen(ArrayList<Snake> segments, Pane pane){
        pane.getChildren().add(segments.get(0).getSegment());
        pane.getChildren().add(segments.get(1).getSegment());
        pane.getChildren().add(segments.get(2).getSegment());
        pane.getChildren().add(segments.get(3).getSegment());
    }

    public void setStartingPositions(ArrayList<Snake> segments, Pane pane){
        segments.get(0).getSegment().setTranslateY(startingYPos);
        segments.get(0).getSegment().setTranslateX(startingXPos);
        segments.get(1).getSegment().setTranslateY(startingYPos+moveIncrement);
        segments.get(1).updateYPos(startingYPos+moveIncrement);
        segments.get(1).getSegment().setTranslateX(startingXPos);
        segments.get(2).getSegment().setTranslateY(startingYPos+moveIncrement+moveIncrement);
        segments.get(2).updateYPos(startingYPos+moveIncrement+moveIncrement);
        segments.get(2).getSegment().setTranslateX(startingXPos);
        segments.get(3).getSegment().setTranslateY(startingYPos+moveIncrement+moveIncrement+moveIncrement);
        segments.get(3).updateYPos(startingYPos+moveIncrement+moveIncrement+moveIncrement);
        segments.get(3).getSegment().setTranslateX(startingXPos);
    }

    public void generateWalls(ArrayList<Obstacle> obstacles, Pane pane){
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

    public boolean checkForCollisionWithWalls(ArrayList<Snake> segments){
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

    public void checkForCollisionWithFood(ArrayList<Snake> segments, Food food, ScoreBoard scoreBoard){
        if(segments.get(0).getXPos() == food.getXPos() && segments.get(0).getYPos() == food.getYPos()){
            System.out.println("Ate Food");
            scoreBoard.increaseScore();
            moveFood(segments, food);
        }
    }

    public void moveFood(ArrayList<Snake> segments, Food food){
        Random rand = new Random();
        int newX = rand.nextInt(58) + 1;
        int newY = rand.nextInt(55)+4;
        double newXForFood = newX * 10;
        double newYForFood = newY * 10;
        food.getFood().setTranslateX(newXForFood);
        food.setXPos(newXForFood);
        food.getFood().setTranslateY(newYForFood);
        food.setYPos(newYForFood);
    }


}
