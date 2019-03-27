package net.thumbtack.school.windows.managers;

import net.thumbtack.school.windows.v4.Desktop;
import net.thumbtack.school.windows.v4.base.Window;
import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;


public class PairManager<T extends Window, V extends Window> {

    public PairManager<T, V> pairManager;

    private T firstWindow;
    private V secondWindow;

    public PairManager(T firstWindow, V secondWindow) throws WindowException {
        setFirstWindow(firstWindow);
        setSecondWindow(secondWindow);
    }

    public T getFirstWindow() {
        return firstWindow;
    }

    public void setFirstWindow(T firstWindow) throws WindowException {
        if (firstWindow == null) {
            throw new WindowException(WindowErrorCode.NULL_WINDOW);
        }
        this.firstWindow = firstWindow;
    }

    public V getSecondWindow() {
        return secondWindow;
    }

    public void setSecondWindow(V secondWindow) throws WindowException {
        if (secondWindow == null) {
            throw new WindowException(WindowErrorCode.NULL_WINDOW);
        }
        this.secondWindow = secondWindow;
    }

    //проверяет, верно ли, что одновременно находятся на Desktop все окна данного PairManager и еще одного PairManager.
    public boolean allWindowsFullyVisibleOnDesktop(PairManager pairManager, Desktop desktop) {
        return pairManager.getFirstWindow().isFullyVisibleOnDesktop(desktop) &&
                pairManager.getSecondWindow().isFullyVisibleOnDesktop(desktop);
    }

    //проверяет, верно ли это для двух разных PairManager.
    public static boolean allWindowsFullyVisibleOnDesktop(PairManager pairManager1, PairManager pairManager2, Desktop desktop) {
        return pairManager1.allWindowsFullyVisibleOnDesktop(pairManager1, desktop) &&
                pairManager2.allWindowsFullyVisibleOnDesktop(pairManager2, desktop);
    }

}
