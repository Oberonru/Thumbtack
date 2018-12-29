package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

public class WindowFactory {
    //Класс, создающий окна (фабрика окон)
    private static int RectButtonCount, RoundBottonCount;


    //Создает RectButton по координатам точек и флагу активности.
    public static RectButton createRectButton(Point leftTop, Point rightBottom, String state) throws  WindowException{
        return createRectButton(leftTop, rightBottom, state, null);
    }

    //Создает RectButton по координатам точек и флагу активности.
    public static RectButton createRectButton(Point leftTop, Point rightBottom, String state, String text) throws WindowException {
        RectButtonCount++;
        return new RectButton(leftTop, rightBottom, WindowState.ACTIVE, text);
    }
    public static RectButton createRectButton(Point leftTop, Point rightBottom, WindowState state, String text) throws WindowException {
        RectButtonCount++;
        return new RectButton(leftTop, rightBottom, WindowState.ACTIVE, text);
    }

    //Создает RoundButton по координатам центра, значению рад//иуса и флагу активности.
    public static RoundButton createRoundButton(Point center, int radius, WindowState state) {
        return createRoundButton(center, radius, state, null);
    }

    //Создает RoundButton по координатам центра, значению рад//иуса и флагу активности.
    public static RoundButton createRoundButton(Point center, int radius, WindowState state, String text) {
        RoundBottonCount++;
        return new RoundButton(center, radius, state, text);
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
