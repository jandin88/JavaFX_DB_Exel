package Model.Entites;

import javax.swing.*;
import java.io.File;

public class FileUser {
    static public String fileUsers(){

        // Cria um seletor de pasta
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Exibe a janela de diálogo para o usuário selecionar uma pasta
        int result = fileChooser.showOpenDialog(null);
        String  selectedFolderPath = "";
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtém a pasta selecionada pelo usuário
            selectedFolderPath = fileChooser.getSelectedFile().getPath();

        }
        return selectedFolderPath;
    }
    static public File selectExel(){
        // Cria um seletor de pasta
        JFileChooser fileChooser = new JFileChooser();
        // Exibe a janela de diálogo para o usuário selecionar uma pasta
        int result = fileChooser.showOpenDialog(null);
        File file=null;
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtém a pasta selecionada pelo usuário
            file= fileChooser.getSelectedFile();

        }
        return file;
    }
}
