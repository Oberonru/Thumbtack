package net.thumbtack.school.windows.v4.base;

import net.thumbtack.school.windows.v4.Desktop;
import net.thumbtack.school.windows.v4.Point;
import net.thumbtack.school.windows.v4.iface.Movable;
import net.thumbtack.school.windows.v4.iface.Resizable;

public abstract class Window implements Movable, Resizable {
    //устаналивает вместо active состояние кнопки(enum)
    private WindowState state;

    public void setState(WindowState newState) throws WindowException {
        if (newState == null
                || state == null && newState == WindowState.DESTROYED
                || state == WindowState.DESTROYED ) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        }
        state = newState;
    }

    public void setState(String stateString) throws WindowException {
        WindowState state = WindowState.fromString(stateString);
        setState(state);
    }

    public WindowState getState() {
        return state;
    }

    public abstract boolean isInside(int x, int y);
    public abstract boolean isInside(Point point);
    public abstract boolean isFullyVisibleOnDesktop(Desktop desktop);
}