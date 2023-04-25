/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controler.Messages;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 18mt4
 */
public class HistoriqueController implements Initializable {

    @FXML
    private ListView<String> listeview;
    @FXML
    private AnchorPane pare;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Création de la requête SQL pour récupérer les données de la table "messages"
    String sql = "SELECT * FROM messages";

    // Connexion à la base de données et exécution de la requête
    try (
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clients", "root", "root");
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        // Itération sur les résultats et ajout de chaque ligne dans la listeView
        while (rs.next()) {
            String expediteur = rs.getString("expediteur");
            String destinateur = rs.getString("destinateur");
            String content = rs.getString("content");
            String timestamp = rs.getTimestamp("timestamp").toString();
            String message = expediteur + " -> " + destinateur + " : " + content + " (" + timestamp + ")";
            listeview.getItems().add(message);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }    

    @FXML
    private void fermer(MouseEvent event) {
         pare.getScene().getWindow().hide();
    }
    
}
