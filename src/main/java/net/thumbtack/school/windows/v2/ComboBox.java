package net.thumbtack.school.windows.v2;

public class ComboBox extends ListBox{

    /** Прямоугольное окно,  содержащее в себе список строк, одна из которых является выделенной (ее может и не быть).
    *Для ComboBox определено 2 состояния - активен (изображается черным цветом) и пассивен (серым цветом).

    *  Предполагается, что всегда будут передаваться допустимые аргументы, то есть при создании или изменении всегда
    * будет выполняться : левая точка не правее правой, верхняя точка не выше нижней, номер выбранной строки не больше
    * значения (количество строк -1), если массив строк отсутствует, номер выбранной строки будет обязательно null.
    * Тем не менее в результате нескольких вызовов методов экземпляр класса может перейти в неконсистентное состояние,
    * например, если был установлен номер выбранной строки, а после этого массив строк устанавливается в null. В рамках
    * настоящего Задания мы не будем заниматься этими вопросами, отложим их до Задания 6.
    *
    *Обращаем Ваше внимание,  что ComboBox должен иметь свою собственную копию массива, а не хранить в себе переданный
    *  массив, (подробности см. в описании класса ListBox).
    */

    private Integer selected;

   //    Создает ComboBox по координатам углов - левого верхнего и правого нижнего, флагу активности, набору строк и
   // номеру выделенной строки. Если выделенной строки нет, в качестве “selected” передается null. Обращаем внимание на
   // то, что обе точки входят в ComboBox, так что если создать ComboBox с topLeft.equals(bottomRight), то будет создан
   // ComboBox ширины и высоты 1. Параметр lines может быть null.
    public ComboBox(Point topLeft, Point bottomRight, boolean active, String[] lines, Integer selected) {
        super(topLeft, bottomRight, active, lines);
        this.selected = selected != null ? selected : null;
    }
    //    Создает ComboBox по координатам левого верхнего угла, ширине, высоте, флагу активности,  набору строк и номеру
    // выделенной строки. Если выделенной строки нет, в качестве “selected” передается null. Параметр lines может быть null.
    public ComboBox(int xLeft, int yTop, int width, int height, boolean active, String[] lines, Integer selected) {
        super(xLeft, yTop, width, height, active, lines);
        this.selected = selected != null ? selected : null;
    }
    //    Создает активный ComboBox по координатам углов - левого верхнего и правого нижнего, набору строк и номеру
    // выделенной строки. Если выделенной строки нет, в качестве “selected” передается null. Параметр lines может быть null.
    public ComboBox(Point topLeft, Point bottomRight,  String[] lines, Integer selected) {
        super(topLeft, bottomRight, lines);
        this.selected = selected != null ? selected : null;
    }
    //    Создает активный ComboBox по координатам левого верхнего угла, ширине, высоте, набору строк и номеру
    // выделенной строки. Если выделенной строки нет, в качестве “selected” передается null. Параметр lines может быть null.
    public ComboBox(int xLeft, int yTop, int width, int height, String[] lines, Integer selected) {
        super(xLeft, yTop, width, height, lines);
        this.selected = selected != null ? selected : null;
    }

    // Возвращает номер выбранной строки ComboBox.
    public Integer getSelected() {
        return selected;
    }


    //Устанавливает номер выбранной строки ComboBox.
    public void setSelected(Integer selected) {
        this.selected = selected;
    }


}
