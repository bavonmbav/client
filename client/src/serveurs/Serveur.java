
package serveurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class Serveur  extends Thread{
    int compte;
    
    private ListView<String> connectedUsersListView;
    private ObservableList<String> connecteUsers = FXCollections.observableArrayList();
    List<Conversation> Connecter = new ArrayList<>();
    
    

    public Serveur(ListView<String> connectedUsersListView) {
        this.connectedUsersListView = connectedUsersListView;
    }

    private Serveur() {
        
    }
    
    @Override
    public void run() {
        try {
            ServerSocket serverSockets = new ServerSocket(2020);
            while(true){
                Socket s = serverSockets.accept();
                ++compte;   

                Conversation con =new Conversation(s, compte,connecteUsers);
                Connecter.add(con);
                con.start();
             
            }  
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }
    
    // pour diffuser le message 
    public void broadcast(String message, int[]num){
        Connecter.forEach((con) -> {
            PrintWriter pw;
            boolean ClientTrouver = false;
            try {
                for(int i=0;i<num.length; i++){
                    if(con.numeroClient == num[i]){
                        ClientTrouver = true;
                        break;
                    }
                }if(ClientTrouver == true){
                        pw = new PrintWriter(con.soc.getOutputStream(), true);
                        pw.println(Arrays.toString(num)+"  " + message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
            
    
  // la classe internet va permettre d'attendre plusieurs connection 
    public class Conversation extends Thread{
        
            Socket soc;
            public int numeroClient;
            ObservableList<String> connectedUsersList;
            
        public Conversation(Socket soc, int numeroClient, ObservableList<String> connectedUsersList) {
            this.soc = soc;
            this.numeroClient = numeroClient;
            this.connectedUsersList = connectedUsersList;
        }
    
    
       
        @Override
        public void run() {
           
         
            
             //reccuperation de l'addresse ip de la machine connecter
                String Ip = soc.getRemoteSocketAddress().toString();
                
              Connection conn = null;
               try {
               InputStream is = soc.getInputStream();
               InputStreamReader rd = new InputStreamReader(is);
               BufferedReader inp = new BufferedReader(rd);
               OutputStream outs = soc.getOutputStream();
               PrintWriter pw = new PrintWriter(outs, true);
               System.out.println("client num =>" + numeroClient + "  Ip => "+Ip +" Connecter");
               pw.println(compte + ": enligne");
               
             //mise a jour de la liste 
                Platform.runLater(() -> {
                    connectedUsersList.add(Ip +"client numero = " + compte);
                    connectedUsersListView.getItems().add(Ip +"client numero = " + compte);
                });
                
                
                
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // Etablir la connexion avec la base de données
                    String url = "jdbc:mysql://localhost:3306/Clients";
                    String utilisateur = "root";
                    String motDePasse = "root";
                    conn = DriverManager.getConnection(url, utilisateur, motDePasse);
                
                   while (true) {                       
                       String req;
                       while ((req=inp.readLine()) != null) {
                           String[] tab1 = req.split(":");
                           String message = tab1[0];
                           String [] tab2 = tab1[1].split(",");
                           int[] numeroClients = new int[tab1.length];
                           for(int i=0; i<tab2.length; i++){
                               numeroClients[i] = Integer.parseInt(tab2[i]);
                               
                           }
                           String recipients = Arrays.toString(numeroClients);
                           // Insérer le message dans la base de données
                String sql = "INSERT INTO messages (expediteur, destinateur, content) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, Ip);
                stmt.setString(2, recipients);
                stmt.setString(3, message);
                stmt.executeUpdate();
                // Diffuser le message aux destinataires
                            broadcast(message,numeroClients);
                       }
                   }
                   
             
            } catch (Exception e) {
                 try {
                     soc.close();
                     
                     
                     e.printStackTrace();
                 } catch (IOException ex) {
                        ex.printStackTrace();                 
                 }
            }finally {
                Platform.runLater(() -> {
                    connectedUsersList.remove(Ip);
                    connectedUsersListView.getItems().remove(Ip);
                });
            }
        }
      
       
        
        //connexion
        public void getconnexion(){
              Connection conn = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // Etablir la connexion avec la base de données
                    String url = "jdbc:mysql://localhost:3306/Clients";
                    String utilisateur = "root";
                    String motDePasse = "root";
                    conn = DriverManager.getConnection(url, utilisateur, motDePasse);
                } catch (ClassNotFoundException ex) {
                     ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
       

 }
    public void Star(){
        new Serveur(connectedUsersListView).start();
    }
}