package by.divin.weather.model.report;

import java.util.Objects;

public class FirstReport {
    private String month;
    private double temperature;
    private double windSpeed;
    private double pressure;
    private int sunnyDayQuantity;
    private int cloudyDayQuantity;
    private int rainyDayQuantity;
    private int snowyDayQuantity;
    private int stormDayQuantity;

    public FirstReport() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getSunnyDayQuantity() {
        return sunnyDayQuantity;
    }

    public void setSunnyDayQuantity(int sunnyDayQuantity) {
        this.sunnyDayQuantity = sunnyDayQuantity;
    }

    public int getCloudyDayQuantity() {
        return cloudyDayQuantity;
    }

    public void setCloudyDayQuantity(int cloudyDayQuantity) {
        this.cloudyDayQuantity = cloudyDayQuantity;
    }

    public int getRainyDayQuantity() {
        return rainyDayQuantity;
    }

    public void setRainyDayQuantity(int rainyDayQuantity) {
        this.rainyDayQuantity = rainyDayQuantity;
    }

    public int getSnowyDayQuantity() {
        return snowyDayQuantity;
    }

    public void setSnowyDayQuantity(int snowyDayQuantity) {
        this.snowyDayQuantity = snowyDayQuantity;
    }

    public int getStormDayQuantity() {
        return stormDayQuantity;
    }

    public void setStormDayQuantity(int stormDayQuantity) {
        this.stormDayQuantity = stormDayQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstReport that = (FirstReport) o;
        return Double.compare(that.temperature, temperature) == 0 &&
                Double.compare(that.windSpeed, windSpeed) == 0 &&
                Double.compare(that.pressure, pressure) == 0 &&
                sunnyDayQuantity == that.sunnyDayQuantity &&
                cloudyDayQuantity == that.cloudyDayQuantity &&
                rainyDayQuantity == that.rainyDayQuantity &&
                snowyDayQuantity == that.snowyDayQuantity &&
                stormDayQuantity == that.stormDayQuantity &&
                Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, temperature, windSpeed, pressure, sunnyDayQuantity, cloudyDayQuantity, rainyDayQuantity, snowyDayQuantity, stormDayQuantity);
    }

    @Override
    public String toString() {
        return "FirstReport{" +
                "month='" + month + '\'' +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", pressure=" + pressure +
                ", sunnyDayQuantity=" + sunnyDayQuantity +
                ", cloudyDayQuantity=" + cloudyDayQuantity +
                ", rainyDayQuantity=" + rainyDayQuantity +
                ", snowyDayQuantity=" + snowyDayQuantity +
                ", stormDayQuantity=" + stormDayQuantity +
                '}';
    }
}
