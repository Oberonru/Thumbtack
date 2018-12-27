package net.thumbtack.school.windows.v3.base;

import net.thumbtack.school.windows.v3.Desktop;
import net.thumbtack.school.windows.v3.Point;
import net.thumbtack.school.windows.v3.iface.Movable;
import net.thumbtack.school.windows.v3.iface.Resizable;

public abstract class Window implements Movable, Resizable {
    private boolean active;

    //Возвращает true, если ListBox активен, иначе false
    public boolean isActive() {
        return active;
    }
    //Устанавливает состояние активности ListBox.
    public void setActive(boolean active) {
        this.active = active;
    }

    public abstract boolean isInside(int x, int y);
    public abstract boolean isInside(Point point);
    public abstract boolean isFullyVisibleOnDesktop(Desktop desktop);
}