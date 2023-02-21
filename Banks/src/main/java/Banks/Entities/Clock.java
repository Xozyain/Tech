package Banks.Entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Clock {
	private LocalDateTime currentTime = LocalDateTime.now();
	private ChronoUnit timeUnit;
	private long timeShift;

	public Clock(long timeShift, ChronoUnit timeUnit) {
		this.timeShift = timeShift;
		this.timeUnit = timeUnit;
	}

	public void incrementTime() {
		currentTime = currentTime.plus(timeShift, timeUnit);
	}

	public LocalDateTime getNow() {
		return currentTime;
	}
}