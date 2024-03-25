package kata.morningroutine.strategy;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class NoActivity implements Routine {

    private final String name;

    public NoActivity() {
        this.name = "No activity";
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public LocalTime getStart() {
        return null;
    }

    @Override
    public LocalTime getEnd() {
        return null;
    }
}
