
package serveurs;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Serveurs extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root ;
        FXMLLoader racine = new FXMLLoader(getClass().getResource("/vue/server.fxml"));
        root = racine.load();
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
