package food.app.demo;

public class Restuarant {
    String name;
    String distance;
    String texts;
    String ImageUri;

    public Restuarant() {
    }

    public Restuarant(String name, String distance, String texts, String imageUri) {
        this.name = name;
        this.distance = distance;
        this.texts = texts;
        ImageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTexts() {
        return texts;
    }

    public void setText(String text) {
        this.texts = text;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }
}
