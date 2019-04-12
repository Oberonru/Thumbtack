package net.thumbtack.school.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixNonSimilarRows {
    /**
     * Дана целочисленная матрица, в которой имеется N строк, а число элементов в строке для каждой строки может быть
     * любым, в том числе нулевым. Строки назовем похожими, если совпадают множества чисел, встречающихся в этих строках.
     * Найти список строк этой матрицы максимальной размерности, в котором все строки попарно непохожи друг на друга.
     * Из похожих строк в список включить строку с наибольшим номером. Порядок элементов в списке произвольный.
     *
     * Пример. Матрица содержит 3 строки:
     *
     *                          1 2 2 4 4
     * 							4 2 1 4
     * 							3 2 4 1 5 8
     *
     * Первые 2 строки похожи друг на друга и непохожи на 3 строку. Ответом будет список из 2 и 3 строк.
     */

    private int[][] matrix;

    //Создает MatrixNonSimilarRows по заданной матрице.
    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    //Возвращает список непохожих строк.
    public List<int[]> getNonSimilarRows() {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            int[] row1 = matrix[i];
            int[] lastRow = row1;
            for (int j = i + 1; j < matrix.length; j++) {
                int[] row2 = matrix[j];
                if(isSimilar(row1, row2)) {
                    lastRow = row2;
                }
            }
            if (!isContains(result, lastRow)) {
                result.add(lastRow);
            }
        }
        return result;
    }

    private static boolean isSimilar(int[] row1, int[] row2) {
        if (Arrays.equals(row1, row2)) {
            return true;
        }

        if (row2.length > row1.length) {
            int[] buffer = row1;
            row1 = row2;
            row2 = buffer;
        }

        boolean result = false;
        for (int i = 0; i < row1.length; i++) {
            boolean isExists = false;
            for (int j = 0; j < row2.length; j++) {
                if (row1[i] == row2[j]) {
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                return false;
            }
            result = true;
        }
        return result;
    }

    private boolean isContains(List<int[]> matrix, int[] row) {
        for(int[] matrixRow : matrix) {
            if (Arrays.equals(matrixRow, row)) {
                return true;
            }
        }
        return false;
    }
}
