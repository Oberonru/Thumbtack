package net.thumbtack.school.windows.v3;

public class WindowFactory {
    //Класс, создающий окна (фабрика окон)
    private static int RectButtonCount, RoundBottonCount;


    //Создает RectButton по координатам точек и флагу активности.
    public static RectButton createRectButton(Point leftTop, Point rightBottom, boolean active) {
        return createRectButton(leftTop, rightBottom, active, null);
    }

    //Создает RectButton по координатам точек и флагу активности.
    public static RectButton createRectButton(Point leftTop, Point rightBottom, boolean active, String text) {
        RectButtonCount++;
        return new RectButton(leftTop, rightBottom, active, text);
    }

    //Создает RoundButton по координатам центра, значению рад//иуса и флагу активности.
    public static RoundButton createRoundButton(Point center, int radius, boolean active) {
        return createRoundButton(center, radius, active, null);
    }

    //Создает RoundButton по координатам центра, значению рад//иуса и флагу активности.
    public static RoundButton createRoundButton(Point center, int radius, boolean active, String text) {
        RoundBottonCount++;
        return new RoundButton(center, radius, active, text);
    }

    //Возвращает количество RectButton, созданных с помощью метода createRectButton.
    public static int getRectButtonCount() {
        return RectButtonCount;
    }

    //Возвращает количество RoundButton, созданных с помощью метода createRoundButton.
    public static int getRoundButtonCount() {
        return RoundBottonCount;
    }

    //Возвращает общее количество окон (RectButton и RoundButton), созданных с помощью методов WindowFactory.
    public static int getWindowCount() {
        return RectButtonCount + RoundBottonCount;
    }

    //Устанавливает количество всех окон, созданных с помощью методов WindowFactory, равным 0 (иными словами, реинициализирует фабрику).
    public static void reset() {
        RectButtonCount = 0;
        RoundBottonCount = 0;
    }

}
