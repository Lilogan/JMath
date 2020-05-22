import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
    @FXML
    private TextField input;
    @FXML
    private LineChart<Number, Number> chart;
    @FXML
    private Label error;

    @FXML
    private TextField start;
    @FXML
    private TextField end;
    @FXML
    private TextField step;

    @FXML
    private TableView<FunctionProperty> functionTable;
    @FXML
    private TableColumn<FunctionProperty, String> functionName, functionArg, functionExpr;

    @FXML
    private TableView<VariableProperty> variableTable;
    @FXML
    private TableColumn<VariableProperty, String> variableName;
    @FXML
    private TableColumn<VariableProperty, Double> variableValue;

    @FXML
    public void handleValidate(ActionEvent e) {
        clear();
        run(input.getText());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        functionName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        functionArg.setCellValueFactory(cellData -> cellData.getValue().argProperty());
        functionExpr.setCellValueFactory(cellData -> cellData.getValue().exprProperty());

        variableName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        variableValue.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());
    }

    public void clear() {
        error.setVisible(false);
        chart.getData().clear();
    }

    public <T> void addInTable(TableView<T> table, T object) throws IOException {
        if (table.getItems().contains(object)) {
            table.getItems().remove(object);
            table.getItems().add(object);
        } else {
            table.getItems().add(object);
        }
    }

    public void plotFunction(FunctionProperty function, double start, double end, double step) {
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        for (double i = start; i <= end; i = i + step) {
            series.getData().add(new XYChart.Data<Number, Number>(i, function.getFunction().calculate(i)));
        }
        series.getData();
        chart.getData().add(series);

    }

    public void run(String input) {
        Parser parser = new Parser(input);
        try {
            Type type = parser.analyse();
            if (type.equals(Type.FUNCTION)) {
                double start = Double.parseDouble(this.start.getText());
                double end = Double.parseDouble(this.end.getText());
                double step = Double.parseDouble(this.step.getText());
                FunctionProperty function = parser.parseFunction();
                addInTable(functionTable, function);
                plotFunction(function, start, end, step);

            }
            if (type.equals(Type.VARIABLE)) {
                VariableProperty variable = parser.parseVariable();
                addInTable(variableTable, variable);
            }

        } catch (IOException e) {
            error.setText(e.getMessage());
            error.setVisible(true);
        } catch (NumberFormatException e) {
            error.setText("Parameter Invalid");
            error.setVisible(true);
        }

    }

}