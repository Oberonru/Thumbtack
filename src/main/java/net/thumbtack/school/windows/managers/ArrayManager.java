package net.thumbtack.school.windows.managers;

import net.thumbtack.school.windows.v4.Desktop;
import net.thumbtack.school.windows.v4.base.Window;
import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.cursors.Cursor;

public class ArrayManager<T extends Window> {

    private T[] arrayWindow;


    public ArrayManager(T[] arrayWindow) throws WindowException {
      for (T elements : arrayWindow) {
          if (elements == null) {
              throw new WindowException(WindowErrorCode.NULL_WINDOW);
          }
      }
        this.arrayWindow = arrayWindow;
    }

    public T[] getWindows() {
        return arrayWindow;
    }

    public void setWindows(T[] arrayWindow) {
        this.arrayWindow = arrayWindow;
    }

    //возвращает i-й элемент вложенного массива
    public T getWindow(int index) {
        return (T) arrayWindow[index];
    }

    public void setWindow(T arrayWindow, int index) {
        this.arrayWindow[index] = arrayWindow;
    }

    //Проверяет равен ли размер вложенного массива размеру вложенного массива другого ArrayManager
    public boolean isSameSize(ArrayManager anotherArrayManager) {
        return this.arrayWindow.length == anotherArrayManager.getWindows().length;
    }

    //Определяет, лежат ли все окна, находящиеся под контролем менеджера, в пределах некоторого Desktop.
    public boolean allWindowsFullyVisibleOnDesktop(Desktop desktop) {
        for (T element : arrayWindow) {
            if (!element.isFullyVisibleOnDesktop(desktop)) {
                return false;
            }
        }
        return true;
    }

    //Определяет, лежит ли хоть одно окно из находящихся под контролем менеджера, в пределах некоторого Desktop.
    public boolean anyWindowFullyVisibleOnDesktop(Desktop desktop) {
        for (T element : arrayWindow) {
            if (element.isFullyVisibleOnDesktop(desktop)) {
                return true;
            }
        }
        return false;
    }

    //Возвращает первое окно в списке менеджера, в которое попадает некоторый Cursor. Считается, что курсор попадает в
    // окно, если его координаты находятся в пределах окна. Если такого окна нет, возвращает null.
    public Window getFirstWindowFromCursor(Cursor cursor) {
        for (T element : arrayWindow) {
          if (element.isInside(cursor.getX(), cursor.getY())) {
           return element;
          }
        }
        return null;
    }


}
