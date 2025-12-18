package arac;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;

public class DataManager {
    private final String DATA_FILE = "araclar.txt";

    public HashMap<String, Arac> loadData() {
        HashMap<String, Arac> dataMap = new HashMap<>();
        Path path = Paths.get(DATA_FILE);
        if (Files.exists(path)) {
            try (BufferedReader br = Files.newBufferedReader(path)) {
                String line;
                while ((line = br.readLine()) != null) {
                    Arac arac = Arac.fromString(line);
                    if (arac != null) {
                        dataMap.put(arac.getId(), arac);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Dosya yoksa oluştur
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataMap;
    }

    public boolean saveData(HashMap<String, Arac> dataMap) {
        Path path = Paths.get(DATA_FILE);
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Arac arac : dataMap.values()) {
                bw.write(arac.toString());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
