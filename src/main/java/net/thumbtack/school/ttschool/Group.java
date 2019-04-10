package net.thumbtack.school.ttschool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {
    private String name;
    private String room;
    private List<Trainee> trainees = new ArrayList<>();

    public Group(String name, String room) throws TrainingException {
        setName(name);
        setRoom(room);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name.length() == 0) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        }
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if (room == null || room.length() == 0) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        }
        this.room = room;
    }

    //6.Возвращает список учащихся.
    public List<Trainee> getTrainees() {
        return trainees;
    }

    //7.Добавляет Trainee в группу.
    public void addTrainee(Trainee trainee) {
        trainees.add(trainee);
    }

    //8.Удаляет Trainee из группы. Если такого Trainee в группе нет, выбрасывает
    // TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
    public void removeTrainee(Trainee trainee) throws TrainingException {
        boolean isRemoved = false;
        for (Trainee traineeItem : trainees) {
            if (traineeItem.equals(trainee)) {
               isRemoved = true;
                trainees.remove(trainee);
            }
           if (!isRemoved) {
               throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
           }
        }
    }

    //9.Удаляет Trainee с данным номером в списке из группы. Если номер не является допустимым, выбрасывает
    // TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
    public void removeTrainee(int index) throws TrainingException {
        if (index < 0 || index > trainees.size() - 1) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        trainees.remove(index);
    }

    //10.Возвращает первый найденный в списке группы Trainee, у которого имя равно firstName. Если такого Trainee в
    // группе нет, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        for (Trainee traineeItem : trainees) {
            if (traineeItem.getFirstName().equals(firstName)) {
                return traineeItem;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    //11.Возвращает первый найденный в списке группы Trainee, у которого полное имя равно fullName. Если такого Trainee
    // в группе нет, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        for (Trainee traineeItem : trainees) {
            if (traineeItem.getFullName().equals(fullName)) {
                return traineeItem;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    //12.Сортирует список Trainee группы, упорядочивая его по возрастанию имени Trainee.
    public void sortTraineeListByFirstNameAscendant() {
        Collections.sort(trainees, this::compareFirstname);
    }

    //13.Сортирует список Trainee группы, упорядочивая его по убыванию оценки Trainee.
    public void sortTraineeListByRatingDescendant() {
        Collections.sort(trainees, this::compareRathing);
    }

    //14.Переворачивает список Trainee группы, то есть последний элемент списка становится начальным,
    // предпоследний - следующим за начальным и т.д..
    public void reverseTraineeList() {
        for (int i = 0; i < trainees.size(); i++) {
            trainees.add(i, trainees.get(trainees.size() - 1));
            trainees.remove(trainees.size() - 1);
        }
    }

    //15.Циклически сдвигает список Trainee группы на указанное число позиций. Для положительного значения positions
    // сдвигает вправо, для отрицательного - влево на модуль значения positions.
    public void rotateTraineeList(int positions) {
        if (positions > 0) {
            for (int i = 0; i < positions; i++) {
                int finishIndex = trainees.size();
                trainees.add(0, trainees.get(trainees.size() - 1));
                trainees.remove(finishIndex);

            }
        } else if(positions < 0) {
            for (int i = positions; i < 0; i++) {
                trainees.add(trainees.get(0));
                trainees.remove(trainees.get(0));

            }
        }
    }

    //16.Возвращает список тех Trainee группы , которые имеют наивысшую оценку. Иными словами, если в группе есть
    // Trainee с оценкой 5, возвращает список получивших оценку 5, если же таких нет, но есть Trainee с оценкой 4,
    // возвращает список получивших оценку 4 и т.д. Для пустого списка выбрасывает TrainingException с TrainingErrorCode.
    // TRAINEE_NOT_FOUND. Желательно сделать этот метод  без сортировки и в один проход по списку.
    public List<Trainee> getTraineesWithMaxRating() throws TrainingException {
        if (trainees.size() == 0) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        int maxRating = 0;
        List<Trainee>  result = new ArrayList<>();
        for(Trainee trainee : trainees) {
            if(trainee.getRating() > maxRating) {
                result.clear();
                maxRating = trainee.getRating();
            }
            if (trainee.getRating() == maxRating) {
                result.add(trainee);
            }
        }
        return result;
    }

    //17.Проверяет, есть ли в группе хотя бы одна пара Trainee, для которых совпадают имя, фамилия и оценка.
    public boolean hasDuplicates() {
        for (int i = 0; i < trainees.size(); i++) {
            for (int j = i + 1; j < trainees.size(); j++) {
                if (trainees.get(i).getFirstName().equals(trainees.get(j).getFirstName()) &&
                        trainees.get(i).getLastName().equals(trainees.get(j).getLastName()) &&
                        trainees.get(i).getRating() == trainees.get(j).getRating()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int compareFirstname(Trainee o1, Trainee o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }

    public int compareRathing(Trainee o1, Trainee o2) {
        return o1.getRating() < o2.getRating() ? 1 : -1;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Group)) {
            return false;
        }
        Group group = (Group) obj;
        return obj instanceof Group && group.getName().equals(getName()) && group.getRoom().equals(getRoom())
                && group.getTrainees().equals(getTrainees());
    }
}
