package com.ltp.globalsuperstore.repository;

import com.ltp.globalsuperstore.Item;

import java.util.ArrayList;
import java.util.List;

public class StoreRepository {

    private List<Item> items = new ArrayList<>();

    public Item getItem(int index) {
        return items.get(index);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void updateItem(int index, Item item) {
        items.set(index, item);
    }

    public List<Item> getItems() {
        return items;
    }
}
