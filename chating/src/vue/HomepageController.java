
package vue;

import controler.Client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MovePane;
public class HomepageController implements Initializable {

    private StackPane stackp;
  
    @FXML
    private BorderPane parent;
    @FXML
    private ListView<?> liste;
    @FXML
    private ScrollPane sp_main;
    @FXML
    private VBox boxMsp;
    @FXML
    private TextField if_message;
    @FXML
    private ImageView send;

     private Client client;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            client = new Client(new Socket("localhost", 2020));
            System.out.println("connecter");
        } catch (Exception e) {
            e.printStackTrace();
        }   
        boxMsp.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            sp_main.setVvalue((Double) newValue);
        });
        client.RecevoirMessageClient(boxMsp);
        send.setOnMouseClicked((MouseEvent event) -> {
            String sendMessage = if_message.getText();
            if (!sendMessage.isEmpty()) {
                HBox hbox = new HBox();
                hbox.setAlignment(Pos.CENTER_RIGHT);
                hbox.setPadding(new Insets(5, 5, 5, 10));
                
                Text text = new Text(sendMessage);
                TextFlow flow = new TextFlow(text);
                flow.setStyle("*{-fx-color:#5CE7CE;" +
                        "-fx-background-color: #15ACF0;" +
                        " -fx-background-radius: 20px;}");
                flow.setPadding(new Insets(5, 5, 5, 10));
                text.setFill(Color.color(0.934, 0.945, 0.996));
                
                hbox.getChildren().add(flow);
                boxMsp.getChildren().add(hbox);
                
                client.sendMessageServer(sendMessage);
                if_message.clear();
            }
        });
    }   
    //message client 
    public static void addLabbel(String MessageClient, VBox vbox)
    {
                    HBox hbox = new HBox();
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    hbox.setPadding(new Insets(5, 5, 5, 10));
                    
                    Text text = new Text(MessageClient);
                    TextFlow flow = new TextFlow(text);
                    flow.setStyle("*{-fx-color:#5CE7CE;" +
                            "-fx-background-color: #E3FD0D;" + 
                            "-fx-background-radius: 20px;}");
                    flow.setPadding(new Insets(5, 5, 5, 10));
                    hbox.getChildren().add(flow);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                             vbox.getChildren().add(hbox);
                        }
                    });
    }
    @FXML
    private void login(MouseEvent event) {
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
       }

    @FXML
    private void exit(MouseEvent event) {
             BufferedReader in = null;
            Socket socket = null;
            BufferedWriter out = null;
        System.exit(0);
        client.close(socket, in, out);
    }
     @FXML
    private void getDragged(MouseEvent event) {
        MovePane.mouv(event);
    }
    @FXML
    private void getMove(MouseEvent event) {
         MovePane.mouvXY(event);
    }

    @FXML
    private void getHistory(MouseEvent event) {
        
      Stage s = new Stage();
            try {
                    Parent fxml = FXMLLoader.load(getClass().getResource("/vue/historique.fxml"));
                        s.setScene(new Scene(fxml));
                        s.initStyle(StageStyle.TRANSPARENT);
                        s.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
    }

}
