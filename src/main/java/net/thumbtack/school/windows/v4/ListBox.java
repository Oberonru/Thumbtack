package net.thumbtack.school.windows.v4;

import net.thumbtack.school.base.StringOperations;
import net.thumbtack.school.windows.v4.base.*;
import net.thumbtack.school.windows.v4.iface.Movable;
import net.thumbtack.school.windows.v4.iface.Resizable;

import javax.xml.ws.WebServiceException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListBox extends RectWindow implements Movable, Resizable {
    /**
     * Прямоугольное окно,  содержащее в себе список строк. Для ListBox определено 2 состояния - активен (изображается
     * черным цветом) и пассивен (серым цветом). Предполагается, что всегда будут передаваться допустимые координаты, то
     * есть при создании или изменении всегда будет выполняться : левая точка не правее правой, верхняя точка не ниже
     * нижней.
     * Обращаем Ваше внимание,  что ListBox должен иметь свою собственную копию массива, а не хранить в себе переданный
     * массив, поскольку переданный массив может впоследствии быть изменен за пределами класса ListBox. Для создания копии
     * массива можно воспользоваться методом System.arraycopy, который копирует массив. Сами строки при этом не копируются,
     * так как необходимости в этом нет - строки иммутабельные. Новый массив после System.arraycopy будет содержать ссылки
     * на те же строки.
     */

    private String[] lines;


    //Создает ListBox по координатам углов - левого верхнего и правого нижнего, флагу активности и набору строк.
    // Обращаем внимание на то, что обе точки входят в ListBox, так что если создать ListBox с topLeft.equals(bottomRight),
    // то будет создан ListBox ширины и высоты 1. Параметр lines может быть null.
    public ListBox(Point topLeft, Point bottomRight, WindowState state, String[] lines) throws WindowException {
        if (state == WindowState.DESTROYED) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        }
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
        setLines(lines);
        setState(state);
    }

    public ListBox(Point topLeft, Point bottomRight, String state, String[] lines) throws WindowException {
        this(topLeft, bottomRight, lines);
        setState(state);
    }

    //Создает ListBox по координатам левого верхнего угла, ширине, высоте, флагу активности и набору строк.
    // Параметр lines может быть null.
    public ListBox(int xLeft, int yTop, int width, int height, WindowState state, String[] lines) throws WindowException {
        this(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), state, lines);
    }

    public ListBox(int xLeft, int yTop, int width, int height, String state, String[] lines) throws WindowException {
        this(xLeft, yTop, width, height, lines);
        setState(state);
    }

    //Создает активный ListBox по координатам углов - левого верхнего и правого нижнего и набору строк.
    // Параметр lines может быть null.
    public ListBox(Point topLeft, Point bottomRight, String[] lines)  throws WindowException{
        this(topLeft, bottomRight, WindowState.ACTIVE, lines);
    }

    //Создает активный ListBox по координатам левого верхнего угла, ширине и высоте и набору строк.
    // Параметр lines может быть null.
    public ListBox(int xLeft, int yTop, int width, int height, String[] lines) throws WindowException {
        this(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), lines);
    }

    public String[] getLines() throws WindowException {
        if (lines == null) {
            return null;
        }
        if (lines.length == 0) {
            throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        }
        return Arrays.copyOf(lines, lines.length);
    }

    //Устанавливает набор строк ListBox.
    public void setLines(String[] lines) {
        this.lines = lines != null ? lines.clone() : null;
    }

    //Возвращает набор строк ListBox, начиная со строки “from” и до строки (“to”-1) включительно . Если в ListBox строк
    // меньше, чем “to”, возвращает строки от “from” и до конца. Гарантируется, что “from” < “to”. Если массив строк
    // равен null, возвращает null.

    /**
     * Возвращает набор строк ListBox, начиная со строки “from” и до строки (“to”- 1) включительно. Если массив строк
     * равен null, выбрасывается исключение WindowException с кодом ошибки EMPTY_ARRAY. Если “from” < 0 или в ListBox
     * строк меньше, чем “to” , или “from” > (“to” - 1), выбрасывается исключение WindowException с кодом ошибки WRONG_INDEX.
     */
    public String[] getLinesSlice(int from, int to) throws WindowException {
        if (lines == null) {
            throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        }
        if (from < 0 || from > to - 1 || lines.length < to) {
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        }
        to = to <= lines.length ? to : lines.length;
        return Arrays.copyOfRange(lines, from, to);
    }

    // Возвращает строку с номером index. Если массив строк равен null, выбрасывается исключение WindowException с кодом
    // ошибки EMPTY_ARRAY. Если строки с таким номером нет, выбрасывается исключение WindowException с кодом ошибки WRONG_INDEX. .
    public String getLine(int index) throws WindowException {
        if (lines == null || lines.length == 0) {
            throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        }
        if (lines.length <= index || index < 0) {
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        }
        return lines[index];
    }

    //Заменяет строку с номером index. Если массив строк равен null, выбрасывается исключение WindowException с кодом
    //ошибки EMPTY_ARRAY. Если строки с таким номером нет, выбрасывается исключение WindowException с кодом ошибки WRONG_INDEX.
    public void setLine(int index, String line) throws WindowException {
        if (lines == null || lines.length == 0) {
            throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        }
        if (index < 0 || lines.length <= index) {
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        }
        lines[index] = line;
    }

    //Ищет  первую совпадающую с line строку в массиве строк ListBox. Если строка найдена, возвращает ее индекс,
    // в противном случае возвращает null.
    public Integer findLine(String line) {
        //Integer index = null;lines[index]
        if (lines == null) {
            return null;
        }
        for (int i = 0; i < lines.length; i++) {
            if (line.equals(lines[i])) {
                return i;
            }
        }
        return null;

    }

    //Переворачивает массив строк ListBox., то есть делает 0-ю строку - последней, первую - предпоследней и т.д.
    // Если массив строк равен null, не делает ничего.
    public void reverseLineOrder() {
        if (lines == null) {
            return;
        }
        List<String> stringList = Arrays.asList(lines);
        Collections.reverse(stringList);
    }

    //Переворачивает каждую строку в массиве строк ListBox.Если массив строк равен null, не делает ничего.
    public void reverseLines() {
        if (lines == null) {
            return;
        }
        for (int i = 0; i < lines.length; i++) {
            lines[i] = StringOperations.reverse(lines[i]);
        }
    }

    //Заменяет массив строк на новый массив, вместо каждой строки вставлены две копии ее. Если массив строк равен null,
    // не делает ничего.
    public void duplicateLines() {
        if (lines == null) {
            return;
        }
        String[] newLines = new String[lines.length * 2];
        for (int i = 0; i < lines.length; i++) {
            newLines[i * 2] = lines[i];
            newLines[i * 2 + 1] = lines[i];
        }
        lines = newLines;

    }

    //Заменяет массив строк на новый массив, в котором каждая нечетная исходная строка удалена.
    // Если массив строк равен null или содержит только одну строку, не делает ничего.
    public void removeOddLines() {
        if (lines == null) {
            return;
        }
        int result = lines.length / 2;
        result = result % 2 == 0 ? result : result + 1;
        if (lines.length == 1) {
            result = 1;
        }
        String[] newLines = new String[result];
        for (int i = 0; i < lines.length; i++) {
            if (i * 2 < lines.length) {
                newLines[i] = lines[i * 2];
            }
        }
        lines = newLines;
    }

    //Возвращает true, если массив строк строго упорядочен по убыванию, иначе false. Если массив строк равен null,
    // возвращает true.
    public boolean isSortedDescendant() {
        if (lines == null) {
            return true;
        }
        String prevItem = lines[0];
        for (int i = 0; i < lines.length; i++) {
            if (i + 1 < lines.length && !StringOperations.isLess(lines[i + 1], prevItem)) {
                return false;
            }
            prevItem = lines[i];
        }
        return true;
    }

    //Передвигает ListBox  так, чтобы левый верхний угол его оказался в точке (x, y).
    public void moveTo(int x, int y) {
        int originWidth = getWidth();
        int originHeight = getHeight();
        getTopLeft().setX(x);
        getTopLeft().setY(y);
        getBottomRight().setX(getTopLeft().getX() + originWidth - 1);
        getBottomRight().setY(getTopLeft().getY() + originHeight - 1);
    }

    //Передвигает ListBox  так, чтобы левый верхний угол его оказался в точке point.
    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    //Передвигает ListBox на (dx, dy).
    public void moveRel(int dx, int dy) {
        moveTo(getTopLeft().getX() + dx, getTopLeft().getY() + dy);
    }

    //Изменяет ширину и длину ListBox в ratio раз при сохранении координат левой верхней точки. Дробная часть
    // вычисленной длины или ширины отбрасывается. Если при таком изменении длина или ширина какой-то из сторон окажется
    // меньше 1, то она принимается равной 1.
    public void resize(double ratio) {
        int newWidtg = (int) (getWidth() * ratio);
        int newHeight = (int) (getHeight() * ratio);
        newWidtg = newWidtg >= 1 ? newWidtg : 1;
        newHeight = newHeight >= 1 ? newHeight : 1;
        int newParamWidth = newWidtg - 1;
        int newParamHeight = newHeight - 1;
        newParamWidth = ratio != 0 ? newParamWidth : 0;
        newParamHeight = ratio != 0 ? newParamHeight : 0;
        getBottomRight().setX(getTopLeft().getX() + newParamWidth);
        getBottomRight().setY(getTopLeft().getY() + newParamHeight);
    }

    //Определяет, пересекается  ли ListBox с другим ListBox. Считается, что ListBox’ы пересекаются, если у них
    // есть хоть одна общая точка.
    public boolean isIntersects(ListBox listBox) {
        return getTopLeft().getX() <= listBox.getBottomRight().getX() && getBottomRight().getX() >= listBox.getTopLeft().getX() &&
                getTopLeft().getY() <= listBox.getBottomRight().getY() && getBottomRight().getY() >= listBox.getTopLeft().getY();
    }

    //Определяет, лежит ли ListBox целиком внутри текущего ListBox.
    public boolean isInside(ListBox listBox) {
        return listBox.getTopLeft().getX() >= getTopLeft().getX() && listBox.getBottomRight().getX() <= getBottomRight().getX() &&
                listBox.getTopLeft().getY() >= getTopLeft().getY() && listBox.getBottomRight().getY() <= getBottomRight().getY();
    }

    //методы equals и hashCode.
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListBox)) {
            return false;
        }
        ListBox listBox = (ListBox) obj;
        return obj instanceof ListBox && listBox.getTopLeft().getX() == getTopLeft().getX() &&
                listBox.getTopLeft().getY() == getTopLeft().getY() && listBox.getBottomRight().getX() == getBottomRight().getX() &&
                listBox.getBottomRight().getY() == getBottomRight().getY() && Arrays.equals(listBox.lines, lines);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
