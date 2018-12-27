package net.thumbtack.school.windows.v3.base;

import net.thumbtack.school.windows.v3.Desktop;
import net.thumbtack.school.windows.v3.Point;
import net.thumbtack.school.windows.v3.iface.Movable;
import net.thumbtack.school.windows.v3.iface.Resizable;


public abstract class RectWindow extends Window implements Movable, Resizable {
    private Point topLeft;
    private Point bottomRight;


    //Возвращает левую верхнюю точку ListBox.
    public Point getTopLeft() {
        return topLeft;
    }
    //	Устанавливает левую верхнюю точку ListBox.
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    //Возвращает правую нижнюю точку ListBox.
    public Point getBottomRight() {
        return bottomRight;
    }
    //Устанавливает правую нижнюю точку ListBox.
    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }


    //Возвращает ширину RectButton.
    public int getWidth() {
        return Math.abs(bottomRight.getX() - topLeft.getX() + 1);
    }

    //Возвращает высоту RectButton.
    public int getHeight() {
        return Math.abs(bottomRight.getY() - topLeft.getY() + 1);
    }
    //Определяет, лежит ли точка (x, y) внутри ListBox. Если точка лежит на стороне, считается, что она лежит внутри.
    public boolean isInside(int x, int y) {
        return x >= topLeft.getX() && x <= bottomRight.getX()
                && y >= topLeft.getY() && y <= bottomRight.getY();
    }
    //Определяет, лежит ли точка point внутри ListBox. Если точка лежит на стороне, считается, что она лежит внутри.
    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    //Определяет, верно ли, что вся RectButton находится в пределах Desktop.
    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return topLeft.getX() >= 0 && topLeft.getX() <= desktop.getWidth() &&
                bottomRight.getY() >= 0 && bottomRight.getY() <= desktop.getHeight();
    }


}
