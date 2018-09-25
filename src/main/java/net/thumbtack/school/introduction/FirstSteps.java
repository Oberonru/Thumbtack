package net.thumbtack.school.introduction;

import java.util.*;

public class FirstSteps {

    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    //Возвращает частное от деления чисел x и y. Гарантируется, что y != 0.
    public int div(int x, int y) {
        return x / y;
    }

    //Возвращает остаток от деления чисел x и y. Гарантируется, что y != 0.
    public int mod(int x, int y) {
        return x % y;
    }

    //Возвращает true, если  x равен y, иначе false.
    public boolean isEqual(int x, int y) {
        return x == y;
    }

    //Возвращает true, если  x больше y, иначе false.
    public boolean isGreater(int x, int y) {
        return x > y;
    }

    /*
    Прямоугольник с горизонтальными и вертикальными сторонами, задан двумя точками - левой верхней (xLeft, yTop)
     и правой нижней (xRight, yBottom). На плоскости OXY ось X направлена вправо, ось Y - вниз. Дана еще одна точка
      с координатами (x, y). Гарантируется, что xLeft < xRight и yTop < yBottom. Метод должен возвращать true, если
       точка лежит внутри прямоугольника , иначе false. Если точка лежит на границе прямоугольника, то считается,
       что она лежит внутри него.
    */
    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {

        return x >= xLeft && x <= xRight && y <= yBottom && y >= yTop;
    }

    //Возвращает сумму чисел, заданных одномерным массивом array. Для пустого одномерного массива возвращает 0.
    public int sum(int[] array) {
        return Arrays.stream(array).sum();
    }

    //Возвращает произведение чисел, заданных одномерным массивом array. Для пустого одномерного массива возвращает 0.
    public int mul(int[] array) {
        if (array.length != 0) {
            int multiplay = 1;
            for (int i = 0; i < array.length; i++) {
                multiplay *= array[i];
            }
            return multiplay;
        } else return 0;
    }

    // Возвращает минимальное из чисел, заданных одномерным массивом array. Для пустого одномерного массива возвращает Integer.MAX_VALUE.
    public int min(int[] array) {
        return Arrays.stream(array).min().orElse(Integer.MAX_VALUE);
    }

    //Возвращает максимальное из чисел, заданных одномерным массивом array. Для пустого одномерного массива возвращает Integer.MIN_VALUE.
    public int max(int[] array) {
        return Arrays.stream(array).max().orElse(Integer.MIN_VALUE);
    }

    //Возвращает среднее значение для чисел, заданных одномерным массивом array. Для пустого одномерного массива возвращает 0.
    public double average(int[] array) {

        return Arrays.stream(array).average().orElse(0);
    }

    //Возвращает true, если одномерный массив array строго упорядочен по убыванию, иначе false. Пустой одномерный массив считается упорядоченным.
    public boolean isSortedDescendant(int[] array){
        boolean isSorted = true;
        for (int i = 0; i < array.length; i++) {
            if(i < array.length - 1 &&  array[i] <= array[i + 1]){
                isSorted = false;
                break;
            }
        }
           return isSorted;
    }
    //Возводит все элементы одномерного массива array в куб.
    public void cube(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.pow(array[i], 3);
        }
    }

    //Возвращает true, если в одномерном массиве array имеется элемент, равный value, иначе false.
    public boolean find(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    //Переворачивает одномерный массив array, то есть меняет местами 0-й и последний, 1-й и предпоследний и т.д. элементы.
    public void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int num = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = num;
        }
    }
    public boolean isPalindrome(int[]array){
        boolean result = true;
        for (int i = 0; i < array.length/2 ; i++) {

            if(array[i] != array[array.length - 1 - i]){
                result = false;
                break;
            }
        }
        return result;
}
    //Возвращает сумму чисел, заданных двумерным массивом matrix.
    public int sum(int[][] matrix){
            int summ=0;
            for (int i=0;i<matrix.length;i++) {
                for (int j = 0; j < matrix.length; j++) {
                    summ += matrix[i][j];
                }
            }
            return summ;
        }

    //Возвращает максимальное из чисел, заданных двумерным массивом matrix.
    // Для пустого двумерного массива возвращает Integer.MIN_VALUE.
    public int max(int[][] matrix){
        int result = Integer.MIN_VALUE;

        for (int[] i : matrix) {
            for (int j : i)
                result = Math.max(result, j);
        }
        return result;
    }
    //Возвращает максимальное из чисел, находящихся на главной диагонали квадратного
    // двумерного массива matrix. Для пустого двумерного массива возвращает Integer.MIN_VALUE.
    public int diagonalMax(int[][] matrix){
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
               if(matrix[i][j] > max && i == j){
                   max = matrix[i][j];
               }
                if(matrix.length == 0){
                    max = Integer.MAX_VALUE;
                }
            }
        }
        return  max;
    }
    //Возвращает true, если все строки двумерного массива matrix строго упорядочены по убыванию, иначе false. Пустая строка считается упорядоченной.
    // Разные строки массива matrix могут иметь разное количество элементов. При написании метода рекомендуется внутри него вызвать метод из п. 13.
    public boolean isSortedDescendant(int[][] matrix){
        boolean result = true;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(j < matrix[j].length - 1 && matrix[i][j] <= matrix[i][j + 1]){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}




