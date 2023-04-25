

package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;


public class MovePane {
    static  double i = 0, y = 0;
    
    public static void mouv(MouseEvent event)
    {
        
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        stage.setX(event.getScreenX()-i);
        stage.setY(event.getScreenY()-y);
    }
      public static void mouvXY(MouseEvent event)
    {
             i = event.getSceneX();
            y = event.getSceneY();
    }
    
    public  Stage stage(String page)
    {   
        Stage s = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(page+".fxml"));
            s.setScene(new Scene(root));
            s.initStyle(StageStyle.TRANSPARENT);
            s.show();
          
            return s;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
    }
    public  Stage stage1(String page, String titre)
    {   
        Stage s = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(page+".fxml"));
            s.setScene(new Scene(root));
            s.initStyle(StageStyle.DECORATED);
            s.setTitle(titre);
            s.show();
          
            return s;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
    }
    public void verifier(String title, String mesaage)
    {
      
         Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mesaage);
        styleAlert(alert);
        alert.showAndWait();
    }
     public void verifie(String title, String mesaage)
    {
      
         Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mesaage);
        styleAlert(alert);
        alert.showAndWait();
    }
    
    
    private static void styleAlert(Alert alert) {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(MovePane.class.getResource("/mouvement/dark-theme.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");
    }
}
