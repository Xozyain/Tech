package Banks.Models;

import java.util.UUID;

/**
 * The type Transaction.
 */
public record Transaction(UUID id, UUID accountWithdraw, UUID accountDeposit, Money amount) {
}

//    private final Money amount;
//    private final UUID id;
//    private final UUID accountWithdraw;
//    private final UUID accountDeposit;
//
//    public Transaction(UUID id, UUID accountWithdraw, UUID accountDeposit, Money amount) {
//        this.id = id;
//        this.accountWithdraw = accountWithdraw;
//        this.accountDeposit = accountDeposit;
//        this.amount = amount;
//    }
//
//    public final Money getAmount() {
//        return amount;
//    }
//
//    public final UUID getId() {
//        return id;
//    }
//
//    public final UUID getAccountWithdraw() {
//        return accountWithdraw;
//    }
//
//    public final UUID getAccountDeposit() {
//        return accountDeposit;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) {
//            return true;
//        }
//        if (!(obj instanceof Transaction other)) {
//            return false;
//        }
//        return getId().equals(other.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getId().hashCode();
//    }
