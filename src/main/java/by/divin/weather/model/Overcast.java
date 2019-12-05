package by.divin.weather.model;

public enum Overcast {
    CLOUDY("dull", "Cloudy"),
    SUNNY("sun", "Sunny");

    private String name;
    private String label;

    Overcast(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public static String getOvercastLabelBySrcName(String name) {
        for (Overcast overcast : Overcast.values()) {
            if (name.contains(overcast.getName())) {
                return overcast.getLabel();
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
