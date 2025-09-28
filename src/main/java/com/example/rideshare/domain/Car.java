package com.example.rideshare.domain;

public final class Car extends Vehicle {
    private final int seatCount;

    public Car(String model, int seatCount) {
        super(model);
        if (seatCount <= 0) throw new IllegalArgumentException("seatCount must be > 0");
        this.seatCount = seatCount;
    }

    public int getSeatCount() { return seatCount; }

    public String vehicleType() {
        return "Car"; }

}
