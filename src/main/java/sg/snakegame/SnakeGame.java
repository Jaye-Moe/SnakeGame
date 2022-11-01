/*
DONE:   Add walls
DONE:   Add scoreboard
DONE:   Add food
TODO:   Add additional segments
TODO:   Collisions with:
TODO:       self
DONE:       wall
DONE:       food
TODO:   Play again
TODO:   High score
TODO:   Move forward on own
DONE:   Don't allow direction to reverse
DONE:   Move food when eaten
TODO:   Don't let food spawn on walls (DONE) or snake (NEED)
TODO:   Increase speed after food is eaten
TODO:   Add more levels after X points

 */




package sg.snakegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SnakeGame extends Application {
    @Override
    public void start(Stage stage) {
        System.out.println("Hello World");

        double moveIncrement = 10;
        double startingXPos = 100;
        double startingYPos = 100;
        double windowHeight = 600;
        double windowWidth = 600;
        boolean gameIsOn = true;

        ArrayList<Snake> segments = new ArrayList<>();
        ArrayList<Obstacle> obstacles = new ArrayList<>();

        Pane pane = new Pane();
        pane.setPrefSize(windowWidth,windowHeight);
        pane.setStyle("-fx-background-color: black;");

        UI ui = new UI(moveIncrement, startingXPos, startingYPos);

        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.generateScoreBoard();

        pane.getChildren().add(scoreBoard.getScoreBoard());


        ui.generateSnake(segments);
        ui.addSegmentsToScreen(segments, pane);
        ui.setStartingPositions(segments,pane);
        ui.generateWalls(obstacles, pane);

        Food food = new Food();
        pane.getChildren().add(food.getFood());
        food.getFood().setTranslateX(food.getXPos());
        food.getFood().setTranslateY(food.getYPos());

        Scene scene = new Scene(pane);



        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                if(!(segments.get(0).getHorizontalDistanceToMove() ==moveIncrement)){
                    segments.get(0).setHorizontalDistanceToMove(-moveIncrement);
                    segments.get(0).setVerticalDistanceToMove(0);
                }
            }

            if (event.getCode() == KeyCode.RIGHT) {
                if(!(segments.get(0).getHorizontalDistanceToMove() ==-moveIncrement)){
                    segments.get(0).setHorizontalDistanceToMove(moveIncrement);
                    segments.get(0).setVerticalDistanceToMove(0);
                }
            }


            if (event.getCode() == KeyCode.UP) {
                if(!(segments.get(0).getVerticalDistanceToMove() == moveIncrement)){
                    segments.get(0).setHorizontalDistanceToMove(0);
                    segments.get(0).setVerticalDistanceToMove(-moveIncrement);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if(!(segments.get(0).getVerticalDistanceToMove() == -moveIncrement)) {
                    segments.get(0).setHorizontalDistanceToMove(0);
                    segments.get(0).setVerticalDistanceToMove(moveIncrement);
                }
            }

            if (event.getCode() == KeyCode.ENTER) {

                if (ui.checkForCollisionWithWalls(segments)) {

                } else {

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
            ui.checkForCollisionWithFood(segments, food, scoreBoard);
            System.out.println("X-POS: " + segments.get(0).getXPos());
            System.out.println("Y-POS: " + segments.get(0).getYPos());



        });




        stage.setScene(scene);
        stage.show();





    }

    public static void main(String[] args) {
        launch();
    }
}