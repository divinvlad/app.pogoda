package by.divin.weather.model.report;

import by.divin.weather.model.Overcast;
import by.divin.weather.model.Phenomena;

import java.util.Map;
import java.util.Objects;

public class ConclusionReport {
    private double summerTemperatureDifference;
    private double autumnTemperatureDifference;
    private double winterTemperatureDifference;
    private double springTemperatureDifference;
    private double pressureDifference;
    private Map<Phenomena, Integer> phenomenaDifference;
    private Map<Overcast, Integer> overcastDifference;

    public ConclusionReport() {
    }

    public double getSummerTemperatureDifference() {
        return summerTemperatureDifference;
    }

    public void setSummerTemperatureDifference(double summerTemperatureDifference) {
        this.summerTemperatureDifference = summerTemperatureDifference;
    }

    public double getAutumnTemperatureDifference() {
        return autumnTemperatureDifference;
    }

    public void setAutumnTemperatureDifference(double autumnTemperatureDifference) {
        this.autumnTemperatureDifference = autumnTemperatureDifference;
    }

    public double getWinterTemperatureDifference() {
        return winterTemperatureDifference;
    }

    public void setWinterTemperatureDifference(double winterTemperatureDifference) {
        this.winterTemperatureDifference = winterTemperatureDifference;
    }

    public double getSpringTemperatureDifference() {
        return springTemperatureDifference;
    }

    public void setSpringTemperatureDifference(double springTemperatureDifference) {
        this.springTemperatureDifference = springTemperatureDifference;
    }

    public double getPressureDifference() {
        return pressureDifference;
    }

    public void setPressureDifference(double pressureDifference) {
        this.pressureDifference = pressureDifference;
    }

    public Map<Phenomena, Integer> getPhenomenaDifference() {
        return phenomenaDifference;
    }

    public void setPhenomenaDifference(Map<Phenomena, Integer> phenomenaDifference) {
        this.phenomenaDifference = phenomenaDifference;
    }

    public Map<Overcast, Integer> getOvercastDifference() {
        return overcastDifference;
    }

    public void setOvercastDifference(Map<Overcast, Integer> overcastDifference) {
        this.overcastDifference = overcastDifference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConclusionReport that = (ConclusionReport) o;
        return Double.compare(that.summerTemperatureDifference, summerTemperatureDifference) == 0 &&
                Double.compare(that.autumnTemperatureDifference, autumnTemperatureDifference) == 0 &&
                Double.compare(that.winterTemperatureDifference, winterTemperatureDifference) == 0 &&
                Double.compare(that.springTemperatureDifference, springTemperatureDifference) == 0 &&
                Double.compare(that.pressureDifference, pressureDifference) == 0 &&
                Objects.equals(phenomenaDifference, that.phenomenaDifference) &&
                Objects.equals(overcastDifference, that.overcastDifference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(summerTemperatureDifference, autumnTemperatureDifference, winterTemperatureDifference, springTemperatureDifference, pressureDifference, phenomenaDifference, overcastDifference);
    }

    @Override
    public String toString() {
        return "ConclusionReport{" +
                "summerTemperatureDifference=" + summerTemperatureDifference +
                ", autumnTemperatureDifference=" + autumnTemperatureDifference +
                ", winterTemperatureDifference=" + winterTemperatureDifference +
                ", springTemperatureDifference=" + springTemperatureDifference +
                ", pressureDifference=" + pressureDifference +
                ", phenomenaDifference=" + phenomenaDifference +
                ", overcastDifference=" + overcastDifference +
                '}';
    }
}
