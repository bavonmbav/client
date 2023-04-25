
package vue;

import chating.slash;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class SlashController implements Initializable {

    @FXML
    private AnchorPane parent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slash.boucheApp(parent, Duration.seconds(2), (e)->{
          Stage s = new Stage();
            try {
                    Parent fxml = FXMLLoader.load(getClass().getResource("/vue/login.fxml"));
                        parent.getScene().getWindow().hide();
                        s.setScene(new Scene(fxml));
                        s.initStyle(StageStyle.TRANSPARENT);
                        s.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
     
    }    
    
}
