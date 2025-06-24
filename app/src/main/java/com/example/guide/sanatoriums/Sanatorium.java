package com.example.guide.sanatoriums;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sanatoriums")
public class Sanatorium {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private String imageUrl;

    public Sanatorium() {

    }

    public Sanatorium(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
