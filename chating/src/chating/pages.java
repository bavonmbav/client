
package chating;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class pages {

          
            private Stage s = new Stage();
            //chargement de la page FXML
            public void page(String stages, AnchorPane pare ) throws IOException
            {
                   Parent fxml = FXMLLoader.load(getClass().getResource(stages));
                        pare.getScene().getWindow().hide();
                        s.setScene(new Scene(fxml));
                        s.initStyle(StageStyle.TRANSPARENT);
                        s.show();
            }
               public void pagess(String stages, AnchorPane pare ) throws IOException
            {
                   Parent fxml = FXMLLoader.load(getClass().getResource(stages));
                        pare.getScene().getWindow().hide();
                        s.setScene(new Scene(fxml));
                        s.initStyle(StageStyle.TRANSPARENT);
                        s.show();
            }
                 public void boxs(String stages, VBox pare ) throws IOException
            {
                   Parent fxml = FXMLLoader.load(getClass().getResource(stages));
                        s.setScene(new Scene(fxml));
                        s.initStyle(StageStyle.TRANSPARENT);
                        s.show();
            }
            
            public void getpasse()
            {
                
            }
                public void verifier(String title, String mesaage)
    {
      
         Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mesaage);
        styleAlert(alert);
        alert.showAndWait();
    }
     public void verifie(String title, String mesaage)
    {
      
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mesaage);
        styleAlert(alert);
        alert.showAndWait();
    }
     
        private static void styleAlert(Alert alert) {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(pages.class.getResource("/styles/style.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");
    }
}
