package com.gmail.malynovskyiroman.javaOOP.map.task1;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class EngUkrDictionary {

    private Map<String, String> map;
    private String text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void translateText(String src, String dest) {
        File source = new File(src);
        File destination = new File(dest);
        if (!source.exists() || !destination.exists()) {
            throw new IllegalArgumentException("Please, enter correct file names!");
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
             PrintWriter printWriter = new PrintWriter(dest)) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                setText(s);
                String[] array = s.split("[\\s\\p{P}]");
                for (int i = 0; i < array.length; i++) {
                    if (map.containsKey(array[i])) {
                        setText(text.replace(array[i], map.get(array[i])));
                    }
                }
                printWriter.write(text);
                printWriter.println();
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
