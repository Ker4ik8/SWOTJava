package sample;

//сделать сохранение инфы в блокнот.

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;


import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {

    public static int tableType = 0;
    private static int arraySize = 0;
    private static XYChart.Series strengthBar = new XYChart.Series();
    private static XYChart.Series weaknessBar = new XYChart.Series();
    private static XYChart.Series opportunitiesBar = new XYChart.Series();
    private static XYChart.Series threatsBar = new XYChart.Series();
    private static XYChart.Series SWOTBar = new XYChart.Series();

    @FXML
    private TableView<Data> strengthTable;

    @FXML
    private TableColumn<Data, Integer> colNumS;

    @FXML
    private TableColumn<Data, String> colNameS;

    @FXML
    private TableColumn<Data, String> colActionsS;

    @FXML
    private TableColumn<Data, String> colImportanceS;

    @FXML
    private TableColumn<Data, String> colProbabilityS;

    @FXML
    private TableColumn<Data, Double> colPowerS;

    @FXML
    private TextField deleteStrS;

    @FXML
    private Button deleteButtonS;

    @FXML
    private BarChart<?, ?> ChartS;

    @FXML
    private CategoryAxis XS;

    @FXML
    private NumberAxis YS;

    @FXML
    private TableView<Data> weaknessTable;

    @FXML
    private TableColumn<Data, Integer> colNumW;

    @FXML
    private TableColumn<Data, String> colNameW;

    @FXML
    private TableColumn<Data, String> colActionsW;

    @FXML
    private TableColumn<Data, String> colImportanceW;

    @FXML
    private TableColumn<Data, String> colProbabilityW;

    @FXML
    private TableColumn<Data, Double> colPowerW;

    @FXML
    private Button button_add1;

    @FXML
    private Button button_refreshW;

    @FXML
    private TextField deleteStrW;

    @FXML
    private Button deleteButtonW;

    @FXML
    private BarChart<?, ?> ChartW;

    @FXML
    private CategoryAxis XW;

    @FXML
    private NumberAxis YW;

    @FXML
    private TableView<Data> opportunitiesTable;

    @FXML
    private TableColumn<Data, Integer> colNumO;

    @FXML
    private TableColumn<Data, String> colNameO;

    @FXML
    private TableColumn<Data, String> colActionsO;

    @FXML
    private TableColumn<Data, String> colImportanceO;

    @FXML
    private TableColumn<Data, String> colProbabilityO;

    @FXML
    private TableColumn<Data, Double> colPowerO;

    @FXML
    private Button button_addO;

    @FXML
    private Button button_refreshO;

    @FXML
    private TextField deleteStrO;

    @FXML
    private Button deleteButtonO;

    @FXML
    private BarChart<?, ?> ChartO;

    @FXML
    private CategoryAxis XS11;

    @FXML
    private NumberAxis YS11;

    @FXML
    private TableView<Data> threatsTable;

    @FXML
    private TableColumn<Data, Integer> colNumT;

    @FXML
    private TableColumn<Data, String> colNameT;

    @FXML
    private TableColumn<Data, String> colActionsT;

    @FXML
    private TableColumn<Data, String> colImportanceT;

    @FXML
    private TableColumn<Data, String> colProbabilityT;

    @FXML
    private TableColumn<Data, Double> colPowerT;

    @FXML
    private Button button_addT;

    @FXML
    private Button button_refreshT;

    @FXML
    private TextField deleteStrT;

    @FXML
    private Button deleteButtonT;

    @FXML
    private BarChart<?, ?> ChartT;

    @FXML
    private CategoryAxis XS111;

    @FXML
    private NumberAxis YS111;

    @FXML
    private TextField fileName;

    @FXML
    private BarChart<?, ?> ChartSWOT;

    @FXML
    private CategoryAxis XS1111;

    @FXML
    private NumberAxis YS1111;

    @FXML
    private Text success;

    @FXML
    void downloadFile(MouseEvent event) throws FileNotFoundException {
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            threatsTable.getItems().clear();
            strengthTable.getItems().clear();
            weaknessTable.getItems().clear();
            opportunitiesTable.getItems().clear();
            File file = fileopen.getSelectedFile();
            String path = file.getPath();
            String tag = "";
            for (int i = path.length() - 3; i < path.length(); i++) {
                tag += path.charAt(i);
            }
            if (tag.equals("lsx")) {
                ExcelParse.parse(path);
            } else {
                TextParse.parseText(path);
            }
            threatsTable.setItems(ExcelParse.threats);
            opportunitiesTable.setItems(ExcelParse.opportunities);
            weaknessTable.setItems(ExcelParse.weakness);
            strengthTable.setItems(ExcelParse.strength);
            makeSWOTBar();
        }
    }

    void makeSWOTBar() {
        SWOTBar.getData().clear();
        ChartSWOT.getData().clear();
        ChartSWOT.setAnimated(false);
        SWOTBar.getData().add(new XYChart.Data<>("Strength", ExcelParse.SPower));
        SWOTBar.getData().add(new XYChart.Data<>("Weakness", -1 * ExcelParse.WPower));
        SWOTBar.getData().add(new XYChart.Data<>("Opportunities", ExcelParse.OPower));
        SWOTBar.getData().add(new XYChart.Data<>("Threats", -1 * ExcelParse.TPower));
        SWOTBar.getData().add(new XYChart.Data<>("Result", ExcelParse.SPower - ExcelParse.WPower + ExcelParse.OPower - ExcelParse.TPower));
        ChartSWOT.getData().addAll(SWOTBar);
        double goodResult = ((ExcelParse.OPower + ExcelParse.SPower) / (ExcelParse.SPower + ExcelParse.TPower + ExcelParse.OPower + ExcelParse.WPower)) * 100;
        success.setText(String.valueOf(goodResult) + "%");
    }

    @FXML
    void saveFile(MouseEvent event) throws IOException {
        JFileChooser fileopen = new JFileChooser();
        fileopen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = fileopen.showDialog(null, "Выбрать папку");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            String path = file.getPath();
            path = path + "\\" + fileName.getText() + ".txt";
            file = new File(path);
            FileWriter writer = new FileWriter(file);
            String result = "";
            result += "strength \n";
            for (int i = 0; i < ExcelParse.strength.size(); i++) {
                result += (ExcelParse.strength.get(i).getName()) + " \n";
                result += (ExcelParse.strength.get(i).getAction()) + " \n";
                result += (ExcelParse.strength.get(i).getImportance()) + " \n";
                result += (ExcelParse.strength.get(i).getProbability()) + " \n";
            }
            result += "weakness \n";
            for (int i = 0; i < ExcelParse.weakness.size(); i++) {
                result += (ExcelParse.weakness.get(i).getName()) + " \n";
                result += (ExcelParse.weakness.get(i).getAction()) + " \n";
                result += (ExcelParse.weakness.get(i).getImportance()) + " \n";
                result += (ExcelParse.weakness.get(i).getProbability()) + " \n";
            }
            result += "opportunities \n";
            for (int i = 0; i < ExcelParse.opportunities.size(); i++) {
                result += (ExcelParse.opportunities.get(i).getName()) + " \n";
                result += (ExcelParse.opportunities.get(i).getAction()) + " \n";
                result += (ExcelParse.opportunities.get(i).getImportance()) + " \n";
                result += (ExcelParse.opportunities.get(i).getProbability()) + " \n";
            }
            result += "threats \n";
            for (int i = 0; i < ExcelParse.threats.size(); i++) {
                result += (ExcelParse.threats.get(i).getName()) + " \n";
                result += (ExcelParse.threats.get(i).getAction()) + " \n";
                result += (ExcelParse.threats.get(i).getImportance()) + " \n";
                result += (ExcelParse.threats.get(i).getProbability()) + " \n";
            }
            writer.write(result);
            writer.close();
        }
    }

    @FXML
    void addORow(MouseEvent event) {
        tableType = 3;
        arraySize = ExcelParse.opportunities.size();
        getAddView();
        if (ExcelParse.opportunities.size() > arraySize) {
            opportunitiesTable.getItems().add(ExcelParse.opportunities.get(ExcelParse.opportunities.size() - 1));
            opportunitiesTable.refresh();
            ExcelParse.OPower = 0;
            for (int i = 0; i < ExcelParse.opportunities.size(); i++) {
                ExcelParse.OPower += ExcelParse.opportunities.get(i).getPower();
            }
            makeSWOTBar();
        }
    }

    @FXML
    void addTRow(MouseEvent event) {
        tableType = 4;
        arraySize = ExcelParse.threats.size();
        getAddView();
        if (ExcelParse.threats.size() > arraySize) {
            threatsTable.getItems().add(ExcelParse.threats.get(ExcelParse.threats.size() - 1));
            threatsTable.refresh();
            ExcelParse.TPower = 0;
            for (int i = 0; i < ExcelParse.threats.size(); i++) {
                ExcelParse.TPower += ExcelParse.threats.get(i).getPower();
            }
            makeSWOTBar();
        }
    }

    @FXML
    void addWRow(MouseEvent event) {
        tableType = 2;
        arraySize = ExcelParse.weakness.size();
        getAddView();
        if (ExcelParse.weakness.size() > arraySize) {
            weaknessTable.getItems().add(ExcelParse.weakness.get(ExcelParse.weakness.size() - 1));
            weaknessTable.refresh();
            ExcelParse.WPower = 0;
            for (int i = 0; i < ExcelParse.weakness.size(); i++) {
                ExcelParse.WPower += ExcelParse.weakness.get(i).getPower();
            }
            makeSWOTBar();
        }
    }

    @FXML
    void addSRow(MouseEvent event) {
        tableType = 1;
        arraySize = ExcelParse.strength.size();
        getAddView();
        if (ExcelParse.strength.size() > arraySize) {
            strengthTable.getItems().add(ExcelParse.strength.get(ExcelParse.strength.size() - 1));
            strengthTable.refresh();
            ExcelParse.SPower = 0;
            for (int i = 0; i < ExcelParse.strength.size(); i++) {
                ExcelParse.SPower += ExcelParse.strength.get(i).getPower();
            }
            makeSWOTBar();
        }

    }

    void getAddView() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addData.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void refreshTable(MouseEvent event) {
        strengthTable.refresh();
        strengthBar.getData().clear();
        ChartS.getData().clear();
        ChartS.setAnimated(false);
        for (int i = 0; i < ExcelParse.strength.size(); i++) {
            strengthBar.getData().add(new XYChart.Data<>(ExcelParse.strength.get(i).getName(), ExcelParse.strength.get(i).getPower()));
        }
        ChartS.getData().addAll(strengthBar);
        ExcelParse.SPower = 0;
        for (int i = 0; i < ExcelParse.strength.size(); i++) {
            ExcelParse.SPower += ExcelParse.strength.get(i).getPower();
        }
        makeSWOTBar();
    }

    @FXML
    void refreshTableO(MouseEvent event) {
        opportunitiesTable.refresh();
        opportunitiesBar.getData().clear();
        ChartO.getData().clear();
        ChartO.setAnimated(false);
        for (int i = 0; i < ExcelParse.opportunities.size(); i++) {
            opportunitiesBar.getData().add(new XYChart.Data<>(ExcelParse.opportunities.get(i).getName(), ExcelParse.opportunities.get(i).getPower()));
        }
        ChartO.getData().addAll(opportunitiesBar);
        ExcelParse.OPower = 0;
        for (int i = 0; i < ExcelParse.opportunities.size(); i++) {
            ExcelParse.OPower += ExcelParse.opportunities.get(i).getPower();
        }
        makeSWOTBar();
    }

    @FXML
    void refreshTableT(MouseEvent event) {
        threatsTable.refresh();
        threatsBar.getData().clear();
        ChartT.getData().clear();
        ChartT.setAnimated(false);
        for (int i = 0; i < ExcelParse.threats.size(); i++) {
            threatsBar.getData().add(new XYChart.Data<>(ExcelParse.threats.get(i).getName(), ExcelParse.threats.get(i).getPower()));
        }
        ChartT.getData().addAll(threatsBar);
        ExcelParse.TPower = 0;
        for (int i = 0; i < ExcelParse.threats.size(); i++) {
            ExcelParse.TPower += ExcelParse.threats.get(i).getPower();
        }
        makeSWOTBar();
    }

    @FXML
    void refreshWTable(MouseEvent event) {
        weaknessTable.refresh();
        weaknessBar.getData().clear();
        ChartW.getData().clear();
        ChartW.setAnimated(false);
        for (int i = 0; i < ExcelParse.weakness.size(); i++) {
            weaknessBar.getData().add(new XYChart.Data<>(ExcelParse.weakness.get(i).getName(), ExcelParse.weakness.get(i).getPower()));
        }
        ChartW.getData().addAll(weaknessBar);
        ExcelParse.WPower = 0;
        for (int i = 0; i < ExcelParse.weakness.size(); i++) {
            ExcelParse.WPower += ExcelParse.weakness.get(i).getPower();
        }
        makeSWOTBar();
    }


    public void changeNameCellEvent(TableColumn.CellEditEvent editedCell) {
        Data selectedData = strengthTable.getSelectionModel().getSelectedItem();
        selectedData.setName(editedCell.getNewValue().toString());
    }

    @FXML
    void changeNameCellEventO(TableColumn.CellEditEvent editedCell) {
        Data selectedData = opportunitiesTable.getSelectionModel().getSelectedItem();
        selectedData.setName(editedCell.getNewValue().toString());
    }

    @FXML
    void changeNameCellEventT(TableColumn.CellEditEvent editedCell) {
        Data selectedData = threatsTable.getSelectionModel().getSelectedItem();
        selectedData.setName(editedCell.getNewValue().toString());
    }

    @FXML
    void changeNameCellEventW(TableColumn.CellEditEvent editedCell) {
        Data selectedData = weaknessTable.getSelectionModel().getSelectedItem();
        selectedData.setName(editedCell.getNewValue().toString());
    }

    public void changeActionCellEvent(TableColumn.CellEditEvent editedCell) {
        Data selectedData = strengthTable.getSelectionModel().getSelectedItem();
        selectedData.setAction(editedCell.getNewValue().toString());
    }

    @FXML
    void changeActionCellEventO(TableColumn.CellEditEvent editedCell) {
        Data selectedData = opportunitiesTable.getSelectionModel().getSelectedItem();
        selectedData.setAction(editedCell.getNewValue().toString());
    }

    @FXML
    void changeActionCellEventT(TableColumn.CellEditEvent editedCell) {
        Data selectedData = opportunitiesTable.getSelectionModel().getSelectedItem();
        selectedData.setAction(editedCell.getNewValue().toString());
    }

    @FXML
    void changeActionCellEventW(TableColumn.CellEditEvent editedCell) {
        Data selectedData = opportunitiesTable.getSelectionModel().getSelectedItem();
        selectedData.setAction(editedCell.getNewValue().toString());
    }

    public void changeImportanceCellEvent(TableColumn.CellEditEvent editedCell) {
        Data selectedData = strengthTable.getSelectionModel().getSelectedItem();
        selectedData.setImportance(editedCell.getNewValue().toString());
        selectedData.setPower(Double.parseDouble(editedCell.getNewValue().toString()) *
                Double.parseDouble(selectedData.getProbability()));
    }

    @FXML
    void changeImportanceCellEventO(TableColumn.CellEditEvent editedCell) {
        Data selectedData = opportunitiesTable.getSelectionModel().getSelectedItem();
        selectedData.setImportance(editedCell.getNewValue().toString());
        selectedData.setPower(Double.parseDouble(editedCell.getNewValue().toString()) *
                Double.parseDouble(selectedData.getProbability()));
    }

    @FXML
    void changeImportanceCellEventT(TableColumn.CellEditEvent editedCell) {
        Data selectedData = threatsTable.getSelectionModel().getSelectedItem();
        selectedData.setImportance(editedCell.getNewValue().toString());
        selectedData.setPower(Double.parseDouble(editedCell.getNewValue().toString()) *
                Double.parseDouble(selectedData.getProbability()));
    }

    @FXML
    void changeImportanceCellEventW(TableColumn.CellEditEvent editedCell) {
        Data selectedData = weaknessTable.getSelectionModel().getSelectedItem();
        selectedData.setImportance(editedCell.getNewValue().toString());
        selectedData.setPower(Double.parseDouble(editedCell.getNewValue().toString()) *
                Double.parseDouble(selectedData.getProbability()));
    }

    public void changeProbabilityCellEvent(TableColumn.CellEditEvent editedCell) {
        Data selectedData = strengthTable.getSelectionModel().getSelectedItem();
        selectedData.setProbability(editedCell.getNewValue().toString());
        selectedData.setPower(Double.parseDouble(editedCell.getNewValue().toString()) *
                Double.parseDouble(selectedData.getImportance()));
    }

    @FXML
    void changeProbabilityCellEventO(TableColumn.CellEditEvent editedCell) {
        Data selectedData = opportunitiesTable.getSelectionModel().getSelectedItem();
        selectedData.setProbability(editedCell.getNewValue().toString());
        selectedData.setPower(Double.parseDouble(editedCell.getNewValue().toString()) *
                Double.parseDouble(selectedData.getImportance()));
    }

    @FXML
    void changeProbabilityCellEventT(TableColumn.CellEditEvent editedCell) {
        Data selectedData = threatsTable.getSelectionModel().getSelectedItem();
        selectedData.setProbability(editedCell.getNewValue().toString());
        selectedData.setPower(Double.parseDouble(editedCell.getNewValue().toString()) *
                Double.parseDouble(selectedData.getImportance()));
    }

    @FXML
    void changeProbabilityCellEventW(TableColumn.CellEditEvent editedCell) {
        Data selectedData = weaknessTable.getSelectionModel().getSelectedItem();
        selectedData.setProbability(editedCell.getNewValue().toString());
        selectedData.setPower(Double.parseDouble(editedCell.getNewValue().toString()) *
                Double.parseDouble(selectedData.getImportance()));
    }

    @FXML
    public void initialize() {
        colNumS.setCellValueFactory(new PropertyValueFactory<>("number"));
        colNameS.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNameS.setCellFactory(TextFieldTableCell.forTableColumn());
        colActionsS.setCellValueFactory(new PropertyValueFactory<>("action"));
        colActionsS.setCellFactory(TextFieldTableCell.forTableColumn());
        colImportanceS.setCellValueFactory(new PropertyValueFactory<>("importance"));
        colImportanceS.setCellFactory(TextFieldTableCell.forTableColumn());
        colProbabilityS.setCellValueFactory(new PropertyValueFactory<>("probability"));
        colProbabilityS.setCellFactory(TextFieldTableCell.forTableColumn());
        colPowerS.setCellValueFactory(new PropertyValueFactory<>("power"));
        strengthTable.setEditable(true);

        colNumW.setCellValueFactory(new PropertyValueFactory<>("number"));
        colNameW.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNameW.setCellFactory(TextFieldTableCell.forTableColumn());
        colActionsW.setCellValueFactory(new PropertyValueFactory<>("action"));
        colActionsW.setCellFactory(TextFieldTableCell.forTableColumn());
        colImportanceW.setCellValueFactory(new PropertyValueFactory<>("importance"));
        colImportanceW.setCellFactory(TextFieldTableCell.forTableColumn());
        colProbabilityW.setCellValueFactory(new PropertyValueFactory<>("probability"));
        colProbabilityW.setCellFactory(TextFieldTableCell.forTableColumn());
        colPowerW.setCellValueFactory(new PropertyValueFactory<>("power"));
        weaknessTable.setEditable(true);

        colNumO.setCellValueFactory(new PropertyValueFactory<>("number"));
        colNameO.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNameO.setCellFactory(TextFieldTableCell.forTableColumn());
        colActionsO.setCellValueFactory(new PropertyValueFactory<>("action"));
        colActionsO.setCellFactory(TextFieldTableCell.forTableColumn());
        colImportanceO.setCellValueFactory(new PropertyValueFactory<>("importance"));
        colImportanceO.setCellFactory(TextFieldTableCell.forTableColumn());
        colProbabilityO.setCellValueFactory(new PropertyValueFactory<>("probability"));
        colProbabilityO.setCellFactory(TextFieldTableCell.forTableColumn());
        colPowerO.setCellValueFactory(new PropertyValueFactory<>("power"));
        opportunitiesTable.setEditable(true);

        colNumT.setCellValueFactory(new PropertyValueFactory<>("number"));
        colNameT.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNameT.setCellFactory(TextFieldTableCell.forTableColumn());
        colActionsT.setCellValueFactory(new PropertyValueFactory<>("action"));
        colActionsT.setCellFactory(TextFieldTableCell.forTableColumn());
        colImportanceT.setCellValueFactory(new PropertyValueFactory<>("importance"));
        colImportanceT.setCellFactory(TextFieldTableCell.forTableColumn());
        colProbabilityT.setCellValueFactory(new PropertyValueFactory<>("probability"));
        colProbabilityT.setCellFactory(TextFieldTableCell.forTableColumn());
        colPowerT.setCellValueFactory(new PropertyValueFactory<>("power"));
        threatsTable.setEditable(true);

        threatsTable.setItems(ExcelParse.threats);
        opportunitiesTable.setItems(ExcelParse.opportunities);
        weaknessTable.setItems(ExcelParse.weakness);
        strengthTable.setItems(ExcelParse.strength);

    }

    @FXML
    public void deleteData(MouseEvent mouseEvent) {
        String number = deleteStrS.getText();
        tableType = 1;
        deleteStr(number);
    }

    @FXML
    void deleteDataO(MouseEvent event) {
        String number = deleteStrO.getText();
        tableType = 3;
        deleteStr(number);
    }

    @FXML
    void deleteDataT(MouseEvent event) {
        String number = deleteStrT.getText();
        tableType = 4;
        deleteStr(number);
    }

    @FXML
    void deleteDataW(MouseEvent event) {
        String number = deleteStrW.getText();
        tableType = 2;
        deleteStr(number);
    }

    private void deleteStr(String number) {
        if (!number.isEmpty()) {
            if (tableType == 1) {
                ExcelParse.strength.remove(Integer.parseInt(number) - 1);
                for (int i = 0; i < ExcelParse.strength.size(); i++) {
                    ExcelParse.strength.get(i).setNumber(i + 1);
                }
                strengthTable.refresh();
                ExcelParse.SPower = 0;
                for (int i = 0; i < ExcelParse.strength.size(); i++) {
                    ExcelParse.SPower += ExcelParse.strength.get(i).getPower();
                }
                makeSWOTBar();
            } else if (tableType == 2) {
                ExcelParse.weakness.remove(Integer.parseInt(number) - 1);
                for (int i = 0; i < ExcelParse.weakness.size(); i++) {
                    ExcelParse.weakness.get(i).setNumber(i + 1);
                }
                weaknessTable.refresh();
                ExcelParse.WPower = 0;
                for (int i = 0; i < ExcelParse.weakness.size(); i++) {
                    ExcelParse.WPower += ExcelParse.weakness.get(i).getPower();
                }
                makeSWOTBar();
            } else if (tableType == 3) {
                ExcelParse.opportunities.remove(Integer.parseInt(number) - 1);
                for (int i = 0; i < ExcelParse.opportunities.size() - 1; i++) {
                    ExcelParse.opportunities.get(i).setNumber(i + 1);
                }
                opportunitiesTable.refresh();
                ExcelParse.OPower = 0;
                for (int i = 0; i < ExcelParse.opportunities.size(); i++) {
                    ExcelParse.OPower += ExcelParse.opportunities.get(i).getPower();
                }
                makeSWOTBar();
            } else if (tableType == 4) {
                ExcelParse.threats.remove(Integer.parseInt(number) - 1);
                for (int i = 0; i < ExcelParse.threats.size() - 1; i++) {
                    ExcelParse.threats.get(i).setNumber(i + 1);
                }
                threatsTable.refresh();
                ExcelParse.TPower = 0;
                for (int i = 0; i < ExcelParse.threats.size(); i++) {
                    ExcelParse.TPower += ExcelParse.threats.get(i).getPower();
                }
                makeSWOTBar();
            }
        }
    }
}
