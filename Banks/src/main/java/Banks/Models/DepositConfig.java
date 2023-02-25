package Banks.Models;

import java.math.BigDecimal;
import java.util.Map;

/**
 * The type Deposit config.
 */
public record DepositConfig(int timeLimit, Map<Money, BigDecimal> percents, Money amount) {
}
