package kata.morningroutine.strategy;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class ReadAndStudy implements Routine {

    private final String name;
    private final LocalTime start;
    private final LocalTime end;

    public ReadAndStudy() {
        this.name = "Read and study";
        this.start = LocalTime.of(7, 0);
        this.end = LocalTime.of(7, 59);
    }

    @Override
    public String getRoutine() {
        return this.name;
    }
}
