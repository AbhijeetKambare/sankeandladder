package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize=40,height=10,width=10;

    int lowerLine=tileSize*height;
    int diceValue;
    Label rolledDiceValueLable;
    Label TurnLable;
    Button StartGameButton;
    boolean firstPlayerTurn=true,seconfPlayerTurn=false,gameStarted=false;

    Pane createContent(){
        Pane root=new Pane();

        root.setPrefSize(height*tileSize,width*tileSize+100);

        Player FirstPlayer=new Player(tileSize, Color.BLACK,"Abhijeet");
        Player SecondPlayer=new Player(tileSize-10,Color.WHITE,"Vishwajeet");

        for(int i=0;i<width;i++) {
            for (int j = 0; j < height; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                root.getChildren().add(tile);
            }
        }

                Image img=new Image("C:\\Users\\Lenovo\\Desktop\\accio\\SnakeAndLadder\\src\\main\\snakeladder.jpg");
                ImageView borderImage=new ImageView();
                borderImage.setImage(img);
                borderImage.setFitWidth(tileSize*width);
                borderImage.setFitHeight(height*tileSize);

                 Button playerOneButton =new Button("Player One");
                 playerOneButton.setTranslateX(20);
                 playerOneButton.setTranslateY(lowerLine+20);
                 playerOneButton.setDisable(true);
                 Button playerTwoButton =new Button("Player Two");
                 playerTwoButton.setTranslateX(300);
                 playerTwoButton.setTranslateY(lowerLine+20);
                 playerTwoButton.setDisable(true);
                 playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent actionEvent) {
                         if(gameStarted) {
                             if (seconfPlayerTurn) {
                                 getDiceValue();
                                 SecondPlayer.movePlayer(diceValue);
                                 if (SecondPlayer.PlayerWon() != null) {
                                     rolledDiceValueLable.setText(SecondPlayer.PlayerWon());
                                     firstPlayerTurn=true;
                                     seconfPlayerTurn=false;
                                     gameStarted=false;
                                     StartGameButton.setDisable(false);
                                     StartGameButton.setText("Start Game");
                                     playerTwoButton.setDisable(true);

                                 }
                                 firstPlayerTurn=true;
                                 seconfPlayerTurn=false;
                             }
                         }
                     }
                 });
                 StartGameButton=new Button("Start");
                 StartGameButton.setTranslateY(lowerLine+50);
                 StartGameButton.setTranslateX(140);
                 StartGameButton.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent actionEvent) {
                         gameStarted=true;
                         StartGameButton.setDisable(true);
                         playerTwoButton.setDisable(false);
                         playerOneButton.setDisable(false);
                         StartGameButton.setText("Ongoing Game");
                         rolledDiceValueLable.setText("Game Started ");
                     }
                 });

                 playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent actionEvent) {
                         if(gameStarted) {
                             if (firstPlayerTurn) {
                                 getDiceValue();
                                 FirstPlayer.movePlayer(diceValue);
                                 if (FirstPlayer.PlayerWon() != null) {
                                     rolledDiceValueLable.setText(FirstPlayer.PlayerWon());
                                     firstPlayerTurn=true;
                                     seconfPlayerTurn=false;
                                     gameStarted=false;

                                 }
                                 firstPlayerTurn=false;
                                 seconfPlayerTurn=true;
                             }
                         }
                     }
                 });

                 rolledDiceValueLable=new Label("Start The Game");
                 rolledDiceValueLable.setTranslateX(140);
                 rolledDiceValueLable.setTranslateY(lowerLine+20);
                root.getChildren().addAll(borderImage,playerOneButton,playerTwoButton,
                        FirstPlayer.getCoin(), SecondPlayer.getCoin(),
                        rolledDiceValueLable,
                        StartGameButton);

        return root;
    }

    private void getDiceValue(){

        diceValue=(int)(Math.random()*6+1);
        rolledDiceValueLable.setText("Dice Value :"+diceValue);
    }

    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}