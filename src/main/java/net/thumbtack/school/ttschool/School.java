package net.thumbtack.school.ttschool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class School {
    private String name;
    private int year;
    private Set<Group> groups = new HashSet<>();

    public School(String name, int age) throws TrainingException {
        setName(name);
        this.year = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name.length() == 0) {
            throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        }
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    //7.Добавляет Group в школу. Если группа с таким именем уже есть, выбрасывает TrainingException с
    // TrainingErrorCode.DUPLICATE_GROUP_NAME
    public void addGroup(Group group) throws TrainingException {
        for (Group groupSet : groups) {
            if (groupSet.getName().equals(group.getName())) {
                throw new TrainingException(TrainingErrorCode.DUPLICATE_GROUP_NAME);
            }
        }
        groups.add(group);
    }

    //8.Удаляет Group из школы. Если такой Group в школе нет, выбрасывает TrainingException с
    // TrainingErrorCode.GROUP_NOT_FOUND
    public void removeGroup(Group group) throws TrainingException {
        if (!groups.contains(group)) {
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
        }
        Iterator<Group> groupIterator = groups.iterator();
        while (groupIterator.hasNext()) {
            if (groupIterator.next().equals(group)) {
                groups.remove(group);
                break;
            }
        }
    }

    //9.Удаляет Group с данным названием из школы. Если группа с таким названием не найдена, выбрасывает
    // TrainingException с TrainingErrorCode.GROUP_NOT_FOUND
    public void removeGroup(String name) throws TrainingException {
        if (!groups.removeIf(t -> t.getName().equals(name))) {
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
        }
    }

    //10.Определяет, есть ли в школе группа с таким названием.
    public boolean containsGroup(Group group) {
        for (Group groupItem : groups) {
            if (groupItem.getName().equals(group.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof School)) {
            return false;
        }
        School school = (School) obj;
        return obj instanceof School && school.getName().equals(getName()) && Objects.equals(school.getName(), getName())
                && school.getYear() == getYear();
    }
}
