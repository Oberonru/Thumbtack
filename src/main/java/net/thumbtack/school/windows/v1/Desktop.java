package net.thumbtack.school.windows.v1;

public class Desktop {
    private int width, height;

    //Экран компьютера. Координаты отсчитываются от левой верхней точки.
    //Создает Desktop по значениям ширины и высоты.
    public Desktop(int width, int height) {
        this.width = width;
        this.height = height;
    }

    //Создает классический VGA Desktop с размерами 640*480
    public Desktop() {
        this(640, 480);
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
