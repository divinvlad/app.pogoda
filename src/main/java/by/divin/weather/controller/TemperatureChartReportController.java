package by.andruhovich.weather.controller;

import by.divin.weather.model.WeatherInfo;
import by.divin.weather.service.IReportService;
import by.divin.weather.service.IReportServiceImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.UnknownHostException;
import java.text.DateFormatSymbols;
import java.util.*;

public class TemperatureChartReportController {
    @FXML
    private LineChart<Integer, Double> lineChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TextField minTemperatureYear;

    @FXML
    private TextField maxTemperatureYear;

    @FXML
    private TextField minTemperature;

    @FXML
    private TextField maxTemperature;

    @FXML
    private ComboBox<String> comboBox;

    public void initialize() {
        comboBox.setItems(FXCollections.observableArrayList(getComboBox()));
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);

        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            lineChart.getData().clear();
            LineChart.Series series = new LineChart.Series();
            try {
                Map<Integer, Double> data = formData(newValue);
                setAxisBounds(data);
                setAdditionalInfo(formAdditionalData(newValue));
                for (Map.Entry<Integer, Double> entry : data.entrySet()) {
                    series.getData().add(new LineChart.Data(entry.getKey(), entry.getValue()));
                }
                lineChart.getData().add(series);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        });
    }

    private List<String> getComboBox() {
        return Arrays.asList(new DateFormatSymbols(Locale.ENGLISH).getMonths());
    }

    private void setAdditionalInfo(List<WeatherInfo> data) {
        minTemperature.setText(String.valueOf(data.get(0).getTemperature()));
        maxTemperature.setText(String.valueOf(data.get(1).getTemperature()));
        minTemperatureYear.setText(String.valueOf(data.get(0).getYear()));
        maxTemperatureYear.setText(String.valueOf(data.get(1).getYear()));
    }

    private void setAxisBounds(Map<Integer, Double> data) {
        xAxis.setLowerBound(data.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getKey)).get().getKey() - 1);
        xAxis.setUpperBound(data.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getKey)).get().getKey() + 1);
        yAxis.setLowerBound(Collections.min(data.values()) - 1);
        yAxis.setUpperBound(Collections.max(data.values()) + 1);
    }

    private Map<Integer, Double> formData(String month) throws UnknownHostException {
        IReportService reportService = new IReportServiceImpl();
        return reportService.formGraphMonthlyTemperatureReport(month);
    }

    private List<WeatherInfo> formAdditionalData(String month) throws UnknownHostException {
        IReportService reportService = new IReportServiceImpl();
        return reportService.formExtremumMonthlyTemperatureInfo(month);
    }
}
