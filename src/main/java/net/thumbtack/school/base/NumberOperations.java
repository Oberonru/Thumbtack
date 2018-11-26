package net.thumbtack.school.base;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Arrays;

public class NumberOperations {

    //Ищет в массиве array первый элемент, значение которого равно value. Если такое значение найдено, возвращает его
    // позицию в массиве array, в противном случае возвращает null.
    public static Integer find(int[] array, int value) {
        Integer num = null;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                num = i;
                break;
            }
        }
        return num;
        //цикл вроде как сам по себе, как в методе выкинуть значение i, которое нашлось после пробежки по массиву?
    }

    //Ищет в массиве array первый элемент, значение которого по модулю не отличается от value более чем на eps.
    // Если такое значение найдено, возвращает его позицию в массиве array, в противном случае возвращает null.
    public static Integer find(double[] array, double value, double eps) {
        Integer position = null;
        for (int i = 0; i < array.length; i++) {
            if (i < array.length && Math.abs(array[i] - value) <= eps) {
                position = i;
                break;
            }
        }
        return position;
    }

    //Вычисляет плотность вещества по формуле weight / volume. Если получившееся значение не превышает max и не меньше
    // min, возвращает полученное значение, в противном случае возвращает null.
    public static Double calculateDensity(double weight, double volume, double min, double max) {
        double density = weight / volume;
        return density < max && density > min ? density : null;
    }

    //Ищет в массиве array первый элемент, значение которого равно value.
    // Если такое значение найдено, возвращает его позицию в массиве array, в противном случае возвращает null.
    public static Integer find(BigInteger[] array, BigInteger value) {
        Integer position = null;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                position = i;
                break;
            }
        }
        return position;
    }

    //Вычисляет плотность вещества по формуле weight / volume. Если получившееся значение не превышает max и не меньше
    // min, возвращает полученное значение, в противном случае возвращает null.
    public static BigDecimal calculateDensity(BigDecimal weight, BigDecimal volume, BigDecimal min, BigDecimal max) {
        BigDecimal density = weight.divide(volume);
        int result1 = density.compareTo(max);
        int result2 = density.compareTo(min);
        return result1 < 0 && result2 > 0 ? density : null;


    }


}
