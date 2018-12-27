package net.thumbtack.school.windows.v3.base;

import net.thumbtack.school.windows.v3.Point;
import net.thumbtack.school.windows.v3.iface.Movable;
import net.thumbtack.school.windows.v3.iface.Resizable;

public abstract class RoundWindow extends Window implements Movable, Resizable {

    private Point center;
    private int radius;

    //Возвращает центр RoundButton.
    public Point getCenter() {
        return center;
    }
    public void setCenter(Point center) {
        this.center = center;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    //Возвращает радиус RoundButton.
    public int getRadius() {
        return radius;
    }
    //Устанавливает центр RoundButton.

    public void setCenter(int x, int y) {
        center.setX(x);
        center.setY(y);
    }



}
