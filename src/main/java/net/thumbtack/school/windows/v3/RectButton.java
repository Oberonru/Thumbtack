package net.thumbtack.school.windows.v3;

public class RectButton {
    //Нажимная прямоугольная кнопка. Для кнопки определено 2 состояния - активна (можно нажать) и пассивна (серого
    // цвета, нажать нельзя). Предполагается, что всегда будут передаваться допустимые координаты, то есть при создании
    // или изменении всегда будет выполняться : левая точка не правее правой, верхняя точка не ниже нижней.
    private Point topLeft;
    private Point bottomRight;
    boolean active = true;

    private String text;

    //Создает RectButton по координатам углов - левого верхнего и правого нижнего, и флагу активности.
    // Обращаем внимание на то, что обе точки входят в кнопку, так что если создать кнопку с topLeft.equals(bottomRight),
    // то будет создана кнопка ширины и высоты 1.
    public RectButton(Point topLeft, Point bottomRight, boolean active, String text) {
        this(topLeft, bottomRight, text);
        this.active = active;
    }

    public RectButton(Point topLeft, Point bottomRight, boolean active) {
        this(topLeft, bottomRight);
        this.active = active;
    }

    //Создает RectButton по координатам левого верхнего угла, ширине, высоте и флагу активности.
    public RectButton(int xLeft, int yTop, int width, int height, boolean active, String text) {
        this(xLeft, yTop, width, height, text);
        this.active = active;
    }

    public RectButton(int xLeft, int yTop, int width, int height, boolean active) {
        this(xLeft, yTop, width, height);
        this.active = active;
    }

    //Создает активную RectButton по координатам углов - левого верхнего и правого нижнего.
    public RectButton(Point topLeft, Point bottomRight, String text) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.text = text;
    }
    public RectButton(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    //Создает активную RectButton по координатам левого верхнего угла, ширине и высоте.
    public RectButton(int xLeft, int yTop, int width, int height, String text) {
        this(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), text);
    }
    public RectButton(int xLeft, int yTop, int width, int height) {
        this(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1));
    }

    //Возвращает левую верхнюю точку RectButton.
    public Point getTopLeft() {
        return topLeft;
    }

    //Возвращает правую нижнюю точку RectButton.
    public Point getBottomRight() {
        return bottomRight;
    }

    //Возвращает true, если кнопка активна, иначе false.
    public boolean isActive() {
        return active;
    }

    //Устанавливает левую верхнюю точку RectButton.
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    //Устанавливает правую нижнюю точку RectButton.
    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    //Устанавливает состояние активности RectButton.
    public void setActive(boolean active) {
        this.active = active;
    }

    //Возвращает ширину RectButton.
    public int getWidth() {
        return Math.abs(bottomRight.getX() - topLeft.getX() + 1);
    }

    //Возвращает высоту RectButton.
    public int getHeight() {
        return Math.abs(bottomRight.getY() - topLeft.getY() + 1);
    }

    //Передвигает RectButton так, чтобы левый верхний угол его оказался в точке (x, y).
    public void moveTo(int x, int y) {
        int originWidth = getWidth();
        int originHeight = getHeight();
        topLeft.setX(x);
        topLeft.setY(y);
        bottomRight.setX(topLeft.getX() + originWidth - 1);
        bottomRight.setY(topLeft.getY() + originHeight - 1);
    }

    //Передвигает RectButton  так, чтобы левый верхний угол его оказался в точке point.
    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    //Передвигает RectButton на (dx, dy).
    public void moveRel(int dx, int dy) {
        moveTo(topLeft.getX() + dx, topLeft.getY() + dy);
    }

    //Изменяет ширину и длину RectButton в ratio раз при сохранении координат левой верхней точки.
    // Дробная часть вычисленной длины или ширины отбрасывается.
    // Если при таком изменении длина или ширина какой-то из сторон окажется меньше 1, то она принимается равной 1.
    public void resize(double ratio) {
        int newWidth = (int) (getWidth() * ratio);
        int newHeight = (int) (getHeight() * ratio);
        newWidth = newWidth >= 1 ? newWidth : 1;
        newHeight = newHeight >= 1 ? newHeight : 1;
        bottomRight.setX(topLeft.getX() + newWidth - 1);
        bottomRight.setY(topLeft.getY() + newHeight - 1);
    }

    //Определяет, лежит ли точка (x, y) внутри RectButton. Если точка лежит на стороне, считается, что она лежит внутри.
    public boolean isInside(int x, int y) {
        return x >= topLeft.getX() && x <= bottomRight.getX()
                && y >= topLeft.getY() && y <= bottomRight.getY();
    }

    //Определяет, лежит ли точка point внутри RectButton. Если точка лежит на стороне, считается, что она лежит внутри.
    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    //Определяет, пересекается  ли RectButton с другим RectButton. Считается, что кнопки пересекаются, если у них есть
    // хоть одна общая точка.
    public boolean isIntersects(RectButton rectButton) {
        return topLeft.getX() <= rectButton.bottomRight.getX() && bottomRight.getX() >= rectButton.topLeft.getX() &&
                topLeft.getY() <= rectButton.bottomRight.getY() && bottomRight.getY() >= rectButton.topLeft.getY();
    }

    //Определяет, лежит ли RectButton целиком внутри текущего RectButton.
    public boolean isInside(RectButton rectButton) {
        return rectButton.topLeft.getX() >= topLeft.getX() && rectButton.bottomRight.getX() <= bottomRight.getX() &&
                rectButton.topLeft.getY() >= topLeft.getY() && rectButton.bottomRight.getY() <= bottomRight.getY();
    }

    //Определяет, верно ли, что вся RectButton находится в пределах Desktop.
    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return topLeft.getX() >= 0 && topLeft.getX() <= desktop.getWidth() &&
                bottomRight.getY() >= 0 && bottomRight.getY() <= desktop.getHeight();
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
