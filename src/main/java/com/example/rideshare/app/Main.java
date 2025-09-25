package com.example.rideshare.app;

import com.example.rideshare.domain.EBike;
import com.example.rideshare.domain.Scooter;
import com.example.rideshare.domain.Car;
import com.example.rideshare.money.Money;
import com.example.rideshare.pricing.TimeBasedPricing;
import com.example.rideshare.pricing.DistanceBasedPricing;
import com.example.rideshare.pricing.SurgeDecorator;
import com.example.rideshare.pricing.HybridPricing;
import com.example.rideshare.trip.Trip;
import com.example.rideshare.user.MembershipTier;
import com.example.rideshare.user.User;

public class Main {
    public static void main(String[] args) {
        User alice = new User("Alice", MembershipTier.STANDARD, new Money(100.0));
        Car car = new Car("Toyota Corolla", 5);
        EBike ebike = new EBike("E-Bike X", 500);
        Scooter scooter = new Scooter("Scoot-2000", 25);

        System.out.println("Vehicles:");
        System.out.println(car);
        System.out.println(ebike);
        System.out.println(scooter);

        var pricing = new HybridPricing(0.2 /* per minute */, 0.5 /* per km */);
        var timePricing = new TimeBasedPricing(0.3);
        var distPricing = new DistanceBasedPricing(1.2);
        var surge = new SurgeDecorator(timePricing, 1.5);

        Trip trip = new Trip(alice, ebike, pricing);
        trip.start();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        trip.end(0.8);

        System.out.println("Trip completed: " + trip);
        System.out.println("User after trip: " + alice);
    }
}
