package Model.Entites;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static Model.Entites.FileUser.fileUsers;

public class Export {


    public void generateExcel(List<Client> clients, String fileNome) throws IOException {

        String file =fileUsers()+File.separator+new File("Clientes.xlsx");

        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            SXSSFWorkbook workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet("Clientes");

        //criando cabeçalho
        Row cabRow = sheet.createRow(0);
            cabRow.createCell(0).setCellValue("ID");
            cabRow.createCell(1).setCellValue("Nome");
            cabRow.createCell(2).setCellValue("Email");
            cabRow.createCell(3).setCellValue("Telefone");
            cabRow.createCell(4).setCellValue("Idade");

            int countRow = 1;
            for (Client client : clients) {
                Row rowValor = sheet.createRow(countRow++);
                rowValor.createCell(0).setCellValue(client.getId());
                rowValor.createCell(1).setCellValue(client.getNome());
                rowValor.createCell(2).setCellValue(client.getEmail());
                rowValor.createCell(3).setCellValue(client.getTelefone());
                rowValor.createCell(4).setCellValue(client.getIdade());
            }
            // Ajustar largura das colunas
            for (int i = 0; i < 5; i++) {
                ((SXSSFSheet)sheet).trackAllColumnsForAutoSizing();
                sheet.autoSizeColumn(i);}
            // Fechamento do arquivo
            workbook.write(outputStream);
            workbook.close();
            JOptionPane.showMessageDialog(null, "Arquivo Criado Com sucesso\n"+file);


        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
