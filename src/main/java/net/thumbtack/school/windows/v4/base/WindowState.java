package net.thumbtack.school.windows.v4.base;

/**
 * Состояние окна.
 * <p>
 * В ходе разработки выяснилось, что двух вариантов для состояния окна (активно или пассивно) недостаточно.
 * Окно может находиться еще и в состоянии “разрушено”, в него окно переходит, когда его закрывают, но оно еще
 * окончательно не закрыто. Поэтому вместо boolean active нам придется ввести поле, которое может принимать 3 значения :
 * ACTIVE, INACTIVE и DESTROYED. При этом надо иметь в виду, что переходы из состояния ACTIVE или INACTIVE в другое
 * состояние разрешены, а вот переход из состояния DESTROYED в любое иное состояние не разрешается - такое окно
 * “оживить” уже нельзя. Кроме того, нельзя создавать окно с состоянием DESTROYED.
 */
public enum WindowState {
    ACTIVE,
    INACTIVE,
    DESTROYED;

    public static WindowState fromString(String stateString) throws WindowException {
        if (stateString == null) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        }
        if (stateString.equals(WindowState.ACTIVE.toString())) {
            return ACTIVE;
        }
        if (stateString.equals(WindowState.INACTIVE.toString())) {
            return INACTIVE;
        }
        if (stateString.equals(WindowState.DESTROYED.toString())) {
            return DESTROYED;
        }
        return null;
    }
}
