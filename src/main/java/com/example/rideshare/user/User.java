package com.example.rideshare.user;

import com.example.rideshare.money.Money;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String name;
    private MembershipTier tier;
    private Money balance;

    public User(String name, MembershipTier tier, Money initialBalance) {
        this.id = UUID.randomUUID();
        this.name = Objects.requireNonNull(name);
        this.tier = Objects.requireNonNull(tier);
        this.balance = Objects.requireNonNull(initialBalance);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public MembershipTier getTier() { return tier; }
    public Money getBalance() { return balance; }

    public synchronized void credit(Money amount) {
        if (amount == null) throw new IllegalArgumentException("amount null");
        balance = balance.add(amount);
    }

    public synchronized void charge(Money amount) {
        if (amount == null) throw new IllegalArgumentException("amount null");
        if (amount.isNegative() || amount.compareTo(Money.ZERO) == 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient balance. Current: " + balance + ", required: " + amount);
        }
        balance = balance.subtract(amount);
    }

    public synchronized Money applyMembershipDiscount(Money price) {
        if (tier == MembershipTier.PREMIUM) {
            return price.multiply(0.9);
        }
        return price;
    }

    @Override
    public String toString() {
        return "User { " + name + ", tier = " + tier + ", balance = " + balance + " }";
    }
}
