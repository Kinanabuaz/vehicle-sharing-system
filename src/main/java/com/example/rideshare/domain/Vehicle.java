package com.example.rideshare.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class Vehicle {
    private final UUID id;
    private VehicleState state;
    private final String model;

    protected Vehicle(String model) {
        this.id = UUID.randomUUID();
        this.model = Objects.requireNonNull(model, "model must not be null");
        this.state = VehicleState.AVAILABLE;
    }

    public UUID getId() { return id; }
    public VehicleState getState() { return state; }
    public String getModel() { return model; }

    public synchronized void markInUse() {
        if (state != VehicleState.AVAILABLE) {
            throw new IllegalStateException("Cannot mark in use. Current state: " + state);
        }
        state = VehicleState.IN_USE;
    }

    public synchronized void markAvailable() {
        if (state == VehicleState.AVAILABLE) {
            throw new IllegalStateException("Vehicle already available.");
        }
        state = VehicleState.AVAILABLE;
    }

    public synchronized void sendToMaintenance() {
        if (state == VehicleState.IN_USE) {
            throw new IllegalStateException("Cannot send to maintenance while IN_USE.");
        }
        state = VehicleState.MAINTENANCE;
    }

    public abstract String vehicleType();

    @Override
    public String toString() {
        return vehicleType() + "{id=" + id + ", model=" + model + ", state=" + state + "}";
    }
}
