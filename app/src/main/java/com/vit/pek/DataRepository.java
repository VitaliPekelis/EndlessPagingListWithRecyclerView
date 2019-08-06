package com.vit.pek;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {


    public List<DataItem> getData(int size) {
        List<DataItem> items = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            items.add(new DataItem());
        }

        return items;
    }
}
