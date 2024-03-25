package kata.morningroutine;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MorningRoutineTest {

    @ParameterizedTest
    @MethodSource("routineSource")
    void shouldGetRoutine(LocalTime time, String expectedRoutine) {
        // Given
        MorningRoutine morningRoutine = new MorningRoutine();

        // When
        String routine = morningRoutine.getCurrentRoutine(time);

        // Then
        assertEquals(expectedRoutine, routine);
    }

    private static Stream<Arguments> routineSource() {
        return Stream.of(
                Arguments.of(LocalTime.of(5, 59), "No activity"),
                Arguments.of(LocalTime.of(6, 0), "Do exercise"),
                Arguments.of(LocalTime.of(6, 30), "Do exercise"),
                Arguments.of(LocalTime.of(6, 59), "Do exercise"),
                Arguments.of(LocalTime.of(7, 0), "Read and study"),
                Arguments.of(LocalTime.of(7, 30), "Read and study"),
                Arguments.of(LocalTime.of(7, 59), "Read and study"),
                Arguments.of(LocalTime.of(8, 0), "Have breakfast"),
                Arguments.of(LocalTime.of(8, 30), "Have breakfast"),
                Arguments.of(LocalTime.of(8, 59), "Have breakfast"),
                Arguments.of(LocalTime.of(9, 0), "No activity")
        );
    }
}
