package net.thumbtack.school.windows.managers;

import net.thumbtack.school.windows.v4.base.Window;
import net.thumbtack.school.windows.v4.base.WindowException;

public class NamedManager<T> extends Manager {

    //имя менеджера
    private String name;

    public NamedManager(Window window, String name) throws WindowException {
        super(window);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
