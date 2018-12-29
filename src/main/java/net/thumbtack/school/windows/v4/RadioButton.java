package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

public class RadioButton extends RoundButton {

    /**
     * Круглая кнопка, внутри которой может быть точка. Несколько таких кнопок, объединенных в группу, используются для
     * выбора одного из нескольких вариантов. Для кнопки определено 2 состояния - активна (можно нажать) и пассивна
     * (серого цвета, нажать нельзя).
     */

    private boolean checked;



    // Создает RadioButton по координатам центра, значению радиуса, флагу активности, тексту и состоянию.
    public RadioButton(int xCenter, int yCenter, int radius, WindowState state, String text, boolean checked) throws WindowException {
        super(xCenter, yCenter, radius, state, text);
        this.checked = checked;
    }
    public RadioButton(int xCenter, int yCenter, int radius, String state, String text, boolean checked) throws WindowException {
        super(xCenter, yCenter, radius, text);
        this.checked = checked;
        setState(state);
    }
    public RadioButton(Point center, int radius, WindowState state, String text, boolean checked) throws WindowException {
        this(center, radius, text, checked);
        setState(state);
    }
    //Создает RadioButton по координатам центра, значению радиуса, флагу активности, тексту и состоянию.
    public RadioButton(Point center, int radius, String state, String text, boolean checked) throws WindowException {
        this(center, radius, text, checked);
        setState(state);
    }

    //Создает активную RadioButton по координатам центра, значению радиуса, тексту и состоянию.
    public RadioButton(Point center, int radius, String text, boolean checked) {
        super(center, radius, text);
        this.checked = checked;
    }

    //Создает активную RadioButton по координатам центра,  значению радиуса, тексту и состоянию.
    public RadioButton(int xCenter, int yCenter, int radius, String text, boolean checked) throws WindowException{
        super(xCenter, yCenter, radius, text);
        this.checked = checked;
    }


    //Возвращает true, если точка внутри кнопки установлена, иначе false.
    public boolean isChecked() {
        return checked;
    }

    //  Устанавливает или убирает точку внутри RadioButton.
    public void setChecked(boolean checked) {
        this.checked = checked;
    }


}
