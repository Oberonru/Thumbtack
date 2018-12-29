package net.thumbtack.school.windows.v4.base;

import net.thumbtack.school.windows.v4.Desktop;
import net.thumbtack.school.windows.v4.Point;
import net.thumbtack.school.windows.v4.iface.Movable;
import net.thumbtack.school.windows.v4.iface.Resizable;

public abstract class Window implements Movable, Resizable {
    private WindowState state;

    public void setState(WindowState state) {
        this.state = state;
    }

    public void setState(String stateString) {
        setState(WindowState.fromString(stateString));
    }

    public WindowState getState() {
        return state;
    }


    public abstract boolean isInside(int x, int y);
    public abstract boolean isInside(Point point);
    public abstract boolean isFullyVisibleOnDesktop(Desktop desktop);
}