package net.thumbtack.school.windows.v3.cursors;

import net.thumbtack.school.windows.v3.Point;
import net.thumbtack.school.windows.v3.iface.Movable;

public class Cursor implements Movable {
    /**
     * Курсор - не окно, Он может передвигаться, но менять размеры не может. В нашем классе эти размеры даже не будут храниться.
     * Создайте класс Cursor, поместив его в пакет net.thumbtack.school.windows.v3.cursors. В классе должны быть следующие конструкторы и методы
     */

    private Point point;
    private int cursorForm;

    //    Создает курсор указанной формы. Мы пока не будем конкретизировать понятие вида курсора, а просто будем считать,
    // что имеются различные формы курсоров, каждая форма имеет свой номер, нумерация произвольная. Курсор помещается в точку (x,y).
    public Cursor(int x, int y, int cursorForm) {
        point = new Point(x, y);
        this.cursorForm = cursorForm;
    }

    // Создает курсор указанной формы. Курсор помещается в точку point.
    public Cursor(Point point, int cursorForm) {
        this.point = new Point(point);
        this.cursorForm = cursorForm;
    }

    // Возвращает форму курсора.
    public int getCursorForm() {

        return cursorForm;
    }

    //Устанавливает форму курсора.
    public void setCursorForm(int cursorForm) {
        this.cursorForm = cursorForm;
    }

    // Возвращает x-координату курсора.
    public int getX() {
        return point.getX();
    }

    // Возвращает y-координату курсора.
    public int getY() {
        return point.getY();
    }

    //Устанавливает x-координату курсора.
    public void setX(int x) {
        point.setX(x);
    }

    // Устанавливает y-координату курсора.
    public void setY(int y) {
        point.setY(y);
    }

    //Перемещает курсор в точку (x,y).
    public void moveTo(int x, int y) {
        point.moveTo(x, y);
    }

    //Перемещает курсор в точку point.
    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    //Перемещает курсор на (dx,dy).
    public void moveRel(int dx, int dy) {
       moveTo(point.getX() + dx, point.getY() + dy);
    }


}
