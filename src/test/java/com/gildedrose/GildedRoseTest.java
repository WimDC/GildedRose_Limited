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


    GildedRose app = new GildedRose(items);

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    @Test
    void agedBrieQualityNegativeSellin() {
        app.updateQuality();
        assertEquals(5, app.items[10].quality);
    }
    @Test
    void sulfurasZeroSellin() {
        app.updateQuality();
        assertEquals(80, app.items[3].quality);
    }
    @Test
    void sulfurasNegativeSellin() {
        app.updateQuality();
        assertEquals(80, app.items[4].quality);
    }
    @Test
    void conjuredNegativeSellin() {
        app.updateQuality();
        assertEquals(2, app.items[9].quality);
    }
    @Test
    void conjuredPositiveSellin() {
        app.updateQuality();
        assertEquals(4, app.items[8].quality);
    }

    @Test
    void backstageOfPassesSellinFiveorLessMaxTest() {
        app.updateQuality();
        assertEquals(50, app.items[6].quality);
    }

    @Test
    void backstageOfPassesSellinBetweenTenAndSixMaxTest() {
        app.updateQuality();
        assertEquals(50, app.items[7].quality);
    }

    @Test
    void backstageOfPassesNegativeSellin() {
        app.updateQuality();
        assertEquals(0, app.items[11].quality);
    }
    @Test
    void backstageOfPassesSellinFiveorLess() {
        app.updateQuality();
        assertEquals(43, app.items[12].quality);
    }

    @Test
    void backstageOfPassesSellinBetweenTenAndSix() {
        app.updateQuality();
        assertEquals(42, app.items[13].quality);
    }
}