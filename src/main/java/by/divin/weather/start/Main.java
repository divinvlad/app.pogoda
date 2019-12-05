package by.divin.weather.start;

import by.divin.weather.model.WeatherInfo;
import by.divin.weather.scraper.Scraper;
import by.divin.weather.service.db.IDBService;
import by.divin.weather.service.db.MongoDBServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;

public class Main extends Application {

    private Parent rootNode;

    public static void main(final String[] args) throws IOException {
        updateData();
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Main.fxml"));
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(rootNode));
        stage.show();
    }

    private static void updateData() throws IOException {
        final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
        final int CURRENT_MONTH = Calendar.getInstance().get(Calendar.MONTH) + 1;
        final int START_YEAR = 1998;

        IDBService idbService = new MongoDBServiceImpl();
        WeatherInfo weatherInfo = idbService.getLastData();
        Scraper scraper = new Scraper();
        if (weatherInfo == null) {
            idbService.saveData(scraper.getAllData(START_YEAR));
        } else if (CURRENT_YEAR != weatherInfo.getYear()) {
            idbService.saveData(scraper.getAllData(weatherInfo.getYear() + 1));
        } else if (CURRENT_MONTH > weatherInfo.getMonth()) {
            idbService.saveData(scraper.getWeatherByDate(CURRENT_YEAR, weatherInfo.getMonth() + 1));
        }

    }
}
