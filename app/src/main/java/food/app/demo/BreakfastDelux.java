package food.app.demo;

public class BreakfastDelux {

    private String name;
    private String price;
    private String uri;

    public BreakfastDelux() {
    }

    public BreakfastDelux(String name, String price, String uri) {
        this.name = name;
        this.price = price;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
