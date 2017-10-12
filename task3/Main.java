package com.gmail.malynovskyiroman.javaOOP.map.task3;

        /* Решить задачу подсчета повторяющихся элементов в массиве с
           помощью HashMap.*/

import java.util.HashMap;

public class Main<T> {

    public static void main(String[] args) {
        Main<Integer> main = new Main<>();

        main.calculateFrequency(new Integer[]{30, 1, 4, 6, 7, 9, 4, 5, 6, 70, 80, 9, 1, 2, 3, 4, 5, 6, 7, 9, 10, 2, 1, 2});
    }


    public void calculateFrequency(T[] array) {
        HashMap<T, Integer> map = new HashMap<>(50, 0.9f);
        for (T elem : array) {
            map.computeIfPresent(elem, (key, value) -> value + 1);
            map.putIfAbsent(elem, 1);
        }

        long result = map.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .mapToInt(entry -> entry.getValue())
                .sum();

        map.forEach((key, value) -> System.out.println("Number: " + key + " Frequency: " + value));
        System.out.println();
        System.out.println("Number of repeating element: " + result);
    }
}
