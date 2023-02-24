package Banks.Entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * The type Clock.
 */
public class Clock {
    private LocalDateTime currentTime = LocalDateTime.now();
    private ChronoUnit timeUnit;
    private long timeShift;

    /**
     * Instantiates a new Clock.
     *
     * @param timeShift the time shift
     * @param timeUnit  the time unit
     */
    public Clock(long timeShift, ChronoUnit timeUnit) {
        this.timeShift = timeShift;
        this.timeUnit = timeUnit;
    }

    /**
     * Increment time.
     */
    public void incrementTime() {
        currentTime = currentTime.plus(timeShift, timeUnit);
    }

    /**
     * Gets now.
     *
     * @return the now
     */
    public LocalDateTime getNow() {
        return currentTime;
    }
}