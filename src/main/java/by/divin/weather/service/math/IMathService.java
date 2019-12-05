package by.divin.weather.service.math;

import by.divin.weather.model.WeatherInfo;

import java.util.List;

public interface IMathService {
    double calculateAverageTemperature(List<WeatherInfo> data);

    double calculateAverageWindSpeed(List<WeatherInfo> data);

    double calculateAveragePressure(List<WeatherInfo> data);

    int calculateSunnyDayQuantity(List<WeatherInfo> data, int yearQuantity);

    int calculateRainyDayQuantity(List<WeatherInfo> data, int yearQuantity);

    int calculateSnowyDayQuantity(List<WeatherInfo> data, int yearQuantity);

    int calculateCloudyDayQuantity(List<WeatherInfo> data, int yearQuantity);

    int calculateStormDayQuantity(List<WeatherInfo> data, int yearQuantity);

    double calculateDoubleDifference(List<Double> data);

    int calculateIntegerDifference(List<Integer> data);

    WeatherInfo findMaxTemperature(List<WeatherInfo> data);

    WeatherInfo findMinTemperature(List<WeatherInfo> data);

    WeatherInfo findMaxPressure(List<WeatherInfo> data);

    WeatherInfo findMinPressure(List<WeatherInfo> data);

}
