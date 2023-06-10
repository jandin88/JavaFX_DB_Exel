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

import javax.swing.JOptionPane;
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
    public void onBtRegisterAction(){
        register();
    }

    @FXML
    public void onBtReturnAction(){
        MainViewController mainView = new MainViewController();
        mainView.loadViewRegister("/GUI/FXML/MainView.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    public  void register(){

        try {
            Connection connection = DB.getConnection();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String telefone = txtTelefone.getText();
            String idade = txtIdade.getText();

            if (name.isEmpty() || email.isEmpty() || telefone.isEmpty() || idade.isEmpty()) {
                alerts.showAlert("ERROR", "Preencha todos os campos", Alert.AlertType.ERROR);
                return;
            }

            Client client = new Client();
            client.setNome(name);
            client.setEmail(email);
            client.setTelefone(telefone);
            client.setIdade(idade);

            ClienteDaoJDBC clienteDaoJDBC = new ClienteDaoJDBC(connection);
            clienteDaoJDBC.insert(client);
            JOptionPane.showMessageDialog(null, "Cadastrado");

        }catch (Exception e){
            alerts.showAlert("ERROR", e.getMessage(), Alert.AlertType.ERROR);
        }
    }


}
