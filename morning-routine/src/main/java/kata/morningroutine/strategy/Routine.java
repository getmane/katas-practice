package kata.morningroutine.strategy;

import java.time.LocalTime;

public interface Routine {

    LocalTime getStart();

    LocalTime getEnd();

    String getRoutine();
}
