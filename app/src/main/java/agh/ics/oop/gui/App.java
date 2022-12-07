package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class App extends Application {
    AbstractWorldMap map;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("y\\x");
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
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

        for(int i = x_low; i <= x_high; ++i){
            label = new Label(String.valueOf(i));
            grid.add(label, index, 0);
            grid.getColumnConstraints().add(new ColumnConstraints(40));
            GridPane.setHalignment(label, HPos.CENTER);
            index -= -1;
        }

        index = 0;
        for(int i = y_high; i >= y_low; --i){
            label = new Label(String.valueOf(i));
            grid.add(label, 0, index+1);
            grid.getRowConstraints().add(new RowConstraints(40));
            GridPane.setHalignment(label, HPos.CENTER);
            index -= -1;
        }

        for(int i = x_low; i <= x_high; ++i){
            for(int j = y_high; j >= y_low; --j){
                Vector2d pos = new Vector2d(i, j);
                if (map.isOccupied(pos)){
                    var obj = map.objectAt(pos);
                    label = new Label(obj.toString());

                    Color color = obj instanceof Grass ? Color.color(0, 0.6, 0) : Color.color(1, 0, 0);
                    label.setTextFill(color);
                    label.setFont(new Font(30));

                    grid.add(label, i + 1 - x_low, y_high - j + 2 + y_low);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }

        int y_size = grid.getRowConstraints().size() * 40;
        int x_size = grid.getColumnConstraints().size() * 40;
        Scene scene = new Scene(grid, x_size, y_size);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        try {
            MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw());
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2, 2), new Vector2d(3, 4) };
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        } catch (Exception e) {
            System.out.println("Caught exception:");
            System.out.println(e.toString());
        }
    }
}
