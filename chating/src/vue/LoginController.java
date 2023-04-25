
package vue;

import chating.pages;
import controler.Client;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.MovePane;
import model.Utilisateur;


public class LoginController implements Initializable {

    @FXML
    private TextField Maile;
    @FXML
    private TextField passAfficher;
    @FXML
    private PasswordField passMasquer;
    @FXML
    private CheckBox checkBoxs;
    @FXML
    private AnchorPane pere;
     pages parents = new pages();
    @FXML
    private Text afficher;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void CreerCompte(MouseEvent event) throws IOException {
        String fxml = "/vue/registre.fxml";
        pages parent = new pages();
        parent.page(fxml, pere);
        
    }

    @FXML
    private void getConnexions(ActionEvent event) {
        getFxml();
    }
    
    
    
    public void getFxml(){
        String use =  Maile.getText();
        String pass = passMasquer.getText();
        String pass2 = passMasquer.getText();
        String message = "verifier vos informations";
        String title = "eche";
        String fxml = "/vue/homepage.fxml";
        if(use.isEmpty() && pass.isEmpty())
        {
            parents.verifie(title, message);
            
        }
        else if(use.isEmpty()){
            parents.verifie("mail", "veillez complter la case d'adress mail");
        }
         else if(pass.isEmpty()){
            parents.verifie("passWord", "veillez complter la case mot de passe");
        }
        else{
            try {
                getselect(fxml);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    Utilisateur ut;


    @FXML
    private void getCrocher(ActionEvent event) {
         if (checkBoxs.isSelected()) {
            passAfficher.setVisible(true);
            passAfficher.setText(passMasquer.getText());
            passMasquer.setVisible(false);
            afficher.setText("masquer le mot de passe");
        }
        else{
             passAfficher.setVisible(false);
            passMasquer.setText(passAfficher.getText());
            passMasquer.setVisible(true);
            afficher.setText("affichier le mot de passe");
        }
    }

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }
    
    
    public void getselect(String fxml) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        String requete = "SELECT nom FROM users  where email =? AND mot_de_passe =? ";
        String use =  Maile.getText();
        String passe = passMasquer.getText();
         
            
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Etablir la connexion avec la base de données
            String url = "jdbc:mysql://localhost:3306/Clients";
            String utilisateur = "root";
            String motDePasse = "root";
            
            Client server;
            
            conn = DriverManager.getConnection(url, utilisateur, motDePasse);
            stmt = conn.prepareStatement(requete);
            stmt.setString(1, use);
            stmt.setString(2, passe);
            ResultSet resultat = stmt.executeQuery();
                
            if(resultat.next()){
                try {
                parents.pagess(fxml, pere);
                
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }             
        }else{
             parents.verifie("echec", "mot de passe ou adresse est incorrecte");
        }
            
            
  } catch (ClassNotFoundException e) {
            System.out.println("Pilote JDBC non trouvé : " + e.getMessage());
        } finally {
            // Fermer les ressources
            if (conn != null) {
                conn.close();
                stmt.close(); 
            }
        }
    }
    
    
      @FXML
    private void getDragged(MouseEvent event) {
         MovePane.mouv(event);
    }

    @FXML
    private void getMove(MouseEvent event) {
         MovePane.mouvXY(event);
    }

}
