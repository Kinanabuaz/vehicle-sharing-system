package com.example.rideshare.pricing;

import com.example.rideshare.trip.Trip;
import com.example.rideshare.money.Money;

public class HybridPricing implements PricingStrategy {
    private final double pricePerMinute;
    private final double pricePerKm;

    public HybridPricing(double pricePerMinute, double pricePerKm) {
        this.pricePerMinute = pricePerMinute;
        this.pricePerKm = pricePerKm;
    }

    @Override
    public Money calculate(Trip trip) {
        double km = Math.max(0.0, trip.getDistanceKm());
        double minutes = 0.0;
        if (trip.getStartTime() != null && trip.getEndTime() != null) {
            minutes = java.time.Duration.between(trip.getStartTime(), trip.getEndTime()).toMillis() / 60000.0;
        }
        double total = (pricePerMinute * Math.max(0.0, minutes)) + (pricePerKm * km);
        return new Money(total);
    }
}
