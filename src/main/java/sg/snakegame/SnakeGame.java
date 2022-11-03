/*

TODO:   High score
TODO:   Increase speed after food is eaten
TODO:   Add more levels after X points
TODO:   Add multiplayer

 */

package sg.snakegame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SnakeGame extends Application {
    @Override
    public void start(Stage stage) {
        System.out.println("Hello World");

        double moveIncrement = 10;
        double startingXPos = 300;
        double startingYPos = 300;
        double windowHeight = 600;
        double windowWidth = 600;
        final int[] timesPlayed = {0};
        final boolean[] gameIsOn = {false};

        ArrayList<Snake> segments = new ArrayList<>();
        ArrayList<Obstacle> obstacles = new ArrayList<>();

        Pane pane = new Pane();
        pane.setPrefSize(windowWidth,windowHeight);
        pane.setStyle("-fx-background-color: black;");

        UI ui = new UI(moveIncrement, startingXPos, startingYPos);

        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.generateScoreBoard();
        scoreBoard.startingInstructions();

        pane.getChildren().add(scoreBoard.getScoreBoard());
        Food food = new Food();

        startGame(pane,  scoreBoard, segments, ui, food, obstacles);
        scoreBoard.startingInstructions();

        food.getFood().setTranslateX(food.getXPos());
        food.getFood().setTranslateY(food.getYPos());

        Scene scene = new Scene(pane);

        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        scene.setOnKeyPressed(event -> pressedKeys.put(event.getCode(), Boolean.TRUE));
        scene.setOnKeyReleased(event -> pressedKeys.put(event.getCode(), Boolean.FALSE));

        new AnimationTimer(){
            @Override
            public void handle (long now) {
//                    if(pressedKeys.getOrDefault(KeyCode.SPACE, false)) {
//                        ui.addSegment(segments, pane);
//                    }
                    if(pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                        if (!(segments.get(0).getHorizontalDistanceToMove() == moveIncrement)) {
                            segments.get(0).setHorizontalDistanceToMove(-moveIncrement);
                            segments.get(0).setVerticalDistanceToMove(0);
                        }
                    }
                    if(pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                        if (!(segments.get(0).getHorizontalDistanceToMove() == -moveIncrement)) {
                            segments.get(0).setHorizontalDistanceToMove(moveIncrement);
                            segments.get(0).setVerticalDistanceToMove(0);
                        }
                    }
                    if(pressedKeys.getOrDefault(KeyCode.UP, false)) {
                        if (!(segments.get(0).getVerticalDistanceToMove() == moveIncrement)) {
                            segments.get(0).setHorizontalDistanceToMove(0);
                            segments.get(0).setVerticalDistanceToMove(-moveIncrement);
                        }
                    }
                    if(pressedKeys.getOrDefault(KeyCode.DOWN, false)) {
                        if (!(segments.get(0).getVerticalDistanceToMove() == -moveIncrement)) {
                            segments.get(0).setHorizontalDistanceToMove(0);
                            segments.get(0).setVerticalDistanceToMove(moveIncrement);
                        }
                    }

//                    if(pressedKeys.getOrDefault(KeyCode.F, false)) {
//                        ui.moveFood(segments, food);
//                    }

                    if (pressedKeys.getOrDefault(KeyCode.ENTER, false)) {
                        if(!(gameIsOn[0])) {
                            startGame(pane, scoreBoard, segments, ui, food, obstacles);

                            if(timesPlayed[0] >0){
                                ui.moveFood(segments, food);
                            }
                            timesPlayed[0]++;

                            gameIsOn[0] = true;
                        }
                    }

                    if(gameIsOn[0]) {
                        if (ui.checkForCollisionWithWalls(segments)) {
                            System.out.println("GAME OVER");
                            gameIsOn[0] = false;
                            scoreBoard.gameOver();
                        } else {
                            ui.moveSnakeForward(segments);
                        }

                        ui.checkForCollisionWithFood(segments, food, scoreBoard, pane);
                        System.out.println("X-POS: " + segments.get(0).getXPos());
                        System.out.println("Y-POS: " + segments.get(0).getYPos());

                        try {
                            Thread.sleep(70);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
            }
            }.start();
            stage.setScene(scene);
            stage.show();
        }

    public static void main(String[] args) {
        launch();
    }

    public void startGame(@NotNull Pane pane, @NotNull ScoreBoard scoreBoard, @NotNull ArrayList<Snake> segments,
                          @NotNull UI ui, @NotNull Food food, @NotNull ArrayList<Obstacle> obstacles){
        pane.getChildren().clear();
        scoreBoard.resetScore();
        segments.clear();
        ui.generateSnake(segments);
        ui.addSegmentsToScreen(segments, pane);
        ui.setStartingPositions(segments);
        obstacles.clear();
        ui.generateWalls(obstacles, pane);
        pane.getChildren().add(scoreBoard.getScoreBoard());
        pane.getChildren().add(food.getFood());

    }
}