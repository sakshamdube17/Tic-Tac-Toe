package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {
    private Label playerXLabel ,playerOLabel ;

    private int playerXScore=0,playerOScore=0;
    private boolean playerXTurn=true;
    Button [][]buttons = new Button[3][3];
    BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        //Title
        Label titleLabel = new Label();
        titleLabel.setText("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size : 35pt; -fx-font-weight : bold");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        //Board
        GridPane gridPane=new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold");
                button.setOnAction(event -> buttonClicked(button));
                buttons[i][j]=button;
                gridPane.add(button,j,i);
            }
        }
       root.setCenter(gridPane);
        //Score
        HBox scoreBoard = new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXLabel = new Label("Player X : 0");
        playerXLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold");
        playerOLabel = new Label("Player O : 0");
        playerOLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold");
        scoreBoard.getChildren().addAll(playerXLabel,playerOLabel);
        root.setBottom(scoreBoard);
        return root;
    }

    private void buttonClicked(Button button){
        if(button.getText().equals("")) {
            if (playerXTurn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            playerXTurn = !playerXTurn;
            checkWinner();
        }
        return;
    }

    private void checkWinner(){
        //row
        for (int i = 0; i < 3; i++) {
           if( buttons[i][0].getText().equals(buttons[i][1].getText())
                   && buttons[i][1].getText().equals(buttons[i][2].getText())
           && !buttons[i][0].getText().isEmpty()){
               String winner = buttons[i][0].getText();
               showWinnerDialog(winner);
               updateScore(winner);
               reset();
               return;
           }
        }

        //col
        for (int i = 0; i < 3; i++) {
            if( buttons[0][i].getText().equals(buttons[1][i].getText())
                    && buttons[1][i].getText().equals(buttons[2][i].getText())
                    && !buttons[1][i].getText().isEmpty()){
                String winner = buttons[0][i].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                reset();
                return;
            }
        }

        //dia
        if( buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[1][1].getText().isEmpty()){
            String winner = buttons[1][1].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            reset();
            return;
        }
        if( buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[1][1].getText().isEmpty()){
            String winner = buttons[1][1].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            reset();
            return;
        }


        //tie
        boolean tie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(buttons[i][j].getText().isEmpty()){
                    tie=false;
                }
            }
        }
        if(tie){
            reset();
            showTieDialog();
        }
    }


    private void showWinnerDialog(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulation! " + winner + " is winner");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void showTieDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("GAME OVER ! It's a tie ");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void updateScore(String winner){
        if(winner.equals("X")){
            playerXScore++;
            playerXLabel.setText("Player X: "+ playerXScore);
        } else if (winner.equals("O")) {
            playerOScore++;
            playerOLabel.setText("Player O: " + playerOScore);
        }
    }

    private void reset(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}