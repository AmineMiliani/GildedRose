package edu.insightr.gildedrose;

public class Inventory {

    //TODO (PBZ) : the code is not indented

    private Item[] items;


    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();

        items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20,"05-09-2018" ),
                new Item("Aged Brie", 2, 0,"05-09-2018" ),
                new Item("Elixir of the Mongoose", 5, 7,"08-09-2018" ),
                new Item("Legendary Sulfuras, Hand of Ragnaros", 0, 80,"11-09-2018" ),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20,"11-09-2018" ),
                new Item("Conjured Mana Cake", 1, 16,"11-09-2018" ),
                new Item("Legendary Torse Vaal Hazak alpha", 15, 10, "04-12-2018")

        };


    }

    public Item[] getItems() {
        return items;
    }

    void setItems(Item[] items) {
        this.items = items;
    }


    private void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item != null) {
                if (!item.getName().contains("Legendary")) {
                    int qualityDifference = -1, sellinDifference = -1;

                    if (item.getSellIn() < 0) {
                        qualityDifference *= 2;
                    }
                    if (item.getName().contains("Conjured")) {
                        qualityDifference *= 2;
                    }
                    if (item.getName().contains("Aged")) {
                        qualityDifference = 1;
                    }
                    if (item.getName().contains("concert")) {
                        if (item.getSellIn() < 0) {
                            item.setQuality(0);
                        } else if (item.getSellIn() <= 5) {
                            qualityDifference = 3;
                        } else if (item.getSellIn() <= 10) {
                            qualityDifference = 2;
                        } else {
                            qualityDifference = 1;
                        }
                    }

                    item.setQuality(item.getQuality() + qualityDifference);
                    item.setSellIn(item.getSellIn() + sellinDifference);
                    if (item.getQuality() > 50) {
                        item.setQuality(50);
                    } else if (item.getQuality() < 0) {
                        item.setQuality(0);
                    }
                }
            }
        }
    }


    public void Delete(int selectedIdx)
    {
        items[selectedIdx] = null;

        Item[] newItemList = new Item[items.length - 1];

        int compteur = 0;
        for (int i =0; i<items.length; i++)
        {
            if (items[i] != null)
            {
                newItemList[compteur]=items[i];
                compteur=compteur+1;
            }
        }

        items = newItemList;

    }

    public void Add(String name, int selIn, int quality, String date) {
        Item[] newItems = new Item[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, items.length);
        newItems[items.length] = new Item(name, selIn, quality, date);
        items = newItems;
    }


    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
            inventory.printInventory();
        }
    }
}
