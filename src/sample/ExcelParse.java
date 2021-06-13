package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ExcelParse {

    public static ObservableList<Data> strength = FXCollections.observableArrayList();
    public static ObservableList<Data> weakness = FXCollections.observableArrayList();
    public static ObservableList<Data> opportunities = FXCollections.observableArrayList();
    public static ObservableList<Data> threats = FXCollections.observableArrayList();
    public static double SPower = 0;
    public static double WPower = 0;
    public static double OPower = 0;
    public static double TPower = 0;

    public static void parse(String fileName) {
        //инициализируем потоки
        InputStream inputStream = null;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //разбираем первый лист входного файла на объектную модель
        Sheet sheet = null;
        if (workBook != null) {
            sheet = workBook.getSheetAt(0);
        }
        Iterator<Row> it = null;
        if (sheet != null) {
            it = sheet.iterator();
        }
        String flag = "strength";
        int number = 0;
        //проходим по всему листу
        if (it != null) {
            while (it.hasNext()) {
                Row row = it.next();
                Iterator<Cell> cells = row.iterator();
                Cell cell = cells.next();
                if (cell.getStringCellValue().equals("strength")) {
                    number = 0;
                    flag = "strength";
                } else if (cell.getStringCellValue().equals("weakness")) {
                    flag = "weakness";
                    number = 0;
                } else if (cell.getStringCellValue().equals("opportunities")) {
                    flag = "opports";
                    number = 0;
                } else if (cell.getStringCellValue().equals("threats")) {
                    flag = "threats";
                    number = 0;
                } else if (!cell.getStringCellValue().equals("") && !cell.getStringCellValue().equals("name")) {
                    String name = cell.getStringCellValue();
                    String action = null;
                    double importance = 0;
                    double probab = 0;
                    for (int i = 1; i < 4; i++) {
                        cell = cells.next();
                        if (i == 1) {
                            action = cell.getStringCellValue();
                        } else if (i == 2) {
                            importance = cell.getNumericCellValue();
                        } else {
                            probab = cell.getNumericCellValue();
                        }
                    }
                    if (flag.equals("strength")) {
                        number++;
                        strength.add(new Data(number, name, action, String.valueOf(importance), String.valueOf(probab),
                                probab * importance));
                    } else if (flag.equals("weakness")) {
                        number++;
                        weakness.add(new Data(number, name, action, String.valueOf(importance), String.valueOf(probab),
                                probab * importance));
                    } else if (flag.equals("opports")) {
                        number++;
                        opportunities.add(new Data(number, name, action, String.valueOf(importance), String.valueOf(probab),
                                probab * importance));
                    } else {
                        number++;
                        threats.add(new Data(number, name, action, String.valueOf(importance), String.valueOf(probab),
                                probab * importance));
                    }
                }
            }

            for (int i = 0; i < strength.size(); i++) {
                SPower += strength.get(i).getPower();
            }
            for (int i = 0; i < opportunities.size(); i++) {
                OPower += opportunities.get(i).getPower();
            }
            for (int i = 0; i < weakness.size(); i++) {
                WPower += weakness.get(i).getPower();
            }
            for (int i = 0; i < threats.size(); i++) {
                TPower += threats.get(i).getPower();
            }
        }
    }

}
