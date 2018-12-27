package net.thumbtack.school.windows.v3.iface;

import net.thumbtack.school.windows.v3.Point;

public interface Movable {
    //Передвигает окно так,  так, чтобы его базовая точка (левый верхний угол или центр соответственно)  оказалась в точке (x,y)
    void moveTo(int x, int y);

    //  Передвигает окно так,  так, чтобы его базовая точка (левый верхний угол или центр соответственно)  оказалась в точке point
    default
      void moveTo(Point point) {
       moveTo(point.getX(), point.getY());
    }

    // Передвигает окно на (dx, dy)
    void moveRel(int dx, int dy);



}
