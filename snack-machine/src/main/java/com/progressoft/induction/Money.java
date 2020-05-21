package com.progressoft.induction;

import java.math.BigDecimal;

public class Money {

    private BigDecimal amount;
    static final Money ZERO = new Money(BigDecimal.valueOf(0.00));
    static final Money QUARTER_DINAR = new Money(BigDecimal.valueOf(0.25));
    static final Money HALF_DINAR = new Money(BigDecimal.valueOf(0.50));
    static final Money DINAR = new Money(BigDecimal.valueOf(1.00));
    static final Money FIVE_DINARS = new Money(BigDecimal.valueOf(5.00));
    static final Money TEN_DINARS = new Money(BigDecimal.valueOf(10.00));

    public Money(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException();
        } else {
            this.amount = amount;
        }
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Money add(Money amount) {
        return new Money(this.amount.add(amount.getAmount()));
    }

    public boolean isLessThan(Money amount) {
        if (amount == null) {
            return false;
        } else if (this.getAmount().compareTo(amount.getAmount()) < 0) {
            return true;
        } else {
            return false;
        }
    }

    public Money subtract(Money amount) {
        if (this.getAmount().compareTo(amount.getAmount()) < 0) {
            throw new IllegalArgumentException();
        }
        return new Money(this.getAmount().subtract(amount.getAmount()));
    }

    @Override
    public boolean equals(Object obj) {
        Money temp = (Money) obj;
        return amount.equals(temp.amount);
    }
}
