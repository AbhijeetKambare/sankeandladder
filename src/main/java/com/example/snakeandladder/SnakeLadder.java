package com.example.snakeandladder;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize=40,height=10,width=10;
    Pane createContent(){
        Pane root=new Pane();

        root.setPrefSize(height*tileSize,width*tileSize+50);

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
                root.getChildren().add(borderImage);


        return root;
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