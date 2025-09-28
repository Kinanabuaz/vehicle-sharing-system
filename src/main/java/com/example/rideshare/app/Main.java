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

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // User
        System.out.print("Enter user name: ");
        String name = input.nextLine();

        System.out.print("Enter balance: ");
        double balance = input.nextDouble();
        input.nextLine();
        User u1 = new User(name, MembershipTier.STANDARD, new Money(balance));

        // Car
        System.out.print("Enter car model: ");
        String carModel = input.nextLine();

        System.out.print("Enter seat count: ");
        int seatCount = input.nextInt();
        input.nextLine();


        Car car = new Car(carModel, seatCount);

        // EBike
        System.out.print("Enter EBikeModle: ");
        String ebikeModle = input.nextLine();

        System.out.print("Enter batteryCapacity: ");
        int batteryCapacity = input.nextInt();
        input.nextLine();

        EBike ebike = new EBike(ebikeModle,batteryCapacity);

        // Scooter
        System.out.print("Enter scooter model: ");
        String scootModel = input.nextLine();

        System.out.print("Enter max speed (km/h): ");
        int maxSpeed = input.nextInt();

        Scooter scooter = new Scooter(scootModel, maxSpeed);
        //Output
        System.out.println("/nUser : "+u1);
        System.out.println("Vehicles:");
        System.out.println(car);
        System.out.println(ebike);
        System.out.println(scooter);
        //  Determine PricingStrategy  calculate(Strategy Pattern)
        var pricing = new HybridPricing(0.2 /* per minute */, 0.5 /* per km */);
        var timePricing = new TimeBasedPricing(0.3);
        var distPricing = new DistanceBasedPricing(1.2);
        var surge = new SurgeDecorator(timePricing, 1.5);

        Trip trip = new Trip(u1, ebike, pricing);
        trip.start();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        trip.end(0.8);

        System.out.println("Trip completed: " + trip);
        System.out.println("User after trip: " + u1);
    }
}
