package agh.ics.oop.gui;

import java.io.FileNotFoundException;
import java.util.Arrays;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class App extends Application implements IStepObserver {
    AbstractWorldMap map;
    Scene scene;
    Stage stage;
    GridPane grid;
    HBox buttonBox;
    SimulationEngine engine;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        buttonBox = new HBox();
        Button button = new Button("Start");
        TextField textField = new TextField();

        button.setOnAction(action -> {

            if (textField.getText().length() > 0) {
                MoveDirection[] directions = OptionsParser.parse(Arrays.asList(textField.getText().split(" ")));
                engine.setDirections(directions);
            }
            Thread engineThread = new Thread(engine);
            engineThread.start();
        });

        buttonBox.getChildren().addAll(button, textField);

        update();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        grid = new GridPane();
        try {
            MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw());
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2, 2), new Vector2d(3, 4) };
            engine = new SimulationEngine(directions, map, positions);
            engine.addObserver(this);
        } catch (Exception e) {
            System.out.println("Caught exception:");
            System.out.println(e.toString());
        }
    }

    public void update() throws FileNotFoundException {
        this.grid.setGridLinesVisible(false);
        this.grid.getColumnConstraints().clear();
        this.grid.getRowConstraints().clear();
        this.grid.getChildren().clear();
        this.grid.setGridLinesVisible(true);

        Label label = new Label("y\\x");
        GridPane.setHalignment(label, HPos.CENTER);
        grid.add(label, 0, 0);
        grid.getRowConstraints().add(new RowConstraints(40));
        grid.getColumnConstraints().add(new ColumnConstraints(40));

        Vector2d[] bounds = map.getBounds();

        int x_low = bounds[0].x;
        int y_low = bounds[0].y;
        int x_high = bounds[1].x;
        int y_high = bounds[1].y;

        int index = 1;
        for (int i = x_low; i <= x_high; ++i) {
            label = new Label(String.valueOf(i));
            grid.add(label, index, 0);
            grid.getColumnConstraints().add(new ColumnConstraints(40));
            GridPane.setHalignment(label, HPos.CENTER);
            index -= -1;
        }

        index = 0;
        for (int i = y_high; i >= y_low; --i) {
            label = new Label(String.valueOf(i));
            grid.add(label, 0, index + 1);
            grid.getRowConstraints().add(new RowConstraints(40));
            GridPane.setHalignment(label, HPos.CENTER);
            index -= -1;
        }

        for (int i = x_low; i <= x_high; ++i) {
            for (int j = y_high; j >= y_low; --j) {
                Vector2d pos = new Vector2d(i, j);
                if (map.isOccupied(pos)) {
                    IMapElement obj = (IMapElement) map.objectAt(pos);

                    GuiElementBox guiElementBox = new GuiElementBox(obj);
                    VBox vbox = guiElementBox.getVBox();

                    grid.add(vbox, i + 1 - x_low, y_high - j + 2 + y_low);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }

        int y_size = 800;
        int x_size = 800;

        VBox vbox = new VBox();
        vbox.getChildren().addAll(grid, buttonBox);

        scene = new Scene(vbox, x_size, y_size);
        stage.setScene(scene);
        stage.show();
    }

    public void onStep() {
        try {
            Platform.runLater(() -> {
                try {
                    update();
                } catch (FileNotFoundException e) {
                    System.out.println("Error loading resource.");
                    e.printStackTrace();
                }
            });
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println("Interruped");
            e.printStackTrace();
        }
    }
}
