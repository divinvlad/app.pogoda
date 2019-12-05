package by.divin.weather.service.math;

import by.divin.weather.model.Overcast;
import by.divin.weather.model.Phenomena;
import by.divin.weather.model.WeatherInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MathServiceImpl implements IMathService {

    @Override
    public double calculateAverageTemperature(List<WeatherInfo> data) {
        return round(data.stream()
                .mapToDouble(WeatherInfo::getTemperature)
                .average()
                .orElse(0));
    }

    @Override
    public double calculateAverageWindSpeed(List<WeatherInfo> data) {
        return round(data.stream()
                .mapToDouble(WeatherInfo::getWindSpeed)
                .average()
                .orElse(0));
    }

    @Override
    public double calculateAveragePressure(List<WeatherInfo> data) {
        return round(data.stream()
                .mapToDouble(WeatherInfo::getPressure)
                .average()
                .orElse(0));
    }

    @Override
    public int calculateSunnyDayQuantity(List<WeatherInfo> data, int yearQuantity) {
        return (int) data.stream()
                .map(WeatherInfo::getOvercast)
                .filter(s -> Overcast.SUNNY.getLabel().equalsIgnoreCase(s))
                .count() / yearQuantity;
    }

    @Override
    public int calculateRainyDayQuantity(List<WeatherInfo> data, int yearQuantity) {
        return (int) data.stream()
                .map(WeatherInfo::getPhenomena)
                .filter(s -> Phenomena.RAIN.getLabel().equalsIgnoreCase(s))
                .count() / yearQuantity;
    }

    @Override
    public int calculateSnowyDayQuantity(List<WeatherInfo> data, int yearQuantity) {
        return (int) data.stream()
                .map(WeatherInfo::getPhenomena)
                .filter(s -> Phenomena.SNOW.getLabel().equalsIgnoreCase(s))
                .count() / yearQuantity;
    }

    @Override
    public int calculateCloudyDayQuantity(List<WeatherInfo> data, int yearQuantity) {
        return (int) data.stream()
                .map(WeatherInfo::getOvercast)
                .filter(s -> Overcast.CLOUDY.getLabel().equalsIgnoreCase(s))
                .count() / yearQuantity;
    }

    @Override
    public int calculateStormDayQuantity(List<WeatherInfo> data, int yearQuantity) {
        return (int) data.stream()
                .map(WeatherInfo::getPhenomena)
                .filter(s -> Phenomena.STORM.getLabel().equalsIgnoreCase(s))
                .count() / yearQuantity;
    }

    @Override
    public double calculateDoubleDifference(List<Double> data) {
        List<Double> differenceList = new LinkedList<>();
        for (int i = 1; i < data.size(); i++) {
            differenceList.add(data.get(i) - data.get(i - 1));
        }

        return round(differenceList.stream().mapToDouble((x) -> x).sum());
    }

    @Override
    public int calculateIntegerDifference(List<Integer> data) {
        List<Integer> differenceList = new LinkedList<>();
        for (int i = 1; i < data.size(); i++) {
            differenceList.add(data.get(i) - data.get(i - 1));
        }

        return differenceList.stream().mapToInt((x) -> x).sum();
    }

    @Override
    public WeatherInfo findMaxTemperature(List<WeatherInfo> data) {
        return data.stream().max(Comparator.comparingInt(WeatherInfo::getTemperature)).get();
    }

    @Override
    public WeatherInfo findMinTemperature(List<WeatherInfo> data) {
        return data.stream().min(Comparator.comparingInt(WeatherInfo::getTemperature)).get();
    }

    @Override
    public WeatherInfo findMaxPressure(List<WeatherInfo> data) {
        return data.stream().max(Comparator.comparingInt(WeatherInfo::getPressure)).get();
    }

    @Override
    public WeatherInfo findMinPressure(List<WeatherInfo> data) {
        return data.stream().min(Comparator.comparingInt(WeatherInfo::getPressure)).get();
    }

    private double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
