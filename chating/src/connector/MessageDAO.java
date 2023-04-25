package connector;


import controler.Messages;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MessageDAO {
    
    private Connection connection;
    
    public MessageDAO() throws SQLException {
        // Connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/mabasededonnees";
        String username = "monutilisateur";
        String password = "monmotdepasse";
        connection = DriverManager.getConnection(url, username, password);
    }
    
    public ObservableList<Messages> getMessages() throws SQLException {
        ObservableList<Messages> messages = FXCollections.observableArrayList();
        
        // Exécution de la requête SELECT
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM messages;");
        
        // Parcours des résultats et création des objets Message
        while (resultSet.next()) {
            String expediteur = resultSet.getString("expediteur");
            String destinateur = resultSet.getString("destinateur");
            String content = resultSet.getString("content");
            Timestamp timestamp = resultSet.getTimestamp("timestamp");
            
            Messages message = new Messages(content, content, content, timestamp);
            messages.add(message);
        }
        
        return messages;
    }
    
    public void close() throws SQLException {
        // Fermeture de la connexion à la base de données
        connection.close();
    }
}
