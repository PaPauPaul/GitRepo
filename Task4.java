package School.Olympiada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Павел on 18.10.2015.
 * В первой строке входного файла input.txt содержится число N
 * (1 <= N <= 30) - количество чисел в левой части равенства, записанного на доске и число S,
 * записанное в правой части равенства (1 <= S <= 10^6). В следующей строке даны N чисел в том порядке, в каком они были выписаны на доске.
 * Все числа неотрицательные и не превышают 10^6.
 * Формат выходных данных
 * Выведете на экран одно число — количество различных вариантов расстановки знаков между числами, приводящих к правильному результату в записанном на доске выражении.
 */
public class Task4 {

    public static void main(String[] args) {
        //    Scanner in = new Scanner(System.in);
        String leftPart = "222222";
        int n = leftPart.length()-1;
        String rightPart = "12";
        System.out.println(Arrays.toString(getBinaryArray(n)));
        System.out.println();
        System.out.println(Arrays.toString(formingFinalArray(leftPart, getBinaryArray(n))));
        System.out.println();
        int bLength = getBinaryArray(n).length;
//        System.out.println(getBinaryArray(n));
        int[][] array;
        array = resultArray(formingFinalArray(leftPart, getBinaryArray(n)));
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                System.out.print(array[i][j]);
//            }
//            System.out.println();
//        }

        System.out.println(getCount(array, rightPart));

    }

    //метод преобразования числа из 10-ой СС в 2-ую СС
    public static String binaryConverter(int a) {
        String result = "";
        if (a == 0) {
            return "0";
        }
        while (a != 1) {
            if (a % 2 == 0) {
                a = a / 2;
                result = "0" + result;
            } else {
                a = (a - 1) / 2;
                result = "1" + result;
            }
        }
        result = "1" + result;
        return result;
    }

    //метод формирования массива из 2^n строковых элементов, обозначающих
    //двоичное представление чисел с эквивалентом от 0 до (2^n)-1,
    //где n - количество арифметических операций для данного количества элементов л.ч. уравнения
    public static StringBuilder[] getBinaryArray(int n) {
        StringBuilder[] binaryRow = new StringBuilder[(int) Math.pow(2, n)];

        for (int i = 0; i < binaryRow.length; i++) {
            StringBuilder s = new StringBuilder(n);
            s = s.append(binaryConverter(i));
            s = s.reverse();
            if (s.length() < (n + 1)) {
                int stLen = s.length();
                for (int j = 0; j < (n - stLen); j++) {
                    s = s.append("0");
                }
            }
            binaryRow[i] = s.reverse();
        }
        return binaryRow;
    }

    /* метод для объединения двух строк:
    * первая строка - входные даннык - числа, которые стоят в л.ч. уравнения,
    * а другой массив - бинарные представления количества различных вариаций арифметических операций между ними.
    * Теоретически, аменяем умножение на "1", а сложение на "0"*/
    public static StringBuilder[] formingFinalArray(String s, StringBuilder[] binaryRow) {
        StringBuilder[] combArray = new StringBuilder[binaryRow.length];
        for (int i = 0; i < binaryRow.length; i++) {
            StringBuilder resArrayElem = new StringBuilder(binaryRow[i].length() + s.length() - 1);
            for (int j = 0; j < s.length() - 1; j++) {
                StringBuilder intermElem = new StringBuilder(2);
                intermElem = intermElem.append(binaryRow[i].charAt(j));
                intermElem = intermElem.append(s.charAt(j)).reverse();
                resArrayElem = resArrayElem.append(intermElem);
            }
            resArrayElem = resArrayElem.append(s.charAt(s.length() - 1));
            combArray[i] = resArrayElem;
        }
        return combArray;
    }

    //метод, преобразующий массив строк в двумерный массив интов
    public static int[][] resultArray(StringBuilder[] combArray) {
        int[][] resultArray = new int[combArray.length][combArray[0].length()];

        for (int i = 0; i < combArray.length; i++) {
            for (int j = 0; j < combArray[i].length(); j++) {
                resultArray[i][j] = Integer.parseInt("" + combArray[i].charAt(j));
            }
        }
        return resultArray;
    }

    public static int getCount(int[][] resultArray, String rightPart) {

        int count = 0;
        for (int i = 0; i < resultArray.length; i++) {
            ArrayList<Integer> finalList = new ArrayList<>();
            for (int j = 1; j < resultArray[i].length - 1; j = j + 2) {
                if (resultArray[i][j] == 0 && j == 1) {
                    finalList.add(resultArray[i][j - 1]);
                } else if (resultArray[i][j] == 0 && j == resultArray[i].length - 2) {
                    finalList.add(resultArray[i][j + 1]);
                } else if (resultArray[i][j] == 1) {
                    int pow = 1; //переменная, которое хранит произведение чисел
                    boolean isFirst = true;
                    int c = 0;
                    while (resultArray[i][j] == 1) { //TODO проверить, как работает итерация для внешнего цикла в цикле WHILE, находящимся внутри FOR
                        if (isFirst) { // если первый раз встречаем умножение, то умножаем число перед знаком на число после знака
                            pow = resultArray[i][j - 1] * resultArray[i][j + 1];
                            isFirst = false;
                        } else { // если нет, то умножаем уже имеющеесе произведение на число после знака
                            pow = pow * resultArray[i][j + 1];
                        }
//                        c++;
                        if (j != resultArray[i].length-2) {
                            j = j + 2;
                        } else {
                            j = j + 2;
                            break;
                        }
                    }
//                    if (c > 1) { //если количество подряд идущих единиц больше, чем 1
                        j = j - 2; //сделано для того, чтобы не проскочить элемент при слкдующей итерации цикла
//                    }
                    finalList.add(pow); //записываем в массив результат произведения
                } else if (resultArray[i][j - 2] == 0 && resultArray[i][j] == 0 && j == 3 && (j + 2) == 1) { // частный случай, когда второй по счету знак - "+", а следующий - "*"
                    finalList.add(resultArray[i][j - 1]);
                } else if (resultArray[i][j] == 0 && resultArray[i][j - 2] == 1 && resultArray[i][j + 2] == 0 && (j + 2) == resultArray.length - 2) {
                    finalList.add(resultArray[i][j + 1]);
                } else if (resultArray[i][j] == 0 && resultArray[i][j - 2] == 0 && resultArray[i][j + 2] == 1) {
                    finalList.add(resultArray[i][j - 1]);
                } else if (resultArray[i][j - 2] == 0 && resultArray[i][j] == 0 && resultArray[i][j + 2] == 0 && (j + 2) == resultArray[i].length - 2) {
                    finalList.add(resultArray[i][j - 1]);
                    finalList.add(resultArray[i][j + 1]);
                } else if (resultArray[i][j] == 0 && resultArray[i][j - 2] == 0 && resultArray[i][j + 2] == 0) {
                    finalList.add(resultArray[i][j - 1]);
                } else if (resultArray[i][j] == 0 && resultArray[i][j-2] == 1 && resultArray[i][j+2] == 0 && (j+2) == resultArray[i].length-2) {
                    finalList.add(resultArray[i][j+1]);
                }
            }
            int sum = 0;
            for (int j = 0; j < finalList.size(); j++) {
                sum += finalList.get(j);
            }

            if (sum == Integer.parseInt(rightPart)) {
                count++;
//                for (int j = 0; j < resultArray[i][j]; j++) {
//                    System.out.print("["+resultArray[i][j]+"]");
//                }
//                System.out.println();
            }
        }
        return count;
    }

}
