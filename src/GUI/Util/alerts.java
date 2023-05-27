package GUI.Util;

import javafx.scene.control.Alert;

public class alerts {
    public static void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert=new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
}
