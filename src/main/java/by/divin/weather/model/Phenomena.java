package by.divin.weather.model;

public enum Phenomena {
    RAIN("rain", "Rain"),
    STORM("storm", "Storm"),
    SNOW("snow", "Snow");

    private String name;
    private String label;

    Phenomena(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public static String getPhenomenaLabelBySrcName(String name) {
        for (Phenomena phenomena : Phenomena.values()) {
            if (name.contains(phenomena.getName())) {
                return phenomena.getLabel();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }
}
