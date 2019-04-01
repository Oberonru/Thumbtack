package net.thumbtack.school.windows.managers;

import net.thumbtack.school.windows.v4.Point;
import net.thumbtack.school.windows.v4.base.Window;
import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;

public class Manager<T extends Window> {

    private T window;

    public Manager(T window) throws WindowException {
       setWindow(window);
    }

    public T getWindow() {
        return window;
    }

    public void setWindow(T window) {
        this.window = window;
    }

    public void moveTo(int x, int y) {
        window.moveTo(x, y);
    }

    public void moveTo(Point point) {
       window.moveTo(point);
    }


}
