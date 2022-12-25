package com.gildedrose;

class GildedRose {
    Item[] items;

    /**
     * @param items
     */
    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        // we iterate through our items indexes with a for loop going from 0 to the length of our itemlist
        for (int i = 0; i < items.length; i++) {
            // if itemname is Aged Brie and/or Backstage passes, then go to ELSE
            // if itemname is NOT aged brie AND NOT backstage passes, then go to next IF
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                //The Quality of an item is never negative
                if (items[i].quality > 0) {
                    //Sulfuras can not lose quality (so never -1 in quality)
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        //decrease quality of all items BESIDE Aged Brie, Backstage Passes and Sulfuras by 1
                        items[i].quality = items[i].quality - 1;
                        if (items[i].quality > 0 && items[i].name.contains("Conjured")) {
                            items[i].quality = items[i].quality - 1;
                        }
                    }

                }
                //talking about items which value increase each day (Backstage Passes and Aged Brie)
            } else {
                //The Quality of an item is never more than 50
                //increases the quality of Aged Brie and Backstage Passes by 1
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                    //"Backstage passes" increases in Quality as its SellIn value approaches;
                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        // if sellIn is 10 days or less and if quality is less than 50 , then increase quality by 1
                        // at this point quality will be increased by 2
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                        // if sellIn is 5 days or less and if quality is less than 50 , then increase quality by 1
                        // at this point quality will be increased by 3
                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }
            // SellIn value has to be decreased by 1 to all product except for Sulfuras
            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }
            // If the item is overdue, then lower its quality again by 1 (doubling its decrease)
            if (items[i].sellIn < 0) {
                // if name = Aged Brie, the go to ELSE
                if (!items[i].name.equals("Aged Brie")) {
                    //if name = backstage passes , then go to ELSE
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            //Sulfuras can not lose quality (so never -1 in quality)
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                //all items, beside Aged Brie, Backstage Passes and Sulfuras lower 1 in quality
                                items[i].quality = items[i].quality - 1;
                                if (items[i].quality > 0 && items[i].name.contains("Conjured")) {
                                    items[i].quality = items[i].quality - 1;
                                }
                            }
                        }
                    } else {
                        //Quality drops to 0 after the concert - only for tickets
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                }
            }
        }
    }
}