package by.divin.weather.service;

import by.divin.weather.model.Overcast;
import by.divin.weather.model.Phenomena;
import by.divin.weather.model.Season;
import by.divin.weather.model.WeatherInfo;
import by.divin.weather.model.report.ConclusionReport;
import by.divin.weather.model.report.FirstReport;
import by.divin.weather.service.db.IDBService;
import by.divin.weather.service.db.MongoDBServiceImpl;
import by.divin.weather.service.math.IMathService;
import by.divin.weather.service.math.MathServiceImpl;

import java.net.UnknownHostException;
import java.text.DateFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

public class IReportServiceImpl implements IReportService {
    private IDBService dbService;

    private IMathService mathService;

    private static final int START_YEAR = 1998;
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    public IReportServiceImpl() {
        dbService = new MongoDBServiceImpl();
        mathService = new MathServiceImpl();
    }

    @Override
    public List<FirstReport> formFirstReport() throws UnknownHostException {
        final int MONTH_QUANTITY = 12;

        List<FirstReport> reports = new LinkedList<>();

        for (int i = 0; i < MONTH_QUANTITY; i++) {
            FirstReport firstReport = new FirstReport();
            List<WeatherInfo> weatherInfos = dbService.getDataByMonth(i + 1);
            firstReport.setMonth(getMonthNameByNumber(i));
            firstReport.setTemperature(mathService.calculateAverageTemperature(weatherInfos));
            firstReport.setWindSpeed(mathService.calculateAverageWindSpeed(weatherInfos));
            firstReport.setPressure(mathService.calculateAveragePressure(weatherInfos));
            firstReport.setCloudyDayQuantity(mathService.calculateCloudyDayQuantity(weatherInfos, CURRENT_YEAR - START_YEAR));
            firstReport.setRainyDayQuantity(mathService.calculateRainyDayQuantity(weatherInfos, CURRENT_YEAR - START_YEAR));
            firstReport.setSnowyDayQuantity(mathService.calculateSnowyDayQuantity(weatherInfos, CURRENT_YEAR - START_YEAR));
            firstReport.setSunnyDayQuantity(mathService.calculateSunnyDayQuantity(weatherInfos, CURRENT_YEAR - START_YEAR));
            firstReport.setStormDayQuantity(mathService.calculateStormDayQuantity(weatherInfos, CURRENT_YEAR - START_YEAR));
            reports.add(firstReport);
        }

        return reports;
    }

    @Override
    public Map<Integer, Double> formGraphMonthlyTemperatureReport(String month) throws UnknownHostException {
        Map<Integer, Double> result = new LinkedHashMap<>();
        List<WeatherInfo> weatherInfos = dbService.getDataByMonth(getMonthNumberByName(month));
        for (int i = START_YEAR; i <= CURRENT_YEAR; i++) {
            List<WeatherInfo> filteredData = filterDataByYear(weatherInfos, i);
            if (!filteredData.isEmpty()) {
                result.put(i, mathService.calculateAverageTemperature(filteredData));
            }
        }
        return result;
    }

    @Override
    public List<WeatherInfo> formExtremumMonthlyTemperatureInfo(String month) throws UnknownHostException {
        List<WeatherInfo> result = new LinkedList<>();
        List<WeatherInfo> weatherInfos = dbService.getDataByMonth(getMonthNumberByName(month));
        result.add(mathService.findMinTemperature(weatherInfos));
        result.add(mathService.findMaxTemperature(weatherInfos));
        return result;
    }

    @Override
    public Map<Integer, Double> formGraphMonthlyPressureReport(String month) throws UnknownHostException {
        Map<Integer, Double> result = new LinkedHashMap<>();
        List<WeatherInfo> weatherInfos = dbService.getDataByMonth(getMonthNumberByName(month));
        for (int i = START_YEAR; i <= CURRENT_YEAR; i++) {
            List<WeatherInfo> filteredData = filterDataByYear(weatherInfos, i);
            if (!filteredData.isEmpty()) {
                result.put(i, mathService.calculateAveragePressure(filteredData));
            }
        }
        return result;
    }

    @Override
    public List<WeatherInfo> formExtremumMonthlyPressureInfo(String month) throws UnknownHostException {
        List<WeatherInfo> result = new LinkedList<>();
        List<WeatherInfo> weatherInfos = dbService.getDataByMonth(getMonthNumberByName(month));
        result.add(mathService.findMinPressure(weatherInfos));
        result.add(mathService.findMaxPressure(weatherInfos));
        return result;
    }

    @Override
    public Map<String, Integer> formYearlyDayWeatherReport(int year) throws UnknownHostException {
        Map<String, Integer> result = new LinkedHashMap<>();
        List<WeatherInfo> weatherInfos = dbService.getDataByYear(year);
        result.put(Overcast.SUNNY.getLabel(), mathService.calculateSunnyDayQuantity(weatherInfos, 1));
        result.put(Overcast.CLOUDY.getLabel(), mathService.calculateCloudyDayQuantity(weatherInfos, 1));
        result.put(Phenomena.RAIN.getLabel(), mathService.calculateRainyDayQuantity(weatherInfos, 1));
        result.put(Phenomena.SNOW.getLabel(), mathService.calculateSnowyDayQuantity(weatherInfos, 1));
        result.put(Phenomena.STORM.getLabel(), mathService.calculateStormDayQuantity(weatherInfos, 1));
        return result;
    }

