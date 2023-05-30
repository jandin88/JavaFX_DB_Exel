package Model.Entites;

import GUI.Util.alerts;
import Model.Dao.Impl.ClienteDaoJDBC;
import db.DB;
import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.sql.Connection;

import static Model.Entites.FileUser.selectExel;

public class Import {
    public void importExel() {
        try {
            FileInputStream file = new FileInputStream(selectExel());
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            Connection connection = DB.getConnection();
            ClienteDaoJDBC clienteDaoJDBC = new ClienteDaoJDBC(connection);
            Client client=new Client();

            for(int i=1;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);

                client.setNome(row.getCell(0).getStringCellValue());
                client.setEmail(row.getCell(1).getStringCellValue());
                client.setTelefone(""+(row.getCell(2).getNumericCellValue()));
                client.setIdade(""+(row.getCell(3).getNumericCellValue()));

                clienteDaoJDBC.insert(client);
            }

            file.close();
        } catch (Exception e) {
            alerts.showAlert("ERROR",e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
