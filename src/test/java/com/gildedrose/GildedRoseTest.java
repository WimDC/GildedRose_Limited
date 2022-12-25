package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20), // 0
            new Item("Aged Brie", 2, 0), // 1
            new Item("Elixir of the Mongoose", 5, 7), // 2
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), // 3
            new Item("Sulfuras, Hand of Ragnaros", -1, 80), // 4
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20), // 5
            new Item("Backstage passes to a TAFKAL80ETC concert", 3, 49), // 6
            new Item("Backstage passes to a TAFKAL80ETC concert", 7, 49), // 7
            new Item("Conjured Mana Cake", 3, 6), // 8
            new Item("Conjured Mana Cake", -1, 6), // 9
            new Item("Aged Brie", -2, 4), // 10
            new Item("Backstage passes to a TAFKAL80ETC concert", -5, 49), // 11
            new Item("Backstage passes to a TAFKAL80ETC concert", 3, 40), // 12
            new Item("Backstage passes to a TAFKAL80ETC concert", 7, 40)}; // 13


    @Test
        // SellIn value has to be decreased by 1 to all products except for Sulfuras
    void sellInChangeTest() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
        // SellIn value has to be decreased by 1 to all products except for Sulfuras
    void sellInChangeTestSulfuras() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
    }
    @Test
        // Aged Brie always increases in quality no matter the sellIn value
    void agedBrieQualityNegativeSellin() {
        Item[] items = new Item[] { new Item("Aged Brie", -2, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].quality);
    }
    @Test
        // Aged Brie always increases in quality no matter the sellIn value
    void agedBrieQualityPositiveSellin() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].quality);
    }
    @Test
        // Sulfuras never changes quality, no matter the sellIn value
    void sulfurasZeroSellin() {
        Item[] items = {new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }
    @Test
        // Sulfuras never changes quality, no matter the sellIn value
    void sulfurasNegativeSellin() {
        Item[] items = {new Item("Sulfuras, Hand of Ragnaros", -1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }
    @Test
        // Conjured items lose quality twice as fast as other products, also when sellIn is negative
    void conjuredNegativeSellin() {
        Item[] items = {new Item("Conjured Mana Cake", -1, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }
    @Test
        // Conjured items lose quality twice as fast as other products, also when sellIn is negative
    void conjuredPositiveSellin() {
        Item[] items = {new Item("Conjured Mana Cake", 3, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
    }
    @Test
        // Backstage Passes increase in value:
        // +1 when sellIn > 10, +2 when sellIn between =5 and =10, +3 when sellIn <= 5,
        // quality = 0 when sellIn is negative
        // quality for products cant exceed past 50
    void backstagePassesSellinFiveorLessMaxTest() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 3, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }
    @Test
        // Backstage Passes increase in value:
        // +1 when sellIn > 10, +2 when sellIn between =5 and =10, +3 when sellIn <= 5
        // quality = 0 when sellIn is negative
        // quality for products cant exceed past 50
    void backstagePassesSellinBetweenTenAndSixMaxTest() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 7, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }
    @Test
        // Backstage Passes increase in value:
        // +1 when sellIn > 10, +2 when sellIn between =5 and =10, +3 when sellIn <= 5
        // quality = 0 when sellIn is negative
        // quality for products cant exceed past 50
    void backstagePassesNegativeSellin() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", -5, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }
    @Test
        // Backstage Passes increase in value:
        // +1 when sellIn > 10, +2 when sellIn between =5 and =10, +3 when sellIn <= 5
        // quality = 0 when sellIn is negative
        // quality for products cant exceed past 50
    void backstagePassesSellinFiveorLess() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 3, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(43, app.items[0].quality);
    }
    @Test
        // Backstage Passes increase in value:
        // +1 when sellIn > 10, +2 when sellIn between =5 and =10, +3 when sellIn <= 5
        // quality = 0 when sellIn is negative
        // quality for products cant exceed past 50
    void backstagePassesSellinBetweenTenAndSix() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 7, 40)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(42, app.items[0].quality);
    }
}