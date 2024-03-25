package kata.morningroutine.strategy;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class HaveBreakfast implements Routine {

    private final String name;
    private final LocalTime start;
    private final LocalTime end;

    public HaveBreakfast() {
        this.name = "Have breakfast";
        this.start = LocalTime.of(8, 0);
        this.end = LocalTime.of(8, 59);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
