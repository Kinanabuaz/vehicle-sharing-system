package com.example.rideshare.pricing;

import com.example.rideshare.trip.Trip;
import com.example.rideshare.money.Money;

public class DistanceBasedPricing implements PricingStrategy {
    private final double pricePerKm;

    public DistanceBasedPricing(double pricePerKm) {
        if (pricePerKm < 0) throw new IllegalArgumentException("pricePerKm >= 0");
        this.pricePerKm = pricePerKm;
    }

    @Override
    public Money calculate(Trip trip) {
        double km = Math.max(0.0, trip.getDistanceKm());
        double total = pricePerKm * km;
        return new Money(total);
    }
}
