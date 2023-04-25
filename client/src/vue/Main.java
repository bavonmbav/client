/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        try {
            InetAddress address = InetAddress.getByName(hostName);
            System.out.println("Address: " + address);
        } catch (UnknownHostException e) {
            System.out.println("Unable to resolve host: " + hostName);
        }
    }
    
    
    
}


class Clients {

    public static void main(String[] args) {

        try {
            // Connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/messagerie", "root", "");
            Statement stmt = con.createStatement();
            
            // Récupération des messages de la table messages
            ResultSet rs = stmt.executeQuery("SELECT * FROM messages");
            
            // Affichage des messages
            while (rs.next()) {
                String sender = rs.getString("sender");
                String recipient = rs.getString("recipient");
                String message = rs.getString("message");
                System.out.println(sender + " a envoyé le message \"" + message + "\" à " + recipient);
            }
            
            // Fermeture de la connexion
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

 class Client {
    private Connection conn;
    
    public Client() {
        try {
            // Connexion à la base de données
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void afficherHistorique() {
        try {
            // Requête pour récupérer l'historique des messages
            String query = "SELECT sender, message, recipient FROM messages";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            // Affichage de l'historique des messages
            while (rs.next()) {
                String sender = rs.getString("sender");
                String message = rs.getString("message");
                String recipient = rs.getString("recipient");
                
                System.out.println("From: " + sender);
                System.out.println("To: " + recipient);
                System.out.println("Message: " + message);
                System.out.println();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
