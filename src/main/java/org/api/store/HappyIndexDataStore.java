package org.api.store;

import org.api.model.HappyIndexEntry;

import java.util.ArrayList;
import java.util.List;

public class HappyIndexDataStore {
    private static List<HappyIndexEntry> data = new ArrayList<HappyIndexEntry>();

    private HappyIndexDataStore() {}

    public static void initialize(List<HappyIndexEntry> input) {
        data = input;
    }

    public static List<HappyIndexEntry> getData() {
        return data;
    }
}
