package com.gmail.malynovskyiroman.javaOOP.map.task1;

        /*Написать программу переводчик, которая будет переводить текст
        в файле English.in,написанный на английском языке, на украинский
        язык согласно заранее составленному словарю. Результат
        сохранить в файл Ukrainian.out.*/


public class Main {

    public static void main(String[] args) {

        EngUkrDictionary engUkrDictionary = new EngUkrDictionary("z:\\srcDict.txt");

        System.out.println(engUkrDictionary);

        engUkrDictionary.translateText("z:\\English.in.txt","z:\\Ukrainian.out.txt");
    }
}
