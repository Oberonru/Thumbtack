package net.thumbtack.school.windows.v3;

import net.thumbtack.school.windows.v3.Desktop;
import net.thumbtack.school.windows.v3.base.RectWindow;
import net.thumbtack.school.windows.v3.base.RoundWindow;
import net.thumbtack.school.windows.v3.iface.Movable;
import net.thumbtack.school.windows.v3.iface.Resizable;

public class RoundButton extends RoundWindow {
    //Нажимная круглая кнопка. Для кнопки определено 2 состояния - активна (можно нажать) и пассивна (серого цвета, нажать нельзя).

    private String text;

    //Создает RoundButton по координатам центра, значению радиуса и флагу активности.
    public RoundButton(Point center, int radius, boolean active, String text) {
        this.setCenter(center);
        this.setRadius(radius);
        this.setActive(active);
        this.text = text;
    }

    public RoundButton(Point center, int radius, boolean active) {
        this(center, radius, active, null);
    }

    //Создает RoundButton по координатам центра, значению радиуса и флагу активности.
    public RoundButton(int xCenter, int yCenter, int radius, boolean active, String text) {
        this(new Point(xCenter, yCenter), radius, active, text);
    }

    public RoundButton(int xCenter, int yCenter, int radius, boolean active) {
        this(new Point(xCenter, yCenter), radius, active);
    }

    //Создает активную RoundButton по координатам центра и значению радиуса.
    public RoundButton(Point center, int radius, String text) {
        this(center, radius, true, text);
    }

    public RoundButton(Point center, int radius) {
        this(center, radius, true);
    }

    //Создает активную RoundButton по координатам центра и значению радиуса.
    public RoundButton(int xCenter, int yCenter, int radius, String text) {
        this(xCenter, yCenter, radius, true, text);
    }

    public RoundButton(int xCenter, int yCenter, int radius) {
        this(xCenter, yCenter, radius, true);
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
