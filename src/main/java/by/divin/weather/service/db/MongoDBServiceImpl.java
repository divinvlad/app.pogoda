package by.divin.weather.service.db;

import by.divin.weather.mapper.DBObjectMapper;
import by.divin.weather.model.Season;
import by.divin.weather.model.WeatherInfo;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

public class MongoDBServiceImpl implements IDBService {
    private static final String DB_COLLECTION_NAME = "weather";

    private DBObjectMapper dbObjectMapper = new DBObjectMapper();

    @Override
    public void saveData(List<WeatherInfo> weatherInfoList) throws UnknownHostException {
        DB database = createDBConnection();
        database.createCollection(DB_COLLECTION_NAME, null);

        DBCollection collection = database.getCollection(DB_COLLECTION_NAME);
        List<BasicDBObject> objects = dbObjectMapper.mapWeatherInfosToDBObjects(weatherInfoList);
        collection.insert(objects);
    }

    @Override
    public List<WeatherInfo> getAllData() throws UnknownHostException {
        DB database = createDBConnection();

        DBCollection collection = database.getCollection(DB_COLLECTION_NAME);
        List<DBObject> objects = collection.find().toArray();
        return dbObjectMapper.mapDBObjectToWeatherInfo(objects);
    }

    @Override
    public List<WeatherInfo> getDataByMonth(int month) throws UnknownHostException {
        final String MONTH_FIELD = "month";

        DB database = createDBConnection();
        DBCollection collection = database.getCollection(DB_COLLECTION_NAME);

        BasicDBObject query = new BasicDBObject();
        query.put(MONTH_FIELD, month);

        List<DBObject> objects = collection.find(query).toArray();
        return dbObjectMapper.mapDBObjectToWeatherInfo(objects);
    }

    @Override
    public List<WeatherInfo> getDataByYear(int year) throws UnknownHostException {
        final String YEAR_FIELD = "year";

        DB database = createDBConnection();
        DBCollection collection = database.getCollection(DB_COLLECTION_NAME);

        BasicDBObject query = new BasicDBObject();
        query.put(YEAR_FIELD, year);

        List<DBObject> objects = collection.find(query).toArray();
        return dbObjectMapper.mapDBObjectToWeatherInfo(objects);
    }

    @Override
    public List<WeatherInfo> getDataBySeason(Season season) throws UnknownHostException {
        List<WeatherInfo> result = new LinkedList<>();
        List<Integer> months = season.getMonths();
        for (Integer month : months) {
            result.addAll(getDataByMonth(month + 1));
        }
        return result;
    }

    @Override
    public WeatherInfo getLastData() throws UnknownHostException {
        List<WeatherInfo> data = getAllData();
        if (data == null || data.isEmpty()) {
            return null;
        }
        return data.stream()
                .reduce((a, b) -> a.getYear() > b.getYear() || (a.getMonth() > b.getMonth() && a.getYear() == b.getYear()) ? a : b)
                .get();
    }

    private DB createDBConnection() throws UnknownHostException {
        final String DB_HOST = "localhost";
        final int DB_PORT = 27017;
        final String DB_NAME = "test";

        MongoClient mongoClient = new MongoClient(DB_HOST, DB_PORT);
        return mongoClient.getDB(DB_NAME);
    }
}