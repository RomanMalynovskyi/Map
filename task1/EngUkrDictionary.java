package com.gmail.malynovskyiroman.javaOOP.map.task1;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class EngUkrDictionary {

    private Map<String, String> map;
    private String pathToDictionary;

    public EngUkrDictionary(String pathToDictionary) {
        this.map = new TreeMap<>();
        this.pathToDictionary = pathToDictionary;
        loadDataFromFile();
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void translateText(String src, String dest) {
        File source = new File(src);
        File destination = new File(dest);
        if (!source.exists() || !destination.exists()) {
            throw new IllegalArgumentException("Please, enter correct file names!");
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
             PrintWriter printWriter = new PrintWriter(dest)) {
            StringBuilder stringBuilder = new StringBuilder();
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s);
                String[] array = s.split("[\\pP\\s]");
                for (int i = 0; i < array.length; i++) {
                    if (map.containsKey(array[i])) {
                        stringBuilder.replace(stringBuilder.indexOf(array[i]), (stringBuilder.indexOf(array[i]) + array[i].length()), map.get(array[i]));
                    }
                }
                printWriter.write(stringBuilder.toString());
                printWriter.println();
                stringBuilder.setLength(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToDictionary))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                map.put(s.split("-")[0], s.split("-")[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "English-Ukrainian Dictionary" + map;
    }
}
