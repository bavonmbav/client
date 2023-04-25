
package vue;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MovePane;


public class RegistreController implements Initializable {

    @FXML
    private AnchorPane pere;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adressMail;
    @FXML
    private TextField passAfficher;
    @FXML
    private PasswordField passMasquer;
    @FXML
    private CheckBox checkBoxs;
    @FXML
    private Text afficher;
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

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
    private void getEnregistrer(ActionEvent event) {
        getSauvegarde();
    }  

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }
     @FXML
    private void retour(MouseEvent event) {
        pages();
    }
    public void getSauvegarde(){
         String noms = nom.getText();
        String prenoms = prenom.getText();
        String motDePasse = passMasquer.getText();
        String email = adressMail.getText();

        if (noms.isEmpty() || prenoms.isEmpty() || motDePasse.isEmpty() || email.isEmpty()) {
            Alert alerte = new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs !");
            alerte.showAndWait();
            return;
            
        }
           if (motDePasse !=null) {
            try {
                // Sauvegarder le nouvel utilisateur dans la base de données
               
                sauvegarder();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Nouvel utilisateur ajouté avec succès !");
                alert.setContentText("Le nouvel utilisateur a été ajouté à la base de données.");
                alert.showAndWait();
                pages();
                // Effacer les champs de saisie
                nom.clear();
                prenom.clear();
                adressMail.clear();
                passMasquer.clear();
            } catch (SQLException e) {
                // Afficher un message d'erreur en cas d'échec de la sauvegarde dans la base de données
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible d'ajouter le nouvel utilisateur !");
                alert.setContentText("Une erreur est survenue lors de la sauvegarde du nouvel utilisateur dans la base de données : " + e.getMessage());
                alert.showAndWait();
            }
        } else {
            // Afficher un message d'erreur si le mot de passe ne respecte pas les critères de validation
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Le mot de passe est invalide !");
            alert.setContentText("Le mot de passe doit contenir uniquement des lettres et des chiffres et avoir une longueur minimale de 6 caractères.");
            alert.showAndWait();
        }
    }

    public void pages()
    {
        Stage s = new Stage();
            try {
                    Parent fxml = FXMLLoader.load(getClass().getResource("/vue/login.fxml"));
                      pere.getScene().getWindow().hide();
                        s.setScene(new Scene(fxml));
                        s.initStyle(StageStyle.TRANSPARENT);
                        s.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
    }
    public boolean validerMotDePasse(String motDePasse) {
    String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";
    return motDePasse.matches(regex);
}

    
   
    
    public void sauvegarder() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String noms = nom.getText();
        String prenoms = prenom.getText();
        String motDePasse = passMasquer.getText();
        String email = adressMail.getText();
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            String url = "jdbc:mysql://localhost:3306/Clients";
            String utilisateur = "root";
            String motDePasses = "root";
            conn = DriverManager.getConnection(url, utilisateur, motDePasses);

            // Préparer la requête SQL
            String sql = "INSERT INTO users (nom, prenom,email, mot_de_passe) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,noms );
            stmt.setString(2, prenoms);
            stmt.setString(3, email);
            stmt.setString(4,motDePasse);

            // Exécuter la requête SQL
            stmt.executeUpdate();

            // Récupérer l'identifiant auto-incrémenté généré par la base de données
           
        } catch (ClassNotFoundException e) {
            System.out.println("Pilote JDBC non trouvé : " + e.getMessage());
        } finally {
            // Fermer les ressources
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
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

