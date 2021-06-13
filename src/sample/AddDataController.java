package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddDataController {

    @FXML
    private TextField nameStr;

    @FXML
    private TextField actionStr;

    @FXML
    private TextField importanceStr;

    @FXML
    private TextField probabStr;

    @FXML
    private Button save;

    public void setSave(javafx.scene.input.MouseEvent mouseEvent) {
        String name = nameStr.getText();
        String action = actionStr.getText();
        String importance = importanceStr.getText();
        String probab = probabStr.getText();

        if (name.isEmpty() || action.isEmpty() || importance.isEmpty() || probab.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Заполните ВСЕ ПОЛЯ!");
            alert.showAndWait();
        } else {
            int num;
            if (Controller.tableType == 1) {
                num = ExcelParse.strength.size() + 1;
                ExcelParse.strength.add(new Data(num, name, action, importance, probab,
                        Double.parseDouble(importance) * Double.parseDouble(probab)));
            } else if(Controller.tableType == 2) {
                num = ExcelParse.weakness.size() + 1;
                ExcelParse.weakness.add(new Data(num, name, action, importance, probab,
                        Double.parseDouble(importance) * Double.parseDouble(probab)));
            } else if (Controller.tableType == 3) {
                num = ExcelParse.opportunities.size() + 1;
                ExcelParse.opportunities.add(new Data(num, name, action, importance, probab,
                        Double.parseDouble(importance) * Double.parseDouble(probab)));
            } else if (Controller.tableType == 4) {
                num = ExcelParse.threats.size() + 1;
                ExcelParse.threats.add(new Data(num, name, action, importance, probab,
                        Double.parseDouble(importance) * Double.parseDouble(probab)));
            }
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
        }
    }
}