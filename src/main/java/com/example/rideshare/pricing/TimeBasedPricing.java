package com.example.rideshare.pricing;

import com.example.rideshare.trip.Trip;
import com.example.rideshare.money.Money;

import java.time.Duration;

public class TimeBasedPricing implements PricingStrategy {
    private final double pricePerMinute;

    public TimeBasedPricing(double pricePerMinute) {
        if (pricePerMinute < 0) throw new IllegalArgumentException("pricePerMinute >= 0");
        this.pricePerMinute = pricePerMinute;
    }

    @Override
    public Money calculate(Trip trip) {
        if (trip.getStartTime() == null || trip.getEndTime() == null) {
            return Money.ZERO;
        }
        Duration d = Duration.between(trip.getStartTime(), trip.getEndTime());
        double minutes = d.toMillis() / 60000.0;
        double total = pricePerMinute * Math.max(0.0, minutes);
        return new Money(total);
    }
}
