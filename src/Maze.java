import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;

/*Scott Cromling
LAB 8
3/25/20
 */

public class Maze extends Application {

    String m;
    String w;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();
        HBox hbox = new HBox();

        MyHandler mh = new MyHandler();

        m = JOptionPane.showInputDialog("Enter width of maze");
        w = JOptionPane.showInputDialog("Enter height of maze");
        if (m.isEmpty() || w.isEmpty()) {
            System.exit(0);
        } else {
            for (int i = 0; i < Integer.parseInt(m); i++) {
                for (int j = 0; j < Integer.parseInt(w); j++) {
                    Button b = new Button();
                    b.setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                    b.setOnAction(mh);
                    b.setPrefHeight(30);
                    b.setPrefWidth(30);
                    getColorChar(b);
                    grid.add(b, i, j);
                }
            }

            Button s = new Button("Save");
            Button c = new Button("Exit");
            Button p = new Button("Print");
            s.setOnAction(mh);
            c.setOnAction(mh);
            p.setOnAction(mh);
            hbox.getChildren().addAll(s, c, p);

            grid.setAlignment(Pos.CENTER);
            hbox.setAlignment(Pos.CENTER);
            root.setCenter(grid);
            root.setBottom(hbox);

            Scene scene = new Scene(root, 300, 250);

            primaryStage.setTitle("Maze");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public class MyHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
//e.getSource() returns the button that was clicked.
            Button b = (Button) e.getSource();

            if (b.getText().equals("Save")) {
//create output file
                try {
                    JFileChooser f = new JFileChooser();
                    int retVal = f.showSaveDialog(null);
                    if (retVal == JFileChooser.APPROVE_OPTION) {
                        File file = f.getSelectedFile();
                        PrintWriter p = new PrintWriter(file);
                        String dem =  m + " x " + w;
                        p.println(dem);

                        for (int i = 0; i < Integer.parseInt(m); i++) {
                            for (int j = 0; j < Integer.parseInt(w); j++) {

                                if (getColorChar(b) == '0') {
                                    p.print("0");
                                }  if (getColorChar(b) == '1') {
                                    p.print("1");
                                }  if (getColorChar(b) == 'S') {
                                    p.print("S");
                                }  if(getColorChar(b) == 'E') {
                                    p.print("E");
                                }
                            }
                            p.println();
                        }
                        p.close();
                    }
                    System.exit(0);
                } catch (java.io.IOException a) {
                    System.err.println("Error");
                }

            } else if (b.getText().equals("Exit")) {
//exit the application
                System.exit(0);
            } else { //a maze button was clicked

                char val = getColorChar(b);
                if (val == '0') { //the button is currently blue so change it to white
                    b.setStyle("-fx-background-color: white;");
                } else if (val == '1') {
//TODO: Set background color to green
                    b.setStyle("-fx-background-color: green; -fx-border-width: 2px; -fx-border-color: black;");
                } else if (val == 'S') {
//TODO: Set background color to red
                    b.setStyle("-fx-background-color: red; -fx-border-width: 2px; -fx-border-color: black;");
                } else {
//TODO: Set background color back to blue
                    b.setStyle("-fx-background-color: blue; -fx-border-width: 2px; -fx-border-color: black;");
                }

            }
        }
    }

    private char getColorChar(Button b) {
        if (b.getStyle().contains("blue")) {
            return '0';
        } else if (b.getStyle().contains("white")) {
            return '1';
        } else if (b.getStyle().contains("green")) {
            return 'S';
        } else {
            return 'E';
        }
    }

}





