package com.example.rideshare.pricing;

import com.example.rideshare.trip.Trip;
import com.example.rideshare.money.Money;

public class SurgeDecorator implements PricingStrategy {
    private final PricingStrategy delegate;
    private final double surgeMultiplier;

    public SurgeDecorator(PricingStrategy delegate, double surgeMultiplier) {
        if (surgeMultiplier <= 0) throw new IllegalArgumentException("surgeMultiplier > 0");
        this.delegate = delegate;
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public Money calculate(Trip trip) {
        Money base = delegate.calculate(trip);
        return base.multiply(surgeMultiplier);
    }
}
