package com.example.rideshare.trip;

import com.example.rideshare.domain.Vehicle;
import com.example.rideshare.pricing.PricingStrategy;
import com.example.rideshare.user.User;
import com.example.rideshare.money.Money;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Trip {
    private final UUID id;
    private final User user;
    private final Vehicle vehicle;
    private TripState state;
    private Instant startTime;
    private Instant endTime;
    private double distanceKm;
    private final PricingStrategy pricingStrategy;
    private Money price;

    public Trip(User user, Vehicle vehicle, PricingStrategy pricingStrategy) {
        this.id = UUID.randomUUID();
        this.user = Objects.requireNonNull(user);
        this.vehicle = Objects.requireNonNull(vehicle);
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy);
        this.state = TripState.CREATED;
        this.price = Money.ZERO;
    }

    public UUID getId() { return id; }
    public User getUser() { return user; }
    public Vehicle getVehicle() { return vehicle; }
    public TripState getState() { return state; }
    public Instant getStartTime() { return startTime; }
    public Instant getEndTime() { return endTime; }
    public double getDistanceKm() { return distanceKm; }
    public Money getPrice() { return price; }

    public synchronized void start() {
        if (state != TripState.CREATED) {
            throw new IllegalStateException("Trip must be in CREATED to start. Current: " + state);
        }
        vehicle.markInUse();
        this.startTime = Instant.now();
        this.state = TripState.STARTED;
    }

    public synchronized void end(double distanceKm) {
        if (state != TripState.STARTED) {
            throw new IllegalStateException("Trip must be STARTED to end. Current: " + state);
        }
        this.endTime = Instant.now();
        this.distanceKm = Math.max(0.0, distanceKm);

        Money calculated = pricingStrategy.calculate(this);
        Money toCharge = user.applyMembershipDiscount(calculated);
        user.charge(toCharge);

        this.price = toCharge;
        this.state = TripState.COMPLETED;
        vehicle.markAvailable();
    }

    @Override
    public String toString() {
        return "Trip { " + id + ", user = " + user.getName() + ", vehicle = " + vehicle.getModel() +
               ", state = " + state + ", price = " + price + "}";
    }
}
