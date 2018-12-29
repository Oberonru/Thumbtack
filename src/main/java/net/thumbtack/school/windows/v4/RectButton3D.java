package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;
import org.omg.PortableInterceptor.INACTIVE;

public class RectButton3D extends RectButton {
    // Создает RectButton3D по координатам углов - левого верхнего и правого нижнего, высоте zHeight, строке на кнопке
    // text и флагу активности. Обращаем вниманиe на то, что обе точки входят в кнопку, так что если создать кнопку
    // с topLeft.equals(bottomRight), то будет создана кнопка ширины и высоты 1.

    private int zHeight;

    public RectButton3D(Point topLeft, Point bottomRight, WindowState state, String text, int zHeight) throws WindowException {
        super(topLeft, bottomRight, state, text);
        this.zHeight = zHeight;
    }

    public RectButton3D(Point topLeft, Point bottomRight, String state, String text, int zHeight) throws WindowException {
        super(topLeft, bottomRight, text);
        this.zHeight = zHeight;
        setState(state);
    }

    //Создает RectButton3D по координатам левого верхнего угла, ширине, высоте, строке на кнопке text, высоте zHeight
    // и флагу активности.
    public RectButton3D(int xLeft, int yTop, int width, int height, WindowState state, String text, int zHeight) throws WindowException {
        super(xLeft, yTop, width, height, state, text);
        this.zHeight = zHeight;
    }

    public RectButton3D(int xLeft, int yTop, int width, int height, String state, String text, int zHeight) throws WindowException {
       this(xLeft, yTop, width, height, text, zHeight);
        setState(state);
    }

    // Создает активную RectButton3D по координатам углов - левого верхнего и правого нижнего,  строке на кнопке text
    // и высоте zHeight.
    public RectButton3D(Point topLeft, Point bottomRight, String text, int zHeight) throws  WindowException{
        super(topLeft, bottomRight, text);
        this.zHeight = zHeight;
    }

    //Создает активную RectButton3D по координатам левого верхнего угла, ширине, строке на кнопке text и высоте zHeight.
    public RectButton3D(int xLeft, int yTop, int width, int height, String text, int zHeight) throws WindowException {
        super(xLeft, yTop, width, height, text);
        this.zHeight = zHeight;
    }

    // Возвращает высоту RectButton3D по оси Z.
    public int getZHeight() {
        return zHeight;
    }

    //Устанавливает высоту RectButton3D по оси Z.
    public void setZHeight(int zHeight) {
        this.zHeight = zHeight;
    }

    //Определяет, лежит ли RectButton3D целиком внутри текущего RectButton3D. Высота учитывается.
    public boolean isInside(RectButton3D rectButton3D) {
        return super.isInside(rectButton3D) && zHeight >= rectButton3D.zHeight;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
