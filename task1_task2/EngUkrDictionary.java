package com.gmail.malynovskyiroman.javaOOP.map.task1_task2;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class EngUkrDictionary implements Serializable {

    private Map<String, String> map;
    private String pathToDictionary;
    private static final long serialVersionUID = 1L;

    public EngUkrDictionary(String pathToDictionary) {
        this.map = new TreeMap<>();
        this.pathToDictionary = pathToDictionary;
        loadWordsFromFile();
    }

    public EngUkrDictionary() {
        this.map = new TreeMap<>();
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void translateText(String src, String dest) throws IOException {
        if (this.getMap().isEmpty()) {
            System.out.println("Dictionary doesn`t contains words!!!");
            return;
        }
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
                        stringBuilder.replace(stringBuilder.indexOf(array[i]),
                                stringBuilder.indexOf(array[i]) + array[i].length(), map.get(array[i]));
                    }
                }
                printWriter.write(stringBuilder.toString());
                printWriter.println();
                stringBuilder.setLength(0);
            }
        }
    }

    private void loadWordsFromFile() {
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

    public void saveToFile(String filepath) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filepath))) {
            objectOutputStream.writeObject(this);
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Please, specify correct path to file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EngUkrDictionary getFromFile(String filepath) {
        EngUkrDictionary engUkrDictionary = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filepath))) {
            engUkrDictionary = (EngUkrDictionary) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Please, specify correct path to file!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return engUkrDictionary;
    }

    @Override
    public String toString() {
        return "English-Ukrainian Dictionary" + map;
    }
}
