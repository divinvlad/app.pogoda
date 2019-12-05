package by.divin.weather.service.db;

import by.divin.weather.model.Season;
import by.divin.weather.model.WeatherInfo;

import java.net.UnknownHostException;
import java.util.List;

public interface IDBService {
    void saveData(List<WeatherInfo> weatherInfoList) throws UnknownHostException;

    List<WeatherInfo> getAllData() throws UnknownHostException;

    List<WeatherInfo> getDataByMonth(int month) throws UnknownHostException;

    List<WeatherInfo> getDataByYear(int year) throws UnknownHostException;

    List<WeatherInfo> getDataBySeason(Season season) throws UnknownHostException;

    WeatherInfo getLastData() throws UnknownHostException;
}