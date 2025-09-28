# Rideshare Example (Java 17)

Simple in-memory domain model for a vehicle-sharing service.
This project is provided as a ready-to-open example for learning:

Object-Oriented Programming (OOP)

Strategy Pattern

Basic domain modeling

Project Structure

com.example.rideshare.domain – Entities and core domain objects

com.example.rideshare.user – User entity and membership logic

com.example.rideshare.money – Value object for handling money

com.example.rideshare.pricing – Pricing strategies (Strategy Pattern)

com.example.rideshare.trip – Trip lifecycle and pricing calculation

com.example.rideshare.app – Application entry point (Main class)

Requirements

Java 17+

Maven 3+

IDE (IntelliJ IDEA, Eclipse) optional

Run the Project

Build and run using Maven:

# Build the project
mvn package

# Run the Main class
mvn exec:java

Usage Example
Start a Trip
Trip trip = new Trip(alice, ebike, pricing);
trip.start();

Wait a bit (simulate trip time)
Thread.sleep(1500); // simulate 1.5 sec

End the Trip
```java
trip.end(0.8); // distance = 0.8 km


Example Output
Trip completed: Trip{id=..., user=Alice, vehicle=Ebike, distance=0.8 km, price=$1.20}
User after trip: User{name=Alice, balance=$98.80}

Learning Goals

How to design a simple domain model.

How to apply Strategy Pattern for flexible pricing.

How to work with Value Objects like Money.

How to structure Java packages for clarity.