    @Override
    public ConclusionReport formConclusionReport() throws UnknownHostException {
        ConclusionReport conclusionReport = new ConclusionReport();
        conclusionReport.setSummerTemperatureDifference(calculateSeasonTemperatureDifference(Season.SUMMER));
        conclusionReport.setAutumnTemperatureDifference(calculateSeasonTemperatureDifference(Season.AUTUMN));
        conclusionReport.setWinterTemperatureDifference(calculateSeasonTemperatureDifference(Season.WINTER));
        conclusionReport.setSpringTemperatureDifference(calculateSeasonTemperatureDifference(Season.SPRING));
        conclusionReport.setPressureDifference(calculatePressureDifference());
        conclusionReport.setPhenomenaDifference(calculatePhenomenaDifference());
        conclusionReport.setOvercastDifference(calculateOvercastDifference());

        return conclusionReport;
    }

    private double calculateSeasonTemperatureDifference(Season season) throws UnknownHostException {
        List<Double> seasonAverageList = new LinkedList<>();
        List<WeatherInfo> seasonInfo = dbService.getDataBySeason(season);
        for (int i = START_YEAR; i <= CURRENT_YEAR; i++) {
            List<WeatherInfo> filteredData = filterDataByYear(seasonInfo, i);
            if (!filteredData.isEmpty()) {
                seasonAverageList.add(mathService.calculateAverageTemperature(filteredData));
            }
        }

        return mathService.calculateDoubleDifference(seasonAverageList);
    }

    private double calculatePressureDifference() throws UnknownHostException {
        List<Double> averageList = new LinkedList<>();
        List<WeatherInfo> data = dbService.getAllData();
        for (int i = START_YEAR; i <= CURRENT_YEAR; i++) {
            List<WeatherInfo> filteredData = filterDataByYear(data, i);
            if (!filteredData.isEmpty()) {
                averageList.add(mathService.calculateAveragePressure(filteredData));
            }
        }

        return mathService.calculateDoubleDifference(averageList);
    }

    private Map<Phenomena, Integer> calculatePhenomenaDifference() throws UnknownHostException {
        Map<Phenomena, Integer> result = new LinkedHashMap<>();
        Phenomena[] phenomenas = Phenomena.class.getEnumConstants();
        for (Phenomena phenomena : phenomenas) {
            result.put(phenomena, calculateDifferenceByPhenomena(phenomena));
        }
        return result;
    }

    private Map<Overcast, Integer> calculateOvercastDifference() throws UnknownHostException {
        Map<Overcast, Integer> result = new LinkedHashMap<>();
        Overcast[] overcasts = Overcast.class.getEnumConstants();
        for (Overcast overcast : overcasts) {
            result.put(overcast, calculateDifferenceByOvercast(overcast));
        }
        return result;
    }

    private Integer calculateDifferenceByPhenomena(Phenomena phenomena) throws UnknownHostException {
        List<Integer> averageList = new LinkedList<>();
        List<WeatherInfo> data = dbService.getAllData();
        for (int i = START_YEAR; i <= CURRENT_YEAR; i++) {
            List<WeatherInfo> filteredData = filterDataByYear(data, i);
            if (!filteredData.isEmpty()) {
                switch (phenomena) {
                    case RAIN:
                        averageList.add(mathService.calculateRainyDayQuantity(filteredData, 1));
                        break;
                    case STORM:
                        averageList.add(mathService.calculateStormDayQuantity(filteredData, 1));
                        break;
                    case SNOW:
                        averageList.add(mathService.calculateSnowyDayQuantity(filteredData, 1));
                        break;
                }
            }
        }
        return mathService.calculateIntegerDifference(averageList);
    }

    private Integer calculateDifferenceByOvercast(Overcast overcast) throws UnknownHostException {
        List<Integer> averageList = new LinkedList<>();
        List<WeatherInfo> data = dbService.getAllData();
        for (int i = START_YEAR; i <= CURRENT_YEAR; i++) {
            List<WeatherInfo> filteredData = filterDataByYear(data, i);
            if (!filteredData.isEmpty()) {
                switch (overcast) {
                    case CLOUDY:
                        averageList.add(mathService.calculateCloudyDayQuantity(filteredData, 1));
                        break;
                    case SUNNY:
                        averageList.add(mathService.calculateSunnyDayQuantity(filteredData, 1));
                        break;
                }
            }
        }
        return mathService.calculateIntegerDifference(averageList);
    }

    private List<WeatherInfo> filterDataByYear(List<WeatherInfo> data, int year) {
        return data.stream()
                .filter(s -> s.getYear() == year)
                .collect(Collectors.toList());
    }

    private int getMonthNumberByName(String month) {
        return Arrays.asList(new DateFormatSymbols(Locale.ENGLISH).getMonths()).indexOf(month) + 1;
    }

    private String getMonthNameByNumber(int monthNumber) {
        Calendar calendar = Calendar.getInstance();
        Map<String, Integer> months = calendar.getDisplayNames(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);

        for (Map.Entry<String, Integer> month : months.entrySet()) {
            if (month.getValue() == monthNumber) {
                return month.getKey();
            }
        }
        return null;
    }
}
