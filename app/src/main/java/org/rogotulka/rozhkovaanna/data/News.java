package org.rogotulka.rozhkovaanna.data;

import android.support.annotation.NonNull;

import java.util.Date;

public class News implements Comparable<News> {

    private String title;
    private String description;
    private Date date;
    private String image;
    private Source source;

    @Override
    public int compareTo(News another) {
        return another.getDate().compareTo(getDate());
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

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (description != null ? !description.equals(news.description) : news.description != null)
            return false;
        if (date != null ? !date.equals(news.date) : news.date != null) return false;
        return !(image != null ? !image.equals(news.image) : news.image != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
