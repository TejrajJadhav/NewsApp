package Entity;

/**
 * Created by Tejraj on 17-Mar-19.
 */

public class MainSourceData {
    public String id;
    public String name;
    public String description;
    public String url;
    public String category;
    public String language;
    public String country;

    public MainSourceData() {

    }

    public MainSourceData(String id, String name, String description, String url, String category, String language, String country) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
    }
}
