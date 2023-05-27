package GUI.FXML;

import Model.Dao.ClientDao;
import Model.Dao.DaoFactory;
import Model.Entites.Client;
import Model.Export.Export;
import Model.Service.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainViewController implements Initializable {

    private ClientService service;
    private ObservableList<Client>obsList;
    @FXML
    private Button btExport;
    @FXML
    private Button btNew;
    @FXML
    public  TableView<Client> clientTableView;
    @FXML
    private TableColumn<Client, Integer>tableColumnID;
    @FXML
    private TableColumn<Client, String>tableColumnName;
    @FXML
    private TableColumn<Client, Integer>tableColumnIdade;
    @FXML
    private TableColumn<Client, String>tableColumnEmail;
    @FXML
    private TableColumn<Client, String>tableColumnTelefone;

    public void setClienteService(ClientService service){
        this.service=service;
    }
    @FXML
    public void onBtNewAction(){
        System.out.println("onBtNewAction");
    }

    @FXML
    public void onBtExportAction() throws IOException {
        ClientDao dao= DaoFactory.createClientDao();
        List<Client> clients = dao.findAll();

        Export export = new Export();
        export.generatorExel(clients);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){initializeNode();}



    private void initializeNode(){
        tableColumnID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        tableColumnIdade.setCellValueFactory(new PropertyValueFactory<>("Idade"));

        //ele ta definindo que o table view fique do tamnho da tela
        VBox.setVgrow(clientTableView, javafx.scene.layout.Priority.ALWAYS);
    }


    public void updateTableView(){
        if (service==null){
            throw new IllegalStateException("Service was null");
        }
        List<Client> list=service.findAll();
        obsList= FXCollections.observableList(list);
        clientTableView.setItems(obsList);
    }


/*
    private synchronized void loaView(String absoluteName){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVbox = loader.load();

            Scene mainScene= Main.getMainScene();
            VBox mainVbox=(VBox) ((ScrollPane)mainScene.getRoot()).getContent();

            Node mainMenu= mainVbox.getChildren().get(0);
            mainVbox.getChildren().clear();
            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newVbox.getChildren());


        }catch (IOException e){
            alerts.showAlert("IO Excpetion",e.getMessage(), Alert.AlertType.ERROR);
        }
    }
*/
}
