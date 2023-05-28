package GUI.FXML;

import Model.Dao.Impl.ClienteDaoJDBC;
import Model.Entites.Client;
import db.DB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import static GUI.FXML.MainViewController.loadViewRegister;


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
        Connection connection=DB.getConnection();
        String name= txtName.getText();
        String email=txtEmail.getText();
        String telefone=txtTelefone.getText();
        Integer idade=Integer.parseInt(txtIdade.getText());

        Client client=new Client();
        client.setNome(name);
        client.setEmail(email);
        client.setTelefone(telefone);
        client.setIdade(idade);

        ClienteDaoJDBC clienteDaoJDBC=new ClienteDaoJDBC(connection);
        clienteDaoJDBC.insert(client);
        DB.closeConnection();
        JOptionPane.showMessageDialog(null, "Cadastrado");


    }



    @FXML
    public void onBtReturnAction(){loadViewRegister("/GUI/FXML/MainView.fxml");}


    @Override
    public void initialize(URL url, ResourceBundle rb) {}


}
