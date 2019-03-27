package net.thumbtack.school.ttschool;

public class TrainingException extends Exception {

    private TrainingErrorCode errorCode;

    public TrainingException(TrainingErrorCode trainingErrorCode) {
        super(trainingErrorCode.toString());
        errorCode = trainingErrorCode;
    }

    public TrainingErrorCode getErrorCode() {
        return errorCode;
    }
}
