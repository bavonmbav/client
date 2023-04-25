
package vue;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import serveurs.MovePane;
import serveurs.Serveur;


public class ServerController implements Initializable {


   
   
    private ScrollPane lScrolListe;
    @FXML
    private TextField TextMessage;
    @FXML
    private Label serCon;
    @FXML
    private Circle decon;
    @FXML
    private ListView<String> ListeVue;
    
    
    private Serveur server;
    private ObservableList<String>connectedUsersObservableList = FXCollections.observableArrayList();;
    @FXML
    private ImageView send;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectedUsersObservableList = FXCollections.observableArrayList();
        ListeVue.setItems(connectedUsersObservableList);  
    } 
    
    @FXML
    private void getStart() {
        Serveur c = new Serveur(ListeVue);
           c.Star(); 
         decon.setVisible(true);
         serCon.setText("Serveur connecter");
           
    }
      //envoyer le message aux client   
   
    
    @FXML
    private void getDragged(MouseEvent event) {
        MovePane.mouv(event);
    }
    @FXML
    private void getMove(MouseEvent event) {
         MovePane.mouvXY(event);
    }

    @FXML
    private void getStop(ActionEvent event) {
        System.exit(0);
    }
    
    }
