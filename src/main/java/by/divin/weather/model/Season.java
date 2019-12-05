package by.divin.weather.model;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public enum Season {
    SUMMER(new LinkedList() {{
        add(Calendar.JUNE);
        add(Calendar.JULY);
        add(Calendar.AUGUST);
    }}),
    AUTUMN(new LinkedList() {{
        add(Calendar.SEPTEMBER);
        add(Calendar.OCTOBER);
        add(Calendar.NOVEMBER);
    }}),
    WINTER(new LinkedList() {{
        add(Calendar.DECEMBER);
        add(Calendar.JANUARY);
        add(Calendar.FEBRUARY);
    }}),
    SPRING(new LinkedList() {{
        add(Calendar.MARCH);
        add(Calendar.APRIL);
        add(Calendar.MAY);
    }});

    private List<Integer> months;

    Season(List<Integer> months) {
        this.months = months;
    }

    public List<Integer> getMonths() {
        return months;
    }
}
