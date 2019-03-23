package net.thumbtack.school.windows.v4;

public class Desktop {
    private int width, height;
    private static final int WIDTH = 640;
    private final static int HEIGHT = 480;
    //Экран компьютера. Координаты отсчитываются от левой верхней точки.
    //Создает Desktop по значениям ширины и высоты.
    public Desktop(int width, int height) {
        this.width = width;
        this.height = height;
    }

    //Создает классический VGA Desktop с размерами 640*480
    public Desktop() {
        this(WIDTH, HEIGHT);
    }

    //Возвращает ширину экрана
    public int getWidth() {
        return width;
    }

    //Возвращает высоту экрана
    public int getHeight() {
        return height;
    }

    //Возвращает площадь экрана (иными словами, количество пикселей)
    public int getArea() {
        int area = width * height;
        return area;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Desktop && ((Desktop) obj).height == height && ((Desktop) obj).width == width;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
