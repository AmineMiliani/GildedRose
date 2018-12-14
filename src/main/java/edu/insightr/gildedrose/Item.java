package edu.insightr.gildedrose;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item implements Serializable {

    private String name;
    private int sellIn;
    private String creationDate;
    private int quality;

    public Item(String name, int sellIn, int quality, String creationDate){
        super();
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.creationDate = creationDate;
    }

    public Item(){

    }
    String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }


    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void RemoveAt(int ligne)
    {}

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", sellIn=" + sellIn +
                ", quality=" + quality +
                ", creationDate=" + creationDate +
                '}';
    }
}