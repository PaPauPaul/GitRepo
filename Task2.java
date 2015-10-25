package School.Olympiada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Павел on 18.10.2015.
 * Написать программу, которая будет находить, на сколько квадратов, стороны
 которых выражены натуральными числами, можно разрезать данный
 прямоугольник, если от него каждый раз отрезается квадрат максимально
 большой площади.
 Формат входных данных (input.txt):
 Входной файл состоит из одной строки, содержащей два
 натуральных числа – стороны прямоугольника, разделенные
 пробелом.
 Формат выходных данных (output.txt):
 Выходной файл содержит одно число – количество квадратов.
 Пример:
 input.txt:
 6 4
 output.txt:
 3
 */
public class Task2 {
    public static void main(String[] args) {
        boolean isCut = true; //флаг остановк поиска
        int count = 0; //количество квадратов
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        Scanner in = new Scanner(System.in);
        System.out.println("Введите первую сторону прямоугольника: ");
        int a = in.nextInt();
        System.out.println("Введите вторую сторону прямоугольника: ");
        int b = in.nextInt();

        //основной цикл нахождения квадратов и их оличества
        while (isCut) {
            //если стороны равны, то мы имеем квадрат, инерементируем количество и выходим
            if (a == b) {
                count++;
                arrayList.add(a);
                isCut = false;
                continue;
            }
            //если сторона a меньше стороны b, переворачиваем прямоугольник
            if (a < b) {
                a = a + b;
                b = a - b;
                a = a - b;
            }
            //ищем максимальный квадрат в прямоугольнике
            a = a - b;
            count++;
            arrayList.add(b);
        }
        System.out.println(count);
        for (int i = 0; i < arrayList.size(); i++) {
            if ( i == arrayList.size() - 1) {
                System.out.print(arrayList.get(i));
            } else {
                System.out.print(arrayList.get(i) + "; ");
            }
        }
    }
}