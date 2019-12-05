package by.divin.weather.controller;

import by.divin.weather.model.Overcast;
import by.divin.weather.model.Phenomena;
import by.divin.weather.model.report.ConclusionReport;
import by.divin.weather.service.IReportService;
import by.divin.weather.service.IReportServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.UnknownHostException;

public class ConclusionReportController {
    @FXML
    private TextField summerTemperatureDifference;

    @FXML
    private TextField autumnTemperatureDifference;

    @FXML
    private TextField winterTemperatureDifference;

    @FXML
    private TextField springTemperatureDifference;

    @FXML
    private TextField pressureDifference;

    @FXML
    private TextField snowDayDifference;

    @FXML
    private TextField rainDifference;

    @FXML
    private TextField stormDifference;

    @FXML
    private TextField sunDifference;

    @FXML
    private TextField cloudDifference;

    public void initialize() throws UnknownHostException {
        IReportService reportService = new IReportServiceImpl();
        ConclusionReport conclusionReport = reportService.formConclusionReport();

        summerTemperatureDifference.setText(String.valueOf(conclusionReport.getSummerTemperatureDifference()));
        autumnTemperatureDifference.setText(String.valueOf(conclusionReport.getAutumnTemperatureDifference()));
        winterTemperatureDifference.setText(String.valueOf(conclusionReport.getWinterTemperatureDifference()));
        springTemperatureDifference.setText(String.valueOf(conclusionReport.getSpringTemperatureDifference()));
        pressureDifference.setText(String.valueOf(conclusionReport.getPressureDifference()));
        snowDayDifference.setText(conclusionReport.getPhenomenaDifference().get(Phenomena.SNOW).toString());
        rainDifference.setText(conclusionReport.getPhenomenaDifference().get(Phenomena.RAIN).toString());
        stormDifference.setText(conclusionReport.getPhenomenaDifference().get(Phenomena.STORM).toString());
        sunDifference.setText(conclusionReport.getOvercastDifference().get(Overcast.SUNNY).toString());
        cloudDifference.setText(conclusionReport.getOvercastDifference().get(Overcast.CLOUDY).toString());
    }
}
