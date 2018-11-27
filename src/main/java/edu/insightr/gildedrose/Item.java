package edu.insightr.gildedrose;

import java.io.Serializable;

public class Item implements Serializable {

    private String name;
    private int sellIn;

    private int quality;

    public Item(String name, int sellIn, int quality){
        super();
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }
    public Item(){

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
                '}';
    }
}