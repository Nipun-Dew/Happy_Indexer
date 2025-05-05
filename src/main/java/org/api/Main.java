/*
package org.api;

import com.opencsv.bean.CsvToBeanBuilder;
import org.api.model.HappyIndexEntry;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<HappyIndexEntry> dataList = new CsvToBeanBuilder<HappyIndexEntry>(new FileReader("2019.csv"))
                .withType(HappyIndexEntry.class).build().parse();

        for (HappyIndexEntry entry : dataList) {
            System.out.println(entry.getCountry() + ": " + entry.getRank());
        }
    }
}
*/
