
package controler;

import java.sql.Date;
import java.sql.Timestamp;


public class Messages {
    private String expediteur;
    private String destnaire;
    private String text;
    private Timestamp date;

    public Messages(String content, String sender, String recipient, Timestamp date) {
        this.expediteur = content;
        this.destnaire = sender;
        this.text = recipient;
        this.date = date;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getDestnaire() {
        return destnaire;
    }

    public void setDestnaire(String destnaire) {
        this.destnaire = destnaire;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
