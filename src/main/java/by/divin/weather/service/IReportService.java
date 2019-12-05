package by.divin.weather.service;

import by.divin.weather.model.WeatherInfo;
import by.divin.weather.model.report.ConclusionReport;
import by.divin.weather.model.report.FirstReport;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

public interface IReportService {
    List<FirstReport> formFirstReport() throws UnknownHostException;

    Map<Integer, Double> formGraphMonthlyTemperatureReport(String month) throws UnknownHostException;

    List<WeatherInfo> formExtremumMonthlyTemperatureInfo(String month) throws UnknownHostException;

    Map<Integer, Double> formGraphMonthlyPressureReport(String month) throws UnknownHostException;

    List<WeatherInfo> formExtremumMonthlyPressureInfo(String month) throws UnknownHostException;

    Map<String, Integer> formYearlyDayWeatherReport(int year) throws UnknownHostException;

    ConclusionReport formConclusionReport() throws UnknownHostException;
}
