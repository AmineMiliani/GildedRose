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
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Legendary Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 1, 16),

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
        // TODO (PBZ) : this method is awful 
        for (Item item : items) {
            if (item != null) {
                if (!(item.getName().contains("Aged")
                        && item.getName().contains("concert"))) {
                    if (item.getQuality() > 0) {
                        if (!item.getName().contains("Legendary")) {
                            //items[i].setQuality(items[i].getQuality() - 1);

                            if (!item.getName().contains("Conjured")) {
                                item.setQuality(item.getQuality() - 2);
                            } else {
                                item.setQuality(item.getQuality() - 1);
                            }
                        }
                    }
                } else {
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);

                        if (!item.getName().contains("concert")) {
                            if (item.getSellIn() < 11) {
                                if (item.getQuality() < 50) {
                                    item.setQuality(item.getQuality() + 1);
                                }
                            }

                            if (item.getSellIn() < 6) {
                                if (item.getQuality() < 50) {
                                    item.setQuality(item.getQuality() + 1);
                                }
                            }
                        }
                    }
                }

                if (!item.getName().contains("Legendary")) {
                    item.setSellIn(item.getSellIn() - 1);
                }

                if (item.getSellIn() < 0) {
                    if (!item.getName().contains("Aged")) {
                        if (!item.getName().contains("concert")) {
                            if (item.getQuality() > 0) {
                                if (!item.getName().contains("Legendary")) {

                                    if (item.getName().contains("Conjured")) {
                                        item.setQuality(item.getQuality() - 2);
                                    } else {
                                        item.setQuality(item.getQuality() - 1);
                                    }
                                }
                            }
                        } else {
                            item.setQuality(0);
                        }
                    } else {
                        if (item.getQuality() < 50) {
                            item.setQuality(item.getQuality() + 1);
                        }
                    }
                }
            }
        }
    }


    public void Delete(int selectedIdx) {
        items[selectedIdx] = null;
    }

    public void Add(String name, int selIn, int quality) {
        Item[] newItems = new Item[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, items.length);
        newItems[items.length] = new Item(name, selIn, quality);
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
