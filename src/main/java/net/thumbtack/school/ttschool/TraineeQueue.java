package net.thumbtack.school.ttschool;

import java.util.ArrayList;

public class TraineeQueue {

    private ArrayList<Trainee> traineeQueue = new ArrayList<>();

    public TraineeQueue() {
    }

    //2.Добавляет студента в очередь.
    public void addTrainee(Trainee trainee) {
        traineeQueue.add(trainee);
    }

    //3.Удаляет студента из очереди. Метод возвращает удаленного Trainee. Если в очереди никого нет, выбрасывает
    // TrainingException с TrainingErrorCode.EMPTY_TRAINEE_QUEUE.
    public Trainee removeTrainee() throws TrainingException {
        if (traineeQueue.size() == 0 ) {
            throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        }
        Trainee curentTrainee = traineeQueue.get(0);
        traineeQueue.remove(traineeQueue.get(0));
        return curentTrainee;
    }

    //4.Возвращает true, если очередь пуста, иначе false
    public boolean isEmpty() {
      return traineeQueue.size() == 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TraineeQueue)) {
            return false;
        }

        TraineeQueue traineeQueue = (TraineeQueue) obj;
        return obj instanceof TraineeQueue;
    }
}
