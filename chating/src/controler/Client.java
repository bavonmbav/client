
package controler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javafx.scene.layout.VBox;
import vue.HomepageController;


public class Client {
   
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    
    public Client(Socket socket){
        try {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
             close(socket, in, out);
             e.printStackTrace();
        }
       
    } 
    
    public void close(Socket s,BufferedReader b,BufferedWriter w)
    {
        try {
            if(s != null)
                s.close();
            if(b != null)
                b.close();
            if(w != null)
                w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessageServer(String sendMessage){
        try {
                out.write(sendMessage);
                out.newLine();
                out.flush();
        } catch (Exception e) {
            close(socket, in, out);
            e.printStackTrace();
        }
       
    }
    
    public void RecevoirMessageClient(VBox box){
       new Thread(new Runnable() {
           @Override
           public void run() {
               while (!socket.isClosed()) {
                   try {
                            String messages = in.readLine();
                            HomepageController.addLabbel(messages, box);
                   } catch (Exception e) {
                   }   
               }
           }
       }).start();
    }
}
