package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

public class ComboBox extends ListBox {

    /**
     * Прямоугольное окно,  содержащее в себе список строк, одна из которых является выделенной (ее может и не быть).
     * Для ComboBox определено 2 состояния - активен (изображается черным цветом) и пассивен (серым цветом).
     * <p>
     * Предполагается, что всегда будут передаваться допустимые аргументы, то есть при создании или изменении всегда
     * будет выполняться : левая точка не правее правой, верхняя точка не выше нижней, номер выбранной строки не больше
     * значения (количество строк -1),
     *
     * если массив строк отсутствует, номер выбранной строки будет обязательно null.
     *
     *
     * Тем не менее в результате нескольких вызовов методов экземпляр класса может перейти в неконсистентное состояние,
     * например, если был установлен номер выбранной строки, а после этого массив строк устанавливается в null. В рамках
     * настоящего Задания мы не будем заниматься этими вопросами, отложим их до Задания 6.
     * <p>
     * Обращаем Ваше внимание,  что ComboBox должен иметь свою собственную копию массива, а не хранить в себе переданный
     * массив, (подробности см. в описании класса ListBox).
     */

    private Integer selected;

    //    Создает ComboBox по координатам углов - левого верхнего и правого нижнего, флагу активности, набору строк и
    // номеру выделенной строки. Если выделенной строки нет, в качестве “selected” передается null. Обращаем внимание на
    // то, что обе точки входят в ComboBox, так что если создать ComboBox с topLeft.equals(bottomRight), то будет создан
    // ComboBox ширины и высоты 1. Параметр lines может быть null.
    public ComboBox(Point topLeft, Point bottomRight, WindowState state, String[] lines, Integer selected) throws WindowException {
        super(topLeft, bottomRight, state, lines);
            setSelected(selected);

    }

    public ComboBox(Point topLeft, Point bottomRight, String state, String[] lines, Integer selected) throws WindowException {
        super(topLeft, bottomRight, state, lines);
        setSelected(selected);

    }

    //    Создает ComboBox по координатам левого верхнего угла, ширине, высоте, флагу активности,  набору строк и номеру
    // выделенной строки. Если выделенной строки нет, в качестве “selected” передается null. Параметр lines может быть null.
    public ComboBox(int xLeft, int yTop, int width, int height, WindowState state, String[] lines, Integer selected) throws WindowException {
        super(xLeft, yTop, width, height, state, lines);
        setSelected(selected);
    }

    public ComboBox(int xLeft, int yTop, int width, int height, String state, String[] lines, Integer selected) throws WindowException {
        super(xLeft, yTop, width, height, state, lines);
        setSelected(selected);
    }

    //    Создает активный ComboBox по координатам углов - левого верхнего и правого нижнего, набору строк и номеру
    // выделенной строки. Если выделенной строки нет, в качестве “selected” передается null. Параметр lines может быть null.
    public ComboBox(Point topLeft, Point bottomRight, String[] lines, Integer selected) throws WindowException {
        super(topLeft, bottomRight, lines);
        setSelected(selected);
    }

    //    Создает активный ComboBox по координатам левого верхнего угла, ширине, высоте, набору строк и номеру
    // выделенной строки. Если выделенной строки нет, в качестве “selected” передается null. Параметр lines может быть null.
    public ComboBox(int xLeft, int yTop, int width, int height, String[] lines, Integer selected) throws WindowException {
        super(xLeft, yTop, width, height, lines);
        setSelected(selected);
    }

    // Возвращает номер выбранной строки ComboBox.
    public Integer getSelected() {
        return selected != null  ? selected : null;
    }


    //Устанавливает номер выбранной строки ComboBox.
    /**
     * Выбрасывается исключение WindowException с кодом ошибки EMPTY_ARRAY, если текущее значение lines равно null,
     * а значение параметра selected не равен null.
     * Выбрасывается исключение WindowException с кодом ошибки WRONG_INDEX, если делается попытка установить номер,
     * больший чем (число строк - 1).
     */
    public void setSelected(Integer selected) throws WindowException {

        String[] lines = getLines();
        if (selected != null && lines == null) {
            throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        }
        if (selected != null && (selected >= lines.length || selected < 0)) {
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        }
        this.selected = selected;
    }

    @Override
    public void setLines(String[] lines) {
        super.setLines(lines);
        selected = null;
    }
}
