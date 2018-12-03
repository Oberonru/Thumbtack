package net.thumbtack.school.base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringOperations {
    //Возвращает суммарную длину строк, заданных массивом strings.
    public static int getSummaryLength(String[] strings) {
        int summaryLength = 0;
        for (int i = 0; i < strings.length; i++) {
            summaryLength += strings[i].length();
        }
        return summaryLength;
    }

    //Возвращает двухсимвольную строку, состоящую из начального и конечного символов заданной строки.
    public static String getFirstAndLastLetterString(String string) {
        return string.substring(0, 1) + string.substring(string.length() - 1);
    }

    //Возвращает true, если обе строки в позиции index содержат один и тот же символ, иначе false.
    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        return string1.codePointAt(index) == string2.codePointAt(index);
    }

    //Возвращает true, если в обеих строках первый встреченный символ character находится в одной и той же позиции.
    // Просмотр строк ведется от начала.
    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        return string1.indexOf(character) == string2.indexOf(character);
    }

    //Возвращает true, если в обеих строках первый встреченный символ character находится в одной и той же позиции.
    // Просмотр строк ведется от конца.
    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
        return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }

    //Возвращает true, если в обеих строках первая встреченная подстрока str начинается в одной и той же позиции.
    // Просмотр строк ведется от начала.
    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        return string1.indexOf(str) == string2.indexOf(str);
    }

    //Возвращает true, если в обеих строках первая встреченная подстрока str начинается в одной и той же позиции.
    // Просмотр строк ведется от конца.
    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
        return string1.lastIndexOf(str) == string2.lastIndexOf(str);
    }

    //Возвращает true, если строки равны.
    public static boolean isEqual(String string1, String string2) {
        return string1.equals(string2);
    }

    //Возвращает true, если строки равны без учета регистра (например, строки “abc” и “aBC” в этом смысле равны).
    public static boolean isEqualIgnoreCase(String string1, String string2) {
        return string1.equalsIgnoreCase(string2);
    }

    // Возвращает true, если строка string1 меньше строки string2.
    public static boolean isLess(String string1, String string2) {
        int result = string1.compareTo(string2);
        return result < 0;
    }

    //Возвращает true, если строка string1 меньше строки string2 без учета регистра
    // (например, строка “abc” меньше строки “ABCd” в этом смысле).
    public static boolean isLessIgnoreCase(String string1, String string2) {
        int result = string1.compareToIgnoreCase(string2);
        return result < 0;
    }

    //Возвращает строку, полученную путем сцепления двух строк.
    public static String concat(String string1, String string2) {
        return string1.concat(string2);
    }

    //Возвращает true, если обе строки string1 и string2 начинаются с одной и той же подстроки prefix.
    public static boolean isSamePrefix(String string1, String string2, String prefix) {
        return string1.startsWith(prefix) && string2.startsWith(prefix);//string1.startsWith(prefix) == string2.startsWith(prefix) если поставить знаки ==, то тест падает, почему то так то???
    }

    //Возвращает true, если обе строки string1 и string2 заканчиваются одной и той же подстрокой suffix.
    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        return string1.endsWith(suffix) == string2.endsWith(suffix);// а здесь всё нормально
    }

    //Возвращает самое длинное общее “начало” двух строк. Если у строк нет общего начала, возвращает пустую строку.
    public static String getCommonPrefix(String string1, String string2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string1.length() && i < string2.length(); i++) {
            if (string1.charAt(i) == string2.charAt(i)) {
                sb.append(string1.charAt(i));
            } else break;
        }
        return sb.toString();
    }

    //Возвращает перевернутую строку.
    public static String reverse(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            sb.append(string.charAt(i));
        }
        return sb.toString();
    }

    //Возвращает true, если строка является палиндромом, то есть читается слева направо так же, как и справа налево.
    public static boolean isPalindrome(String string) {
        char[] charString = string.toCharArray();
        boolean isSorted = true;
        for (int i = 0; i < string.length() / 2; i++) {
            if (charString[i] != charString[charString.length - 1 - i]) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }


    //Возвращает true, если строка является палиндромом, то есть читается слева направо так же, как и справа налево,
    // без учета регистра.
    public static boolean isPalindromeIgnoreCase(String string) {
        String lowerCase = string.toLowerCase();
        char[] charString = lowerCase.toCharArray();
        boolean isSorted = true;
        for (int i = 0; i < string.length() / 2; i++) {
            if (charString[i] != charString[string.length() - 1 - i]) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    //Возвращает самый длинный палиндром (без учета регистра) из массива заданных строк.
    // Если в массиве нет палиндромов, возвращает пустую строку.
    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        String maxPalindrom = strings[0];
        for (int i = 0; i < strings.length; i++) {
            boolean isPalindrom = isPalindromeIgnoreCase(strings[i]);
            if (isPalindrom && strings[i].length() > maxPalindrom.length()) {
                maxPalindrom = strings[i];
            }
        }
        return maxPalindrom;
    }

    //Возвращает true, если обе строки содержат один и тот же фрагмент длиной length, начиная с позиции index.
    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        if (length + index <= string1.length() && length + index <= string2.length()) {
            String result1 = string1.substring(index, index + length);
            String result2 = string2.substring(index, index + length);
            return result1.equals(result2);
        } else
            return false;
    }

    //Возвращает true, если после замены в string1 всех вхождений replaceInStr1 на replaceByInStr1 и замены в
    // string2 всех вхождений replaceInStr2 на replaceByInStr2 полученные строки равны.
    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1,
                                                        String string2, char replaceInStr2, char replaceByInStr2) {
        String newString1 = string1.replace(replaceInStr1, replaceByInStr1);
        String newString2 = string2.replace(replaceInStr2, replaceByInStr2);
        return newString1.equalsIgnoreCase(newString2);
    }

    //Возвращает true, если после замены в string1 всех вхождений строки replceInStr1 на replaceByInStr1 и замены в
    // string2 всех вхождений replceInStr2 на replaceByInStr2 полученные строки равны.
    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1,
                                                     String string2, String replaceInStr2, String replaceByInStr2) {
        String newString1 = string1.replace(replaceInStr1, replaceByInStr1);
        String newString2 = string2.replace(replaceInStr2, replaceByInStr2);

        return newString1.equals(newString2);
    }

    //Возвращает true, если строка после выбрасывания из нее всех пробелов является палиндромом, без учета регистра.
    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        String notSpace = string.replace(" ", "");
        return isPalindromeIgnoreCase(notSpace);
    }

    //Возвращает true, если две строки равны, если не принимать во внимание все пробелы в начале и конце каждой строки.
    public static boolean isEqualAfterTrimming(String string1, String string2) {
        String newString1 = string1.trim();
        String newString2 = string2.trim();
        return newString1.equals(newString2);
    }

    //Для заданного массива целых чисел создает текстовую строку, в которой числа разделены знаком “запятая”
    // (т.н. формат CSV - comma separated values). Для пустого массива возвращается пустая строка.
    public static String makeCsvStringFromInts(int[] array) {
        StringBuilder sb = new StringBuilder();
        if (array.length == 0) {
            return "";
        }
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    //Для заданного массива вещественных чисел создает текстовую строку, в которой числа разделены знаком “запятая”,
    // причем каждое число записывается с двумя знаками после точки. Для пустого массива возвращается пустая строка.
    public static String makeCsvStringFromDoubles(double[] array) {
        if (array.length == 0) {
            return "";
        }

        return makeCsvStringBuilderFromDoubles(array).toString();
    }


    //То же, что и в упражнении 25, но возвращает StringBuilder.
    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
        StringBuilder sb = new StringBuilder("");
        if (array.length == 0) {
            return sb;
        }
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        return sb;
    }

    //То же, что и в упражнении 26, но возвращает StringBuilder.
    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append(String.format("%1$,.2f", array[i]));
            if (i < array.length - 1) {
                result.append(",");
            }
        }
        return result;
    }

    //Удаляет из строки символы, номера которых заданы в массиве positions. Предполагается, что будут передаваться
    // только допустимые номера, упорядоченные по возрастанию. Номера позиций для удаления указаны для исходной строки.
    // Возвращает полученный в результате StringBuilder.
    public static StringBuilder removeCharacters(String string, int[] positions) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            boolean exists = false;
            for (int j = 0; j < positions.length; j++) {
                if (positions[j] == i) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                result.append(string.charAt(i));
            }
        }
        return result;
    }

    //Вставляет в строку символы. Массивы positions и characters имеют одинаковую длину. В позицию positions[i] в
    // исходной строке string вставляется символ characters[i]. Если в массиве positions один и тот же номер позиции
    // повторяется несколько раз, это значит, что в указанную позицию вставляется несколько символов, в том порядке,
    // в котором они перечислены в массиве characters. Предполагается, что будут передаваться только допустимые номера,
    // упорядоченные по неубыванию.  Возвращает полученный в результате StringBuilder.
    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j < positions.length; j++) {
                if (positions[j] == i) {
                    result.append(characters[j]);
                }
            }
            result.append(string.charAt(i));
        }
        return result;
    }
}
