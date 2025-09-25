package com.example.rideshare.pricing;

import com.example.rideshare.trip.Trip;
import com.example.rideshare.money.Money;

public interface PricingStrategy {
    Money calculate(Trip trip);
}
