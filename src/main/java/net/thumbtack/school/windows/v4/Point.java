package net.thumbtack.school.windows.v4;

public class Point  {
    //Точка на экране.
    private  int x,y;
    //Создает Point с заданными координатам
    public Point(int x, int y){
    this.x = x;
    this.y = y;
    }
    //Создает Point с координатам (0,0)
    public Point(){
    this(0,0);
    }
    //Создает point с координатами теми же, что и у передаваемого Point
    public Point(Point point){
       this(point.getX(), point.getY());
    }
    //Возвращает x-координату Point
    public int getX(){
    return x;
    }
    //Возвращает y-координату Point
    public int getY(){
    return y;
    }
    //Устанавливает новую x-координату Point
    public void setX(int x){
    this.x = x;
    }
    //Устанавливает новую y-координату Point
    public void setY(int y){
    this.y = y;
    }
    //Перемещает точку в новые координаты x, y
    public void moveTo(int x, int y){
    this.x = x;
    this.y = y;
    }
    //Перемещает точку в новые координаты, смещая ее на (dx, dy) от текущего положения.
    public void moveRel(int dx, int dy){
    this.x = x + dx;
    this.y = y + dy;
    }
    //Определяет, находится ли точка в пределах Desktop
    public boolean isVisibleOnDesktop(Desktop desktop){
        return x < desktop.getWidth() && x > 0 && y <= desktop.getHeight() && y > 0;
    }
    //Определяет, находится ли точка вне пределов Desktop
    public boolean isNotVisibleOnDesktop(Desktop desktop){
        return  x >= desktop.getWidth() || x <= 0 || y >= desktop.getHeight() || y <= 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Point && ((Point) obj).x == x && ((Point) obj).y == y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
