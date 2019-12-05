package by.divin.weather.model;

import java.util.Objects;

public class WeatherInfo {
    private int year;
    private int month;
    private int day;
    private int temperature;
    private int pressure;
    private String overcast;
    private String phenomena;
    private int windSpeed;

    public WeatherInfo() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public String getOvercast() {
        return overcast;
    }

    public void setOvercast(String overcast) {
        this.overcast = overcast;
    }

    public String getPhenomena() {
        return phenomena;
    }

    public void setPhenomena(String phenomena) {
        this.phenomena = phenomena;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherInfo that = (WeatherInfo) o;
        return year == that.year &&
                month == that.month &&
                day == that.day &&
                temperature == that.temperature &&
                pressure == that.pressure &&
                windSpeed == that.windSpeed &&
                Objects.equals(overcast, that.overcast) &&
                Objects.equals(phenomena, that.phenomena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day, temperature, pressure, overcast, phenomena, windSpeed);
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", overcast='" + overcast + '\'' +
                ", phenomena='" + phenomena + '\'' +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
