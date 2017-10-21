package com.gmail.malynovskyiroman.javaOOP.map.task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Converter {
    private Map<String, String> map;
    private String src;
    public static final int LINES = 15;

    public Converter(String src) {
        this.src = src;
        map = new HashMap<>();
    }

    private void fillMap() {
        try {
            map = Stream.of(Files.lines(Paths.get(src))
                    .reduce("", (a, b) -> a + System.lineSeparator() + b))
                    .flatMap(s -> Arrays.stream(s.split("-")))
                    .collect(Collectors.toMap(
                            s -> s.substring(0, s.indexOf(System.lineSeparator())),
                            s -> s.substring(1, s.lastIndexOf('\n'))
                    ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printText(String string) {
        fillMap();
        StringBuilder stringBuilder = new StringBuilder();
        char[] array = string.toUpperCase().toCharArray();
        for (int i = 0; i < LINES; i++) {
            stringBuilder.setLength(0);
            for (int j = 0; j < array.length; j++) {
                if (map.containsKey(String.valueOf(array[j]))) {
                    stringBuilder.append(map.get(String.valueOf(array[j])).split(System.lineSeparator())[i]);
                } else {
                    stringBuilder.append("                  ");
                }
            }
            System.out.println(stringBuilder.toString());
        }
    }
}
