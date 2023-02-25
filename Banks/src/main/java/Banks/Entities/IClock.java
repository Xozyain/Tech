package Banks.Entities;

import java.time.LocalDateTime;

/**
 * The interface Clock.
 */
public interface IClock {
    /**
     * Gets now time.
     *
     * @return the now time
     */
    LocalDateTime getNowTime();
}
