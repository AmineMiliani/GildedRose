package edu.insightr.gildedrose;

public class Inventory {

    private Item[] items ;


    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();

        items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Legendary Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 1, 16),

        };






    }
    public Item[] getItems()
    {
        return items;
    }

    public void setItems(Item[] items )
    {
        this.items=items;
    }



    public void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if(items[i] != null) {
                if (!(items[i].getName().contains("Aged")
                        && items[i].getName().contains("concert"))) {
                    if (items[i].getQuality() > 0) {
                        if (!items[i].getName().contains("Legendary")) {
                            //items[i].setQuality(items[i].getQuality() - 1);

                            if (!items[i].getName().contains("Conjured")) {
                                items[i].setQuality(items[i].getQuality() - 2);
                            } else {
                                items[i].setQuality(items[i].getQuality() - 1);
                            }
                        }
                    }
                } else {
                    if (items[i].getQuality() < 50) {
                        items[i].setQuality(items[i].getQuality() + 1);

                        if (!items[i].getName().contains("concert")) {
                            if (items[i].getSellIn() < 11) {
                                if (items[i].getQuality() < 50) {
                                    items[i].setQuality(items[i].getQuality() + 1);
                                }
                            }

                            if (items[i].getSellIn() < 6) {
                                if (items[i].getQuality() < 50) {
                                    items[i].setQuality(items[i].getQuality() + 1);
                                }
                            }
                        }
                    }
                }

                if (!items[i].getName().contains("Legendary")) {
                    items[i].setSellIn(items[i].getSellIn() - 1);
                }

                if (items[i].getSellIn() < 0) {
                    if (!items[i].getName().contains("Aged")) {
                        if (!items[i].getName().contains("concert")) {
                            if (items[i].getQuality() > 0) {
                                if (!items[i].getName().contains("Legendary")) {

                                    if (items[i].getName().contains("Conjured")) {
                                        items[i].setQuality(items[i].getQuality() - 2);
                                    } else {
                                        items[i].setQuality(items[i].getQuality() - 1);
                                    }
                                }
                            }
                        } else {
                            items[i].setQuality(items[i].getQuality() - items[i].getQuality());
                        }
                    } else {
                        if (items[i].getQuality() < 50) {
                            items[i].setQuality(items[i].getQuality() + 1);
                        }
                    }
                }
            }
        }
    }


    public void Delete (int selectedIdx)
    {
       items[selectedIdx]= null;
    }

    public void Add (String name,int selIn,int quality)
    {
        Item[] newitems = new Item[items.length +1];
        for(int i = 0; i < items.length; i++){
            newitems[i] = items[i];
        }
        newitems[items.length]= new Item (name,selIn,quality);
        items = newitems;
    }


    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
            inventory.printInventory();
        }
    }
}
