package org.example;

import java.time.LocalDateTime;

class Booking
{
    private int bookingId;
    private int passengerId;
    private int vehicleId;
    private LocalDateTime bookingDateTime;
    private LocationGPS startLocation;
    private LocationGPS endLocation;

    private double cost;  //Calculated at booking time

    //TODO - see specification

    public Booking(int bookingId, int passengerId, int vehicleId, LocalDateTime bookingDateTime,
                   LocationGPS startLocation, LocationGPS endLocation, double cost)
    {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDateTime = bookingDateTime;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.cost = cost;
    }



}
