package GUI.FXML;

import GUI.Util.alerts;
import Model.Dao.ClientDao;
import Model.Dao.DaoFactory;
import Model.Entites.Client;
import Model.Entites.Export;
import Model.Entites.Import;
import Model.Service.ClientService;
import Project.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.File;
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
        loadViewRegister("/GUI/FXML/registerClient.fxml");

    }

    @FXML
    public void onBtExportAction() throws IOException {
        ClientDao dao= DaoFactory.createClientDao();
        List<Client> clients = dao.findAll();
        String userHome = System.getProperty("user.home");
        String fileNome = userHome + File.separator + "Documents";

        Export export = new Export();
        export.generateExcel(clients, fileNome);

    }
    @FXML
    public  void onBtImportAction(){
        Import importEx =new Import();
        importEx.importExel();
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
    synchronized void loadViewRegister(String AbsoluteName){
        try {
            FXMLLoader loader=new FXMLLoader(MainViewController.class.getResource(AbsoluteName));
            ScrollPane newVbox = loader.load();

            Scene mainScene= Main.getMainScene();
            VBox mainVbox=(VBox) ((ScrollPane)mainScene.getRoot()).getContent();

            mainVbox.getChildren().clear();
            newVbox.setFitToHeight(true);
            newVbox.setFitToWidth(true);

            if (AbsoluteName=="/GUI/FXML/MainView.fxml") {

                MainViewController controller=loader.getController();
                controller.setClienteService(new ClientService());
                controller.updateTableView();
                controller.clientTableView.setPrefHeight(Double.parseDouble("750.0"));

            }

            mainVbox.getChildren().addAll(newVbox.getContent());


        }catch (Exception e){
            alerts.showAlert("Error",e.getMessage(), Alert.AlertType.ERROR);
        }
    }


}
