package com.gmail.malynovskyiroman.javaOOP.map.task1_task2;

     /* 1.Написать программу переводчик, которая будет переводить текст
        в файле English.in,написанный на английском языке, на украинский
        язык согласно заранее составленному словарю. Результат
        сохранить в файл Ukrainian.out.
        2.Сделать ф-ю ручного наполнения словаря и возможность его
        сохранения на диск. */

public class Main {

    public static void main(String[] args) {

        // EngUkrDictionary engUkrDictionary = new EngUkrDictionary("z:\\srcDict.txt");
        EngUkrDictionary engUkrDictionary = new EngUkrDictionary();

        EngUkrDictionary dictionaryFromFile = engUkrDictionary.getFromFile("z:\\src.txt");

        FillingDictionary fillingDictionary = new FillingDictionary(dictionaryFromFile,
                "z:\\src.txt");

        System.out.println(dictionaryFromFile);

        // dictionaryFromFile.translateText("z:\\English.in.txt", "z:\\Ukrainian.out.txt");

        // engUkrDictionary.translateText("z:\\English.in.txt", "z:\\Ukrainian.out.txt");

    }
}
