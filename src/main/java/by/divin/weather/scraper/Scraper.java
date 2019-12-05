package by.divin.weather.scraper;

import by.divin.weather.model.Overcast;
import by.divin.weather.model.Phenomena;
import by.divin.weather.model.WeatherInfo;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Scraper {
    private static final String URL = "https://www.gismeteo.ru/diary/4248";

    public Scraper() {
    }

    public List<WeatherInfo> getAllData(int startYear) throws IOException {
        final int START_MONTH = Calendar.JANUARY + 1;
        final int MONTH_QUANTITY = 12;

        List<WeatherInfo> weatherInfos = new LinkedList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

        for (int year = startYear; year <= currentYear; year++) {
            for (int month = START_MONTH; month <= MONTH_QUANTITY; month++) {
                weatherInfos.addAll(getWeatherByDate(year, month));
                if (month - 1 == currentMonth && year == currentYear) {
                    return weatherInfos;
                }
            }
        }
        return weatherInfos;
    }

    public List<WeatherInfo> getWeatherByDate(int year, int month) throws IOException {
        final String TABLE_ROW_ELEMENT = "tr";

        List<WeatherInfo> weatherInfos = new LinkedList<>();
        try {
            Document doc = Jsoup.connect(URL + "/" + year + "/" + month).get();
            Elements rows = doc.select(TABLE_ROW_ELEMENT);
            for (int i = 2; i < rows.size(); i++) {
                WeatherInfo weatherInfo = mapRowToObject(rows.get(i), year, month);
                if (weatherInfo != null) {
                    weatherInfos.add(weatherInfo);
                }
            }
        } catch (HttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return weatherInfos;
    }

    private WeatherInfo mapRowToObject(Element row, int year, int month) {
        final String TABLE_COLUMN_ELEMENT = "td";
        final String SRC_ATTRIBUTE = "src";
        final int DAY_POSITION = 0;
        final int TEMPERATURE_POSITION = 1;
        final int PRESSURE_POSITION = 2;
        final int OVERCAST_POSITION = 3;
        final int PHENOMENA_POSITION = 4;
        final int WIND_POSITION = 5;

        Elements columns = row.select(TABLE_COLUMN_ELEMENT);
        WeatherInfo weatherInfo = new WeatherInfo();

        weatherInfo.setYear(year);
        weatherInfo.setMonth(month);
        weatherInfo.setDay(Integer.valueOf(columns.get(DAY_POSITION).text()));
        try {
            weatherInfo.setTemperature(extractIntegerAndNegativeValue(columns.get(TEMPERATURE_POSITION)));
            weatherInfo.setPressure(extractIntegerValue(columns.get(PRESSURE_POSITION)));
            weatherInfo.setWindSpeed(extractIntegerValue(columns.get(WIND_POSITION)));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return null;
        }
        if (columns.get(OVERCAST_POSITION).childNodeSize() != 0) {
            weatherInfo.setOvercast(Overcast.getOvercastLabelBySrcName(columns.get(OVERCAST_POSITION).childNode(0).attr(SRC_ATTRIBUTE)));
        }
        if (columns.get(PHENOMENA_POSITION).childNodeSize() != 0) {
            weatherInfo.setPhenomena(Phenomena.getPhenomenaLabelBySrcName(columns.get(PHENOMENA_POSITION).childNode(0).attr(SRC_ATTRIBUTE)));
        }

        return weatherInfo;
    }


    private int extractIntegerValue(Element column) {
        final String NON_DIGIT_REGEX = "\\D+";

        String value = column.text().replaceAll(NON_DIGIT_REGEX, "");
        return Integer.valueOf(value);
    }

    private int extractIntegerAndNegativeValue(Element column) {
        final String NON_DIGIT_REGEX = "T(-?[0-9]+)";
        String value = column.text().replaceAll(NON_DIGIT_REGEX, "");
        return value.indexOf('-') != -1 ? Integer.valueOf(value.substring(1)) * -1 : Integer.valueOf(value);
    }
}
