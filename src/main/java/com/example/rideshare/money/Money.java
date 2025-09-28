package com.example.rideshare.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money {
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public Money(double value) {
        this(BigDecimal.valueOf(value));
    }

    public BigDecimal toBigDecimal() { return amount; }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {

        return new Money(this.amount.subtract(other.amount));
    }

    public Money multiply(double multiplier) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplier)));
    }

    public int compareTo(Money other) {
        return this.amount.compareTo(other.amount);
    }

    public boolean isNegative() {
        return amount.signum() < 0;
    }

    @Override
    public String toString() {
        return amount.toPlainString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return amount.equals(money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
