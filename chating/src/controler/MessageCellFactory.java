
package controler;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MessageCellFactory implements Callback<ListView<Messages>, ListCell<Messages>> {
    
    @Override
    public ListCell<Messages> call(ListView<Messages> listView) {
        return new ListCell<Messages>() {
            @Override
            protected void updateItem(Messages message, boolean empty) {
                super.updateItem(message, empty);
                
                if (message == null || empty) {
                    setText(null);
                } else {
                    // Personnalisation de l'affichage de la cellule
                    setText(message.getExpediteur() + " : " + message.getText());
                }
            }
        };
    }
}

