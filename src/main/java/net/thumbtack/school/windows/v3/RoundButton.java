package net.thumbtack.school.windows.v3;

public class RoundButton {
    //Нажимная круглая кнопка. Для кнопки определено 2 состояния - активна (можно нажать) и пассивна (серого цвета, нажать нельзя).

    private Point center;
    private boolean active;
    private int radius;

    private String text;

    //Создает RoundButton по координатам центра, значению радиуса и флагу активности.
    public RoundButton(Point center, int radius, boolean active, String text) {
        this.center = center;
        this.radius = radius;
        this.active = active;
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

    //Возвращает центр RoundButton.
    public Point getCenter() {
        return center;
    }

    //Возвращает радиус RoundButton.
    public int getRadius() {
        return radius;
    }

    //Возвращает true, если кнопка активна, иначе false.
    public boolean isActive() {
        return active;
    }

    //Передвигает RoundButton  так, чтобы центр его оказался в точке  (x, y).
    public void moveTo(int x, int y) {
        center.setX(x);
        center.setY(y);
    }

    //Передвигает RoundButton  так, чтобы центр его оказался в точке point.
    public void moveTo(Point point) {
        center = point;
    }

    //Устанавливает центр RoundButton.
    public void setCenter(int x, int y) {
        center.setX(x);
        center.setY(y);
    }

    //Устанавливает радиус RoundButton.
    public void setRadius(int radius) {
        this.radius = radius;
    }

    //Устанавливает состояние активности RoundButton.
    public void setActive(boolean active) {
        this.active = active;
    }

    //Передвигает RoundButton на (dx, dy).
    public void moveRel(int dx, int dy) {
        center.setX(center.getX() + dx);
        center.setY(center.getY() + dy);
    }

    //Изменяет радиус RoundButton в ratio раз, не изменяя центра.
    // Дробная часть вычисленного таким образом радиуса отбрасывается.
    // Если вычисленный радиус окажется меньше 1, то он принимается равным 1.
    public void resize(double ratio) {
        radius = (int) Math.ceil(radius * ratio);
    }

    //Определяет, лежит ли точка (x, y) внутри RoundButton.
    // Если точка лежит на окружности, считается, что она лежит внутри.
    // В этом методе мы пренебрегаем пиксельной структурой изображения и рассматриваем RoundButton как математический круг.
    public boolean isInside(int x, int y) {
        double distance = Math.sqrt(Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2));
        return radius >= distance;

    }

    //Определяет, лежит ли точка point внутри RoundButton.
    // Если точка лежит на окружности, считается, что она лежит внутри.
    // В этом методе мы пренебрегаем пиксельной структурой изображения и рассматриваем RoundButton как математический круг.
    public boolean isInside(Point point) {
        return radius >= Math.sqrt(Math.pow(point.getX() - center.getX(), 2) + Math.pow(point.getY() - center.getY(), 2));
    }

    //Определяет, верно ли, что вся RoundButton находится в пределах Desktop.
    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return center.getX() + radius < desktop.getWidth() && center.getX() - radius >= 0
                && center.getY() + radius < desktop.getHeight() && center.getY() - radius >= 0;
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
        return obj instanceof RoundButton && button.center.getX() == center.getX() &&
                button.center.getY() == center.getY() &&
                button.radius == radius && button.text == text;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
