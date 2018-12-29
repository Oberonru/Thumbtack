package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.RectWindow;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;
import net.thumbtack.school.windows.v4.iface.Movable;

public class RectButton extends RectWindow implements Movable {
    //Нажимная прямоугольная кнопка. Для кнопки определено 2 состояния - активна (можно нажать) и пассивна (серого
    // цвета, нажать нельзя). Предполагается, что всегда будут передаваться допустимые координаты, то есть при создании
    // или изменении всегда будет выполняться : левая точка не правее правой, верхняя точка не ниже нижней.

    private String text;

    //Создает RectButton по координатам углов - левого верхнего и правого нижнего, и флагу активности.
    // Обращаем внимание на то, что обе точки входят в кнопку, так что если создать кнопку с topLeft.equals(bottomRight),
    // то будет создана кнопка ширины и высоты 1.
    public RectButton(Point topLeft, Point bottomRight, WindowState state, String text) throws WindowException {
        this(topLeft, bottomRight, text);
        setState(state);
    }
    public RectButton(Point topLeft, Point bottomRight, String state, String text) throws WindowException {
        this(topLeft, bottomRight, text);
        setState(state);
    }

    public RectButton(Point topLeft, Point bottomRight, WindowState state)  throws WindowException {
        this(topLeft, bottomRight);
        setState(state);
    }

    //Создает RectButton по координатам левого верхнего угла, ширине, высоте и флагу активности.
    public RectButton(int xLeft, int yTop, int width, int height, WindowState state, String text) throws WindowException {
        this(xLeft, yTop, width, height, text);
        setState(state);
    }
    public RectButton(int xLeft, int yTop, int width, int height, String state, String text) throws WindowException {
        this(xLeft, yTop, width, height, text);
        setState(state);
    }

    public RectButton(int xLeft, int yTop, int width, int height, WindowState state)  throws WindowException {
        this(xLeft, yTop, width, height);
        setState(state);
    }

    //Создает активную RectButton по координатам углов - левого верхнего и правого нижнего.
    public RectButton(Point topLeft, Point bottomRight, String text) throws WindowException {
        this(topLeft, bottomRight, WindowState.ACTIVE);
        this.text = text;
    }

    public RectButton(Point topLeft, Point bottomRight) {
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
    }

    //Создает активную RectButton по координатам левого верхнего угла, ширине и высоте.
    public RectButton(int xLeft, int yTop, int width, int height, String text) throws  WindowException{
        this(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), text);
    }

    public RectButton(int xLeft, int yTop, int width, int height) {
        this(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1));
    }

    //Передвигает RectButton так, чтобы левый верхний угол его оказался в точке (x, y).
    public void moveTo(int x, int y) {
        int originWidth = getWidth();
        int originHeight = getHeight();
        getTopLeft().setX(x);
        getTopLeft().setY(y);
        getBottomRight().setX(getTopLeft().getX() + originWidth - 1);
        getBottomRight().setY(getTopLeft().getY() + originHeight - 1);
    }

    //Передвигает RectButton на (dx, dy).
    public void moveRel(int dx, int dy) {
        moveTo(getTopLeft().getX() + dx, getTopLeft().getY() + dy);
    }

    //Изменяет ширину и длину RectButton в ratio раз при сохранении координат левой верхней точки.
    // Дробная часть вычисленной длины или ширины отбрасывается.
    // Если при таком изменении длина или ширина какой-то из сторон окажется меньше 1, то она принимается равной 1.
    public void resize(double ratio) {
        int newWidth = (int) (getWidth() * ratio);
        int newHeight = (int) (getHeight() * ratio);
        newWidth = newWidth >= 1 ? newWidth : 1;
        newHeight = newHeight >= 1 ? newHeight : 1;
        getBottomRight().setX(getTopLeft().getX() + newWidth - 1);
        getBottomRight().setY(getTopLeft().getY() + newHeight - 1);
    }

    //Определяет, пересекается  ли RectButton с другим RectButton. Считается, что кнопки пересекаются, если у них есть
    // хоть одна общая точка.
    public boolean isIntersects(RectButton rectButton) {
        return getTopLeft().getX() <= rectButton.getBottomRight().getX() && getBottomRight().getX() >= rectButton.getTopLeft().getX() &&
                getTopLeft().getY() <= rectButton.getBottomRight().getY() && getBottomRight().getY() >= rectButton.getTopLeft().getY();
    }

    //Определяет, лежит ли RectButton целиком внутри текущего RectButton.
    public boolean isInside(RectButton rectButton) {
        return rectButton.getTopLeft().getX() >= getTopLeft().getX() && rectButton.getBottomRight().getX() <= getBottomRight().getX() &&
                rectButton.getTopLeft().getY() >= getTopLeft().getY() && rectButton.getBottomRight().getY() <= getBottomRight().getY();
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return getTopLeft().getX() >= 0 && getTopLeft().getX() <= desktop.getWidth() &&
                getBottomRight().getY() >= 0 && getBottomRight().getY() <= desktop.getHeight();
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RectButton)) {
            return false;
        }
        RectButton button = (RectButton) obj;
        return button.getTopLeft().getX() == getTopLeft().getX() && button.getTopLeft().getY() == getTopLeft().getY() &&
                button.getBottomRight().getX() == getBottomRight().getX() && button.getBottomRight().getY() == getBottomRight().getY();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
