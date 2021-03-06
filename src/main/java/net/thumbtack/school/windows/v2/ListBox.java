package net.thumbtack.school.windows.v2;

import net.thumbtack.school.base.StringOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListBox {
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


    private Point topLeft;
    private Point bottomRight;
    private boolean active = true;
    private String[] lines;


    //Создает ListBox по координатам углов - левого верхнего и правого нижнего, флагу активности и набору строк.
    // Обращаем внимание на то, что обе точки входят в ListBox, так что если создать ListBox с topLeft.equals(bottomRight),
    // то будет создан ListBox ширины и высоты 1. Параметр lines может быть null.
    public ListBox(Point topLeft, Point bottomRight, boolean active, String[] lines) {
        this(topLeft, bottomRight, lines);
        this.active = active;
    }

    //Создает ListBox по координатам левого верхнего угла, ширине, высоте, флагу активности и набору строк.
    // Параметр lines может быть null.
    public ListBox(int xLeft, int yTop, int width, int height, boolean active, String[] lines) {
        this(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), active, lines);
    }

    //Создает активный ListBox по координатам углов - левого верхнего и правого нижнего и набору строк.
    // Параметр lines может быть null.
    public ListBox(Point topLeft, Point bottomRight, String[] lines) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.lines = lines != null ? lines.clone() : null;
    }

    //Создает активный ListBox по координатам левого верхнего угла, ширине и высоте и набору строк.
    // Параметр lines может быть null.
    public ListBox(int xLeft, int yTop, int width, int height, String[] lines) {
        this(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), lines);
    }

    //Возвращает левую верхнюю точку ListBox.
    public Point getTopLeft() {
        return topLeft;
    }

    //Возвращает правую нижнюю точку ListBox.
    public Point getBottomRight() {
        return bottomRight;
    }

    //Возвращает true, если ListBox активен, иначе false
    public boolean isActive() {
        return active;
    }

    //	Устанавливает левую верхнюю точку ListBox.
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    //Устанавливает правую нижнюю точку ListBox.
    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    //Устанавливает состояние активности ListBox.
    public void setActive(boolean active) {
        this.active = active;
    }

    //Возвращает ширину ListBox.
    public int getWidth() {
        return Math.abs(bottomRight.getX() - topLeft.getX()) + 1;
    }

    //Возвращает высоту ListBox.
    public int getHeight() {
        return Math.abs(bottomRight.getY() - topLeft.getY()) + 1;
    }

    //Возвращает копию набора строк ListBox.
    public String[] getLines() {
        if (lines == null) {
            return null;
        }
        return Arrays.copyOf(lines,lines.length);
    }

    //Устанавливает набор строк ListBox.
    public void setLines(String[] lines) {
        this.lines = lines;
    }

    //Возвращает набор строк ListBox, начиная со строки “from” и до строки (“to”-1) включительно . Если в ListBox строк
    // меньше, чем “to”, возвращает строки от “from” и до конца. Гарантируется, что “from” < “to”. Если массив строк
    // равен null, возвращает null.
    public String[] getLinesSlice(int from, int to) {
        if (lines == null || from >= lines.length) {
            return null;
        }
        to = to <= lines.length ? to : lines.length;
        return Arrays.copyOfRange(lines, from, to);
    }

    // Возвращает строку с номером index. Если строки с таким номером нет или массив строк равен null, возвращает null.
    public String getLine(int index) {
        if (lines.length > index) {
            return lines[index];
        }
        return null;
    }

    //Заменяет строку с номером index. Если строки с таким номером нет или массив строк равен null, ничего не делает.
    public void setLine(int index, String line) {
        if (lines.length > index) {
            lines[index] = line;
        }
    }

    //Ищет  первую совпадающую с line строку в массиве строк ListBox. Если строка найдена, возвращает ее индекс,
    // в противном случае возвращает null.
    public Integer findLine(String line) {
        //Integer index = null;
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
        topLeft.setX(x);
        topLeft.setY(y);
        bottomRight.setX(topLeft.getX() + originWidth - 1);
        bottomRight.setY(topLeft.getY() + originHeight - 1);
    }

    //Передвигает ListBox  так, чтобы левый верхний угол его оказался в точке point.
    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    //Передвигает ListBox на (dx, dy).
    public void moveRel(int dx, int dy) {
        moveTo(topLeft.getX() + dx, topLeft.getY() + dy);
    }

    //Изменяет ширину и длину ListBox в ratio раз при сохранении координат левой верхней точки. Дробная часть
    // вычисленной длины или ширины отбрасывается. Если при таком изменении длина или ширина какой-то из сторон окажется
    // меньше 1, то она принимается равной 1.
    public void resize(double ratio) {
        int newWidtg = (int) (getWidth() * ratio);
        int newHeight = (int) (getHeight() * ratio);
        newWidtg = newWidtg >=1 ? newWidtg : 1;
        newHeight = newHeight >=1 ? newHeight : 1;
        int newParamWidth =  newWidtg - 1;
        int newParamHeight = newHeight - 1;
        newParamWidth = ratio != 0 ? newParamWidth : 0;
        newParamHeight = ratio != 0 ? newParamHeight : 0;
        bottomRight.setX(topLeft.getX() + newParamWidth);
        bottomRight.setY(topLeft.getY() + newParamHeight);
    }

    //Определяет, лежит ли точка (x, y) внутри ListBox. Если точка лежит на стороне, считается, что она лежит внутри.
    public boolean isInside(int x, int y) {
        return x >= topLeft.getX() && x <= bottomRight.getX() &&
                y >= topLeft.getY() && y <= bottomRight.getY();
    }

    //Определяет, лежит ли точка point внутри ListBox. Если точка лежит на стороне, считается, что она лежит внутри.
    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    //Определяет, пересекается  ли ListBox с другим ListBox. Считается, что ListBox’ы пересекаются, если у них
    // есть хоть одна общая точка.
    public boolean isIntersects(ListBox listBox) {
        return topLeft.getX() <= listBox.bottomRight.getX() && bottomRight.getX() >= listBox.topLeft.getX() &&
                topLeft.getY() <= listBox.bottomRight.getY() && bottomRight.getY() >= listBox.topLeft.getY();
    }

    //Определяет, лежит ли ListBox целиком внутри текущего ListBox.
    public boolean isInside(ListBox listBox) {
        return listBox.topLeft.getX() >= topLeft.getX() && listBox.bottomRight.getX() <= bottomRight.getX() &&
                listBox.topLeft.getY() >= topLeft.getY() && listBox.bottomRight.getY() <= bottomRight.getY();
    }



    //Определяет, верно ли, что весь ListBox находится в пределах Desktop.
    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return topLeft.getX() >= 0 && bottomRight.getX() <= desktop.getWidth() &&
               topLeft.getY() >= 0 && bottomRight.getY() <= desktop.getHeight();
    }

    //методы equals и hashCode.
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListBox)) {
            return false;
        }
        ListBox listBox = (ListBox) obj;
        return obj instanceof ListBox && listBox.topLeft.getX() == topLeft.getX() &&
                listBox.topLeft.getY() == topLeft.getY() && listBox.bottomRight.getX() == bottomRight.getX() &&
                listBox.bottomRight.getY() == bottomRight.getY() && Arrays.equals(listBox.lines, lines);
    }

    @Override
        public int hashCode() {
            return super.hashCode();
        }
}
