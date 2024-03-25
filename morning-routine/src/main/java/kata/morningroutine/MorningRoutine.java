package kata.morningroutine;

import kata.morningroutine.strategy.NoActivity;
import kata.morningroutine.strategy.Routine;
import kata.morningroutine.strategy.DoExercise;
import kata.morningroutine.strategy.HaveBreakfast;
import kata.morningroutine.strategy.ReadAndStudy;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MorningRoutine {

    private final List<Routine> routines;

    public MorningRoutine() {
        this.routines = new ArrayList<>(List.of(new DoExercise(), new ReadAndStudy(), new HaveBreakfast()));
    }

    String getCurrentRoutine(LocalTime time) {
        return this.routines.stream()
                .filter(routine -> shouldPerformRoutine(routine, time))
                .findFirst().orElse(new NoActivity())
                .getRoutine();
    }

    private boolean shouldPerformRoutine(Routine routine, LocalTime currentTime) {
        LocalTime start = routine.getStart();
        LocalTime end = routine.getEnd();

        return (start.equals(currentTime) || end.equals(currentTime))
                || (start.isBefore(currentTime) && end.isAfter(currentTime));
    }
}
