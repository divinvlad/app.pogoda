package by.divin.weather.controller;

import by.divin.weather.fxml.LabeledPieChart;
import by.divin.weather.service.IReportService;
import by.divin.weather.service.IReportServiceImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class YearCharacterReportController {
    @FXML
    private AnchorPane yearCharacterAnchorPane;

    @FXML
    private ComboBox<Integer> comboBox;

    private LabeledPieChart pieChart;

    public void initialize() {
        pieChart = new LabeledPieChart();

        comboBox.setItems(FXCollections.observableArrayList(getComboBox()));
        AnchorPane.setTopAnchor(pieChart, 100.0);
        AnchorPane.setLeftAnchor(pieChart, 100.0);
        yearCharacterAnchorPane.getChildren().add(pieChart);

        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            pieChart.getData().clear();
            try {
                Map<String, Integer> data = formData(newValue);
                for (Map.Entry<String, Integer> entry : data.entrySet()) {
                    PieChart.Data element = new PieChart.Data(entry.getKey(), entry.getValue());
                    pieChart.getData().add(element);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        });
    }

    private List<Integer> getComboBox() {
        final int START_YEAR = 1998;
        final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

        List<Integer> years = new LinkedList<>();
        for (int i = START_YEAR; i <= CURRENT_YEAR; i++) {
            years.add(i);
        }
        return years;
    }

    private Map<String, Integer> formData(Integer year) throws UnknownHostException {
        IReportService reportService = new IReportServiceImpl();
        return reportService.formYearlyDayWeatherReport(year);
    }

}
