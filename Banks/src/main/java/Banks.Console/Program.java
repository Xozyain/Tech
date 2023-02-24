package Banks.Console;

import Banks.Models.*;
import Banks.console.chain.MainController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Program {
    public static void main(String[] args) {
        var creditConfig = new CreditConfig(new Money(new BigDecimal(500)), new Money(new BigDecimal(10)));
        var debitConfig = new DebitConfig(new BigDecimal(3));
        Map<Money, BigDecimal> percents = new HashMap<Money, BigDecimal>();
        percents.put(new Money(new BigDecimal(50)), new BigDecimal(4));

        var depositConfig = new DepositConfig(10, percents, new Money(new BigDecimal(50)));

        var bank = new Bank("MYbank", new Money(new BigDecimal(100)), creditConfig, debitConfig, depositConfig);
        var mainController = new MainController(bank);
        mainController.handle();
    }
}
