package by.divin.weather.mapper;

import by.divin.weather.model.WeatherInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.LinkedList;
import java.util.List;

public class DBObjectMapper {
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";
    private static final String TEMPERATURE = "temperature";
    private static final String PRESSURE = "pressure";
    private static final String OVERCAST = "overcast";
    private static final String PHENOMENA = "phenomena";
    private static final String WIND_SPEED = "windSpeed";

    public List<BasicDBObject> mapWeatherInfosToDBObjects(List<WeatherInfo> weatherInfoList) {
        List<BasicDBObject> result = new LinkedList<>();
        for (WeatherInfo weatherInfo : weatherInfoList) {
            result.add(mapWeatherInfoToDBObject(weatherInfo));
        }
        return result;
    }

    public List<WeatherInfo> mapDBObjectToWeatherInfo(List<DBObject> dbObjects) {
        List<WeatherInfo> result = new LinkedList<>();
        for (DBObject object : dbObjects) {
            result.add(mapDBObjectToWeatherInfo(object));
        }
        return result;
    }

    private WeatherInfo mapDBObjectToWeatherInfo(DBObject object) {
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setYear((Integer) object.get(YEAR));
        weatherInfo.setMonth((Integer) object.get(MONTH));
        weatherInfo.setDay((Integer) object.get(DAY));
        weatherInfo.setTemperature((Integer) object.get(TEMPERATURE));
        weatherInfo.setPressure((Integer) object.get(PRESSURE));
        weatherInfo.setOvercast((String) object.get(OVERCAST));
        weatherInfo.setPhenomena((String) object.get(PHENOMENA));
        weatherInfo.setWindSpeed((Integer) object.get(WIND_SPEED));
        return weatherInfo;
    }

    private BasicDBObject mapWeatherInfoToDBObject(WeatherInfo weatherInfo) {
        BasicDBObject document = new BasicDBObject();
        document.put(YEAR, weatherInfo.getYear());
        document.put(MONTH, weatherInfo.getMonth());
        document.put(DAY, weatherInfo.getDay());
        document.put(TEMPERATURE, weatherInfo.getTemperature());
        document.put(PRESSURE, weatherInfo.getPressure());
        document.put(OVERCAST, weatherInfo.getOvercast());
        document.put(PHENOMENA, weatherInfo.getPhenomena());
        document.put(WIND_SPEED, weatherInfo.getWindSpeed());
        return document;
    }

}
