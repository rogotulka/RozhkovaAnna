package org.rogotulka.rozhkovaanna.data;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class News implements Comparable<News> {

    private static SimpleDateFormat FORMATTER =
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    private String title;
    private String description;
    private Date date;
    private URL image;
    private String category;

    @Override
    public int compareTo(News another) {
        if (getDate() == null || another.getDate() == null)
            return 0;
        return getDate().compareTo(another.getDate());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
