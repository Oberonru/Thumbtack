package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.RoundWindow;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

public class RoundButton extends RoundWindow {
    //Нажимная круглая кнопка. Для кнопки определено 2 состояния - активна (можно нажать) и пассивна (серого цвета, нажать нельзя).

    private String text;

    //Создает RoundButton по координатам центра, значению радиуса и флагу активности.
    public RoundButton(Point center, int radius, WindowState state, String text) throws WindowException {
        this.setCenter(center);
        this.setRadius(radius);
        this.setState(state);
        this.text = text;
    }
    public RoundButton(Point center, int radius, String state, String text) throws WindowException {
        this.setCenter(center);
        this.setRadius(radius);
        this.setState(state);
        this.setText(text);
    }

    public RoundButton(Point center, int radius, WindowState state) throws WindowException {
        this(center, radius, state, null);
    }

    //Создает RoundButton по координатам центра, значению радиуса и флагу активности.
    public RoundButton(int xCenter, int yCenter, int radius, WindowState state, String text) throws WindowException {
        this(new Point(xCenter, yCenter), radius, state, text);
    }
    public RoundButton(int xCenter, int yCenter, int radius, String state, String text) throws WindowException{
        this(new Point(xCenter, yCenter), radius, state, text);

    }

    public RoundButton(int xCenter, int yCenter, int radius, WindowState state) throws WindowException {
        this(new Point(xCenter, yCenter), radius, state);
    }

    //Создает активную RoundButton по координатам центра и значению радиуса.
    public RoundButton(Point center, int radius, String text) throws WindowException {
        this(center, radius, WindowState.ACTIVE, text);
    }

    public RoundButton(Point center, int radius) throws WindowException {
        this(center, radius, WindowState.ACTIVE);
    }

    //Создает активную RoundButton по координатам центра и значению радиуса.
    public RoundButton(int xCenter, int yCenter, int radius, String text) throws WindowException {
        this(xCenter, yCenter, radius, (WindowState) null, text);
    }

    public RoundButton(int xCenter, int yCenter, int radius) throws WindowException {
        this(new Point(xCenter, yCenter), radius, (WindowState) null);
    }

    //Передвигает RoundButton  так, чтобы центр его оказался в точке  (x, y).
    public void moveTo(int x, int y) {
        getCenter().setX(x);
        getCenter().setY(y);
    }

    //Передвигает RoundButton на (dx, dy).
    public void moveRel(int dx, int dy) {
        getCenter().setX(getCenter().getX() + dx);
        getCenter().setY(getCenter().getY() + dy);
    }
    //Устанавливает радиус RoundButton.


    public boolean isInside(int x, int y) {
        double distance = Math.sqrt(Math.pow(x - getCenter().getX(), 2) + Math.pow(y - getCenter().getY(), 2));
        return getRadius() >= distance;
    }
    //Определяет, лежит ли точка point внутри RoundButton.
    // Если точка лежит на окружности, считается, что она лежит внутри.
    // В этом методе мы пренебрегаем пиксельной структурой изображения и рассматриваем RoundButton как математический круг.
    public boolean isInside(Point point) {
        return getRadius() >= Math.sqrt(Math.pow(point.getX() - getCenter().getX(), 2) + Math.pow(point.getY() - getCenter().getY(), 2));
    }
    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return getCenter().getX() + getRadius() < desktop.getWidth() && getCenter().getX() - getRadius() >= 0
                && getCenter().getY() + getRadius() < desktop.getHeight() && getCenter().getY() - getRadius() >= 0;
    }
    //Изменяет радиус RoundButton в ratio раз, не изменяя центра.
    // Дробная часть вычисленного таким образом радиуса отбрасывается.
    // Если вычисленный радиус окажется меньше 1, то он принимается равным 1.
    public void resize(double ratio) {
        setRadius((int) Math.ceil(getRadius() * ratio));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RoundButton)) {
            return false;
        }

        RoundButton button = (RoundButton) obj;
        return obj instanceof RoundButton && button.getCenter().getX() == getCenter().getX() &&
                button.getCenter().getY() == getCenter().getY() &&
                button.getRadius() == getRadius() && button.text == text;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
