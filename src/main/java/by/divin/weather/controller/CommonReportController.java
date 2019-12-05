package by.divin.weather.controller;

import by.divin.weather.model.report.FirstReport;
import by.divin.weather.service.IReportService;
import by.divin.weather.service.IReportServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.UnknownHostException;
import java.util.List;

public class CommonReportController {
    @FXML
    private TableView<FirstReport> firstReportTable;

    @FXML
    private TableColumn<FirstReport, String> month;

    @FXML
    private TableColumn<FirstReport, Double> temperature;

    @FXML
    private TableColumn<FirstReport, Double> windSpeed;

    @FXML
    private TableColumn<FirstReport, Double> pressure;

    @FXML
    private TableColumn<FirstReport, Integer> sunnyDays;

    @FXML
    private TableColumn<FirstReport, Integer> cloudyDays;

    @FXML
    private TableColumn<FirstReport, Integer> rainyDays;

    @FXML
    private TableColumn<FirstReport, Integer> snowyDays;

    @FXML
    private TableColumn<FirstReport, Integer> stormDays;

    public void initialize() throws UnknownHostException {
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        temperature.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        windSpeed.setCellValueFactory(new PropertyValueFactory<>("windSpeed"));
        pressure.setCellValueFactory(new PropertyValueFactory<>("pressure"));
        sunnyDays.setCellValueFactory(new PropertyValueFactory<>("sunnyDayQuantity"));
        cloudyDays.setCellValueFactory(new PropertyValueFactory<>("cloudyDayQuantity"));
        rainyDays.setCellValueFactory(new PropertyValueFactory<>("rainyDayQuantity"));
        snowyDays.setCellValueFactory(new PropertyValueFactory<>("snowyDayQuantity"));
        stormDays.setCellValueFactory(new PropertyValueFactory<>("stormDayQuantity"));
        firstReportTable.setItems(formData());
    }

    private ObservableList<FirstReport> formData() throws UnknownHostException {
        IReportService reportService = new IReportServiceImpl();
        List<FirstReport> reports = reportService.formFirstReport();
        return FXCollections.observableArrayList(reports);
    }
}
