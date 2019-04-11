package net.thumbtack.school.ttschool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TraineeMap {
    private HashMap<Trainee, String> map = new HashMap<>();

    public TraineeMap() {
    }

    //2.Добавляет пару Trainee - String в Map. Если Map уже содержит информацию об этом Trainee, выбрасывает
    // TrainingException с TrainingErrorCode.DUPLICATE_TRAINEE.
    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if (map.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        }
        map.put(trainee, institute);
    }

    //3.Если в Map уже есть информация о данном Trainee, заменяет пару Trainee - String в Map на новую пару, иначе
    // выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND.
    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if (!map.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        map.put(trainee, institute);
    }

    //4.Удаляет информацию о Trainee из Map. Если Map не содержит информации о таком Trainee,
    // выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND.
    public void removeTraineeInfo(Trainee trainee) throws TrainingException {
        if (!map.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        for (Map.Entry<Trainee, String> pair : map.entrySet()) {
            if (pair.getKey().equals(trainee)) {
                map.remove(trainee);
            }
        }
    }

    //5.Возвращает количество элементов в Map, иными словами, количество студентов
    public int getTraineesCount() {
        return map.keySet().size();
    }

    //6.Возвращает институт, в котором учится данный Trainee. Если Map не содержит информации о таком Trainee,
    // выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {
        for (Map.Entry<Trainee, String> pair : map.entrySet()) {
            if (pair.getKey().equals(trainee)) {
                return pair.getValue();
            }
        }
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    //7.Возвращает Set из всех имеющихся в Map Trainee.
    public Set<Trainee> getAllTrainees() {
        Set<Trainee> traineeSet = new HashSet<>();
        for (Map.Entry<Trainee, String> pair : map.entrySet()) {
            traineeSet.add(pair.getKey());
        }
        return traineeSet;
    }

    //8.Возвращает Set из всех институтов.
    public Set<String> getAllInstitutes() {
        Set<String> stringSet = new HashSet<>();
        for (Map.Entry<Trainee, String> pair : map.entrySet()) {
            stringSet.add(pair.getValue());
        }
        return stringSet;
    }

    //9.Возвращает true, если хоть один студент учится в этом institute, иначе false.
    public boolean isAnyFromInstitute(String institute) {
        for (Map.Entry<Trainee, String> pair : map.entrySet()) {
            if (pair.getValue().equals(institute)) {
                return true;
            }
        }
        return false;
    }


}
