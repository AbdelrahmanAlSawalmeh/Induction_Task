package com.progressoft.induction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class SnackMachine {

    public static int DEFAULT_QUANTITY = 5;
    private Money moneyInTransaction;
    private Money moneyInside;
    private List<Snack> snacksList = new ArrayList<Snack>() {{
        add(new Snack(SnackType.CHEWING_GUM, SnackMachine.DEFAULT_QUANTITY, 0.5));
        add(new Snack(SnackType.CHIPS, SnackMachine.DEFAULT_QUANTITY, 1.0));
        add(new Snack(SnackType.CHOCOLATE, SnackMachine.DEFAULT_QUANTITY, 2.0));
    }};


    public SnackMachine() {
        this.moneyInTransaction = Money.ZERO;
        this.moneyInside = Money.ZERO;
    }

    public void insertMoney(Money amount) {
        if (amount == null) {
            throw new IllegalArgumentException();
        } else if (amount.getAmount().compareTo(BigDecimal.valueOf(0.25)) == 0 || 
        amount.getAmount().compareTo(BigDecimal.valueOf(0.5)) == 0 || 
        amount.getAmount().compareTo(BigDecimal.valueOf(1.0)) == 0 ||
        amount.getAmount().compareTo(BigDecimal.valueOf(5.0)) == 0 || 
        amount.getAmount().compareTo(BigDecimal.valueOf(10.0)) == 0) {
            this.moneyInTransaction = this.moneyInTransaction.add(amount);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Money moneyInside() {
        return this.moneyInside;
    }

    public Money moneyInTransaction() {
        return this.moneyInTransaction;
    }

    public Snack chewingGums() {
        return this.snacksList.get(0);
    }

    public Snack chips() {
        return this.snacksList.get(1);
    }

    public Snack chocolates() {
        return this.snacksList.get(2);
    }

    public Money buySnack(SnackType snackType) {
        Money change = null;
        Money totalPrice;
        for (Snack snack : snacksList) {
            if (snack.getSnackType() == snackType) {
                totalPrice = new Money(BigDecimal.valueOf(snack.price()));
                if (this.moneyInTransaction().getAmount().compareTo(totalPrice.getAmount()) >= 0 && snack.quantity() > 0) {
                    change = new Money(this.moneyInTransaction().getAmount().subtract(totalPrice.getAmount()));
                    this.moneyInside = this.moneyInside().add(totalPrice);
                    snack.setQuantity(snack.quantity() - 1);
                } else {
                    throw new IllegalStateException("Insufficient funds or not enough quantity!");
                }
            } else {
                continue;
            }
        }
        this.moneyInTransaction = Money.ZERO;
        return change;
    }
}
