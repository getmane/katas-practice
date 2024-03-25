package kata.morningroutine.strategy;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class DoExercise implements Routine {

    private final String name;
    private final LocalTime start;
    private final LocalTime end;

    public DoExercise() {
        this.name = "Do exercise";
        this.start = LocalTime.of(6, 0);
        this.end = LocalTime.of(6, 59);
    }

    @Override
    public String getRoutine() {
        return this.name;
    }
}
