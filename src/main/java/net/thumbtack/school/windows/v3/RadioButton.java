package net.thumbtack.school.windows.v3;

public class RadioButton extends RoundButton {

    /**
     * Круглая кнопка, внутри которой может быть точка. Несколько таких кнопок, объединенных в группу, используются для
     * выбора одного из нескольких вариантов. Для кнопки определено 2 состояния - активна (можно нажать) и пассивна
     * (серого цвета, нажать нельзя).
     */

    private boolean checked;

    //Создает RadioButton по координатам центра, значению радиуса, флагу активности, тексту и состоянию.
    public RadioButton(Point center, int radius, boolean active, String text, boolean checked) {
        super(center, radius, active, text);
        this.checked = checked;
    }

    // Создает RadioButton по координатам центра, значению радиуса, флагу активности, тексту и состоянию.
    public RadioButton(int xCenter, int yCenter, int radius, boolean active, String text, boolean checked) {
        super(xCenter, yCenter, radius, active, text);
        this.checked = checked;
    }

    //Создает активную RadioButton по координатам центра, значению радиуса, тексту и состоянию.
    public RadioButton(Point center, int radius, String text, boolean checked) {
        super(center, radius, text);
        this.checked = checked;
    }

    //Создает активную RadioButton по координатам центра,  значению радиуса, тексту и состоянию.
    public RadioButton(int xCenter, int yCenter, int radius, String text, boolean checked) {
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
