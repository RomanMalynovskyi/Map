package com.gmail.malynovskyiroman.javaOOP.map.task4;

        /*Реализуйте программу которая сопоставит каждой букве ее
        представление в виде ASCII - art.
        Ваша программа должна дать возможность вывода произвольного
        текста на экран в виде его ASCII-art представления.*/

public class Main {

    public static void main(String[] args) {
        Converter converter = new Converter("z:\\ascii-art.txt");
        converter.printText("I would like to be Junior Java Developer.");
    }
}


