package Banks.Models;

import java.math.BigDecimal;
import java.util.Map;
public record  DepositConfig(int timeLimit, Map<Money, BigDecimal> percents, Money amount) {}
