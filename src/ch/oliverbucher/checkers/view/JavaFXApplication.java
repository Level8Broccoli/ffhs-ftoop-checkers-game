package ch.oliverbucher.checkers.view;

import ch.oliverbucher.checkers.config.Config;
import ch.oliverbucher.checkers.config.ConfigJavaFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXApplication extends Application implements ApplicationInterface {

    @Override
    public void startApplication() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent rootFxml = FXMLLoader.load(getClass().getResource("JavaFXApplication.fxml"));
        VBox container = new VBox();
        container.getChildren().add(rootFxml);

        HBox footer = new HBox();
        footer.getChildren().add(new Label(Config.VERSION));
        footer.setAlignment(Pos.BOTTOM_RIGHT);
        container.getChildren().add(footer);

        Scene scene = new Scene(container, ConfigJavaFX.WINDOW_WIDTH, ConfigJavaFX.WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.setTitle(Config.GAME_NAME);
        stage.show();
    }
}
