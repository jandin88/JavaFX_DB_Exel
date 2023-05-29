package GUI.FXML;

import GUI.Util.alerts;
import Model.Dao.Impl.ClienteDaoJDBC;
import Model.Entites.Client;
import db.DB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;


public class RegisteClientController implements Initializable {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtIdade;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnReturn;


    @FXML
    public void onBtRegisterAction(){
        try {
            Connection connection = DB.getConnection();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String telefone = txtTelefone.getText();
            Integer idade = Integer.parseInt(txtIdade.getText());

            Client client = new Client();
            client.setNome(name);
            client.setEmail(email);
            client.setTelefone(telefone);
            client.setIdade(idade);

            ClienteDaoJDBC clienteDaoJDBC = new ClienteDaoJDBC(connection);
            clienteDaoJDBC.insert(client);
            JOptionPane.showMessageDialog(null, "Cadastrado");
        }catch (Exception e){
            alerts.showAlert("ERROR","Informações incompletas", Alert.AlertType.ERROR);}

    }



    @FXML
    public void onBtReturnAction(){
        MainViewController mainView = new MainViewController();
        mainView.loadViewRegister("/GUI/FXML/MainView.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {}


}
